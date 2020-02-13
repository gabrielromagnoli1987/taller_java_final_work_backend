package com.petclinic.petclinic.services.impl;

import com.petclinic.petclinic.dtos.EnableVetDTO;
import com.petclinic.petclinic.dtos.UserConfigDTO;
import com.petclinic.petclinic.dtos.UserDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Privilege;
import com.petclinic.petclinic.models.Role;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.models.constants.Roles;
import com.petclinic.petclinic.persistence.dao.RoleDAO;
import com.petclinic.petclinic.persistence.dao.UserConfigDAO;
import com.petclinic.petclinic.persistence.dao.UserDAO;
import com.petclinic.petclinic.persistence.utils.DAOFactory;
import com.petclinic.petclinic.services.EmailService;
import com.petclinic.petclinic.services.UserService;
import com.petclinic.petclinic.utils.EmailValidator;
import com.petclinic.petclinic.utils.HashingPassword;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	RoleDAO roleDAO = DAOFactory.getRoleDAO();

	UserDAO userDAO = DAOFactory.getUserDAO();

	UserConfigDAO userConfigDAO = DAOFactory.getUserConfigDAO();

	@Autowired
	EmailService emailService;

	public UserServiceImpl() {
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userDAO.findAll();
	}

	@Override
	public Page<User> getUsers(Pageable pageable) {
		return userDAO.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Page<User> getVetUsers(Pageable pageable) {
		return userDAO.findByIsVetEnabledIsNotNull(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Page<User> getActiveVetUsers(Pageable pageable) {
		return userDAO.findByIsVetEnabledTrue(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public User getUserById(Long userId) throws EntityNotFoundException {
		Optional<User> user = userDAO.retrieve(userId);
		return user.orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " does not exists"));
	}

	@Override
	public User getUserByEmail(String userEmail) throws EntityNotFoundException {
		Optional<User> user = userDAO.findByEmail(userEmail);
		return user.orElseThrow(() -> new EntityNotFoundException("User with email: " + userEmail + " does not exists"));
	}

	@Override
	public User createUser(UserDTO userDTO) throws EntityExistsException {
		validateEmailFormat(userDTO.getEmail());
		isEmailAvailable(userDTO.getEmail());
		User user = new User(userDTO.getName(), userDTO.getLastName(), userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setPassword(HashingPassword.hashPassword(userDTO.getPassword()));
		user.setIsEnabled(true);
		addUserConfig(user);
		addRole(userDTO, user);
		user = userDAO.create(user);
		emailService.sendEmail(user.getEmail(), "New Account", "Welcome");
		return user;
	}

	private void validateEmailFormat(String email) {
		if (EmailValidator.validate(email)) return;
		throw new IllegalArgumentException("The email format: " + email + " is not valid");
	}

	private void isEmailAvailable(String email) {
		Optional<User> optionalUser = userDAO.findByEmail(email);
		if (optionalUser.isPresent()) {
			throw new EntityExistsException("The email: " + email + " already exists");
		}
	}

	private void addUserConfig(User user) {
		UserConfig userConfig = new UserConfig(true, true, false);
		userConfigDAO.create(userConfig);
		user.setUserConfig(userConfig);
	}

	private void addRole(UserDTO userDTO, User user) {
		Role role;
		if (userDTO.isVet()) {
			user.setIsVetEnabled(false);
			user.setAddress(userDTO.getAddress() != null ? userDTO.getAddress() : "");
			user.setResume(userDTO.getResume() != null ? userDTO.getResume() : "");
			role = roleDAO.findByName(Roles.ROLE_VET_USER.toString());
		} else {
			role = roleDAO.findByName(Roles.ROLE_OWNER_USER.toString());
			user.setAddress("");
			user.setResume("");
		}
		user.setRoles(Collections.singletonList(role));
	}

	@Override
	public User updateUser(UserDTO userDTO, Long userId) throws EntityNotFoundException, EntityExistsException {
		return null;
	}

	@Override
	public String deleteUserById(Long userId) throws IllegalArgumentException {
		try {
			User user = new User();
			user.setId(userId);
			userDAO.delete(user);
			return "The user with id: " + userId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The user with id: " + userId + " doesn't exists");
		}
	}

	@Override
	public User enableVetUser(Long userId, EnableVetDTO enableVetDTO) {
		User user = this.getUserById(userId);
		user.setIsVetEnabled(enableVetDTO.getIsEnable());
		user = userDAO.update(userId, user);
		emailService.sendEmail(user.getEmail(), "Account activated", "Your account has been activated");
		return user;
	}

	@Override
	public UserConfig updateUserConfig(Long userId, UserConfigDTO userConfigDTO, Principal principal) throws OwnershipException {
		User user = this.getUserById(userId);
		canUpdate(user, principal);
		UserConfig userConfig = user.getUserConfig();
		BeanUtils.copyProperties(userConfigDTO, userConfig);
		return userConfigDAO.update(userConfig.getId(), userConfig);
	}

	private Boolean canUpdate(User user, Principal principal) throws OwnershipException {
		if (user.getEmail().equals(principal.getName())) {
			return Boolean.TRUE;
		}
		throw new OwnershipException("Error: the resource isn't yours");
	}


	/**
	 * Spring Security
	 */
	@Override
	public UserDetails loadUserByUsername(String email)	throws UsernameNotFoundException {

		Optional<User> optionalUser = userDAO.findByEmail(email);
		User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
		Collection<Role> roles = roleDAO.findByUserId(user.getId());
		Boolean accountNonLocked = Boolean.TRUE;
		if (user.getIsVetEnabled() != null && ! user.getIsVetEnabled()) {
			accountNonLocked = Boolean.FALSE;
		}
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
				accountNonLocked, getAuthorities(roles));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		// choose one of the two lines:
		return getGrantedAuthorities(getRoles(roles)); // puts the roles on the token
		// return getGrantedAuthorities(getPrivileges(roles)); // puts the privileges on the token
	}

	private List<String> getRoles(Collection<Role> roles) {
		List<String> string_roles = new ArrayList<>();
		for (Role role: roles) {
			string_roles.add(role.getName());
		}
		return string_roles;
	}

	private List<String> getPrivileges(Collection<Role> roles) {

		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> list) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String item : list) {
			authorities.add(new SimpleGrantedAuthority(item));
		}
		return authorities;
	}
}
