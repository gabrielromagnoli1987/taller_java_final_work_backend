package com.petclinic.petclinic.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.petclinic.petclinic.models.Privilege;
import com.petclinic.petclinic.models.Role;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.models.constants.Privileges;
import com.petclinic.petclinic.models.constants.Roles;
import com.petclinic.petclinic.repositories.PrivilegeRepository;
import com.petclinic.petclinic.repositories.RoleRepository;
import com.petclinic.petclinic.repositories.UserConfigRepository;
import com.petclinic.petclinic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	private final UserRepository userRepository;

	private final UserConfigRepository userConfigRepository;

	private final RoleRepository roleRepository;

	private final PrivilegeRepository privilegeRepository;

	private final PasswordEncoder passwordEncoder;

	@Value("${admin.name}")
	private String adminName;

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	public InitialDataLoader(UserRepository userRepository, UserConfigRepository userConfigRepository, RoleRepository roleRepository,
							 PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userConfigRepository = userConfigRepository;
		this.roleRepository = roleRepository;
		this.privilegeRepository = privilegeRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (alreadySetup)
			return;

		Privilege readPrivilege	= createPrivilegeIfNotFound(Privileges.READ_PRIVILEGE.toString());
		Privilege writePrivilege = createPrivilegeIfNotFound(Privileges.WRITE_PRIVILEGE.toString());

		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
		createRoleIfNotFound(Roles.ROLE_ADMIN.toString(), adminPrivileges);
		createRoleIfNotFound(Roles.ROLE_OWNER_USER.toString(), Arrays.asList(readPrivilege));
		createRoleIfNotFound(Roles.ROLE_VET_USER.toString(), Arrays.asList(readPrivilege));

		Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN.toString());
		createUserIfNotFound(adminEmail, adminRole);
		alreadySetup = true;
	}

	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

	private void createUserIfNotFound(String adminEmail, Role adminRole) {
		Optional<User> optionalUser = userRepository.findByEmail(adminEmail);
		if(optionalUser.isEmpty()) {
			User user = new User();
			user.setFirstName(adminName);
			user.setLastName(adminName);
			user.setPassword(passwordEncoder.encode(adminPassword));
			user.setEmail(adminEmail);
			user.setPhone("080023646");
			user.setRoles(Arrays.asList(adminRole));
			user.setIsEnabled(true);
			UserConfig userConfig = new UserConfig(true, true, true);
			userConfigRepository.save(userConfig);
			user.setUserConfig(userConfig);
			userRepository.save(user);
		}
	}

}
