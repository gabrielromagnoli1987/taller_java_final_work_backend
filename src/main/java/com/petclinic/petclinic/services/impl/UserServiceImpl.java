package com.petclinic.petclinic.services.impl;

import java.util.*;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.dtos.UserDTO;
import com.petclinic.petclinic.models.Privilege;
import com.petclinic.petclinic.models.Role;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.models.constants.Roles;
import com.petclinic.petclinic.repositories.PrivilegeRepository;
import com.petclinic.petclinic.repositories.RoleRepository;
import com.petclinic.petclinic.repositories.UserConfigRepository;
import com.petclinic.petclinic.repositories.UserRepository;
import com.petclinic.petclinic.services.EmailService;
import com.petclinic.petclinic.services.UserService;
import com.petclinic.petclinic.utils.EmailValidator;
import com.petclinic.petclinic.utils.HashingPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	PrivilegeRepository privilegeRepository;

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
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userEmail));
		return user.orElseThrow(() -> new EntityNotFoundException("User with email: " + userEmail + " does not exists"));
	}

	@Override
	public User getUserByEmailAndPassword(String userEmail, String userPassword) throws BadCredentialsException {
		return null;
	}

	@Override
	public User createUser(UserDTO userDTO) throws EntityExistsException {
		if (EmailValidator.validate(userDTO.getEmail())) {
			User tempUser = userRepository.findByEmail(userDTO.getEmail());
			if (tempUser == null) {
				User user = new User(userDTO.getName(), userDTO.getLastname(), userDTO.getEmail());
				user.setPassword(HashingPassword.hashPassword(userDTO.getPassword()));
				user.setIsEnabled(true);
				UserConfig userConfig = new UserConfig();
				userConfigRepository.save(userConfig);
				user.setUserConfig(userConfig);
				Role role;
				if (userDTO.isVet()) {
					user.setAddress(userDTO.getAddress() != null ? userDTO.getAddress() : "");
					// TODO: handle file
					String path = "resumePath";
					user.setResume(path);
					role = roleRepository.findByName(Roles.ROLE_VET_USER.toString());
				} else {
					role = roleRepository.findByName(Roles.ROLE_OWNER_USER.toString());
					user.setAddress("");
					user.setResume("");
				}
				user.setRoles(Arrays.asList(role));
				user = userRepository.save(user);
				emailService.sendEmail(user.getEmail(), "New Account", "Welcome");
				return user;
			} else {
				throw new EntityExistsException("The email: " + userDTO.getEmail() + " already exists");
			}
		} else {
			throw new IllegalArgumentException("The email: " + userDTO.getEmail() + " is not valid");
		}
	}

	@Override
	public User updateUser(UserDTO userDTO, Long userId) throws EntityNotFoundException, EntityExistsException {
		return null;
	}

	@Override
	public User updateUserEmail(String email, Long userId) throws EntityNotFoundException, EntityExistsException {
		return null;
	}

	@Override
	public String deleteUserById(Long userId) throws IllegalArgumentException {
		userRepository.deleteById(userId);
		return "The user with id: " + userId + "was deleted.";
	}

	/**
	 * Spring Security
	 */
	@Override
	public UserDetails loadUserByUsername(String email)	throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
				true, getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
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

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
