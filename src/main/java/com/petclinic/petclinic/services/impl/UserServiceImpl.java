package com.petclinic.petclinic.services.impl;

import java.util.*;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.dtos.EnableVetDTO;
import com.petclinic.petclinic.dtos.UserDTO;
import com.petclinic.petclinic.models.Privilege;
import com.petclinic.petclinic.models.Role;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.models.constants.Roles;
import com.petclinic.petclinic.repositories.RoleRepository;
import com.petclinic.petclinic.repositories.UserConfigRepository;
import com.petclinic.petclinic.repositories.UserRepository;
import com.petclinic.petclinic.services.EmailService;
import com.petclinic.petclinic.services.UserService;
import com.petclinic.petclinic.utils.EmailValidator;
import com.petclinic.petclinic.utils.HashingPassword;
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

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserConfigRepository userConfigRepository;

	@Autowired
	EmailService emailService;

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public User getUserById(Long userId) throws EntityNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		return user.orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " does not exists"));
	}

	@Override
	public User getUserByEmail(String userEmail) throws EntityNotFoundException {
		Optional<User> user = userRepository.findByEmail(userEmail);
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
		user = userRepository.save(user);
		emailService.sendEmail(user.getEmail(), "New Account", "Welcome");
		return user;
	}

	private void validateEmailFormat(String email) {
		if (EmailValidator.validate(email)) return;
		throw new IllegalArgumentException("The email format: " + email + " is not valid");
	}

	private void isEmailAvailable(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			throw new EntityExistsException("The email: " + email + " already exists");
		}
	}

	private void addUserConfig(User user) {
		UserConfig userConfig = new UserConfig(true, true, false);
		userConfigRepository.save(userConfig);
		user.setUserConfig(userConfig);
	}

	private void addRole(UserDTO userDTO, User user) {
		Role role;
		if (userDTO.isVet()) {
			user.setIsVetEnabled(false);
			user.setAddress(userDTO.getAddress() != null ? userDTO.getAddress() : "");
			user.setResume(userDTO.getResume() != null ? userDTO.getResume() : "");
			role = roleRepository.findByName(Roles.ROLE_VET_USER.toString());
		} else {
			role = roleRepository.findByName(Roles.ROLE_OWNER_USER.toString());
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
			userRepository.deleteById(userId);
			return "The user with id: " + userId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The user with id: " + userId + " doesn't exists");
		}
	}

	@Override
	public User enableVetUser(Long userId, EnableVetDTO enableVetDTO) {
		User user = this.getUserById(userId);
		user.setIsVetEnabled(enableVetDTO.getIsEnable());
		user = userRepository.save(user);
		emailService.sendEmail(user.getEmail(), "Account activated", "Your account has been activated");
		return user;
	}


	/**
	 * Spring Security
	 */
	@Override
	public UserDetails loadUserByUsername(String email)	throws UsernameNotFoundException {

		Optional<User> optionalUser = userRepository.findByEmail(email);
		User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
		Collection<Role> roles = roleRepository.findByUsers_Id(user.getId());
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
