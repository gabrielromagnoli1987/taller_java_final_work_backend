package com.petclinic.petclinic.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.EnableVetDTO;
import com.petclinic.petclinic.dtos.UserConfigDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.models.constants.Constants;
import com.petclinic.petclinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = (List<User>) userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// use with: ?page=0&size=1
	@GetMapping
	public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
		Page<User> users = userService.getUsers(pageable);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/vets")
	@Secured(Constants.ROLE_ADMIN)
	public ResponseEntity<Page<User>> getVetUsers(Pageable pageable) {
		Page<User> users = userService.getVetUsers(pageable);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/vets-active")
	public ResponseEntity<Page<User>> getActiveVetUsers(Pageable pageable) {
		Page<User> users = userService.getActiveVetUsers(pageable);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws EntityNotFoundException {
		User user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/search/findByEmail")
	public ResponseEntity<User> getUserByEmail(@Param("email") String email) throws EntityNotFoundException {
		User user = userService.getUserByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	@Secured(Constants.ROLE_ADMIN)
	public ResponseEntity<Map<String, String>> deleteUserById(@PathVariable Long userId) throws EntityNotFoundException {
		String message = userService.deleteUserById(userId);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PatchMapping(path = "/{userId}/enable-vet", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Secured(Constants.ROLE_ADMIN)
	public ResponseEntity<User> enableVetUser(@PathVariable Long userId, @Valid @RequestBody EnableVetDTO enableVetDTO) throws EntityNotFoundException {
		User user = userService.enableVetUser(userId, enableVetDTO);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}/update-config", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserConfig> updateUserConfig(@PathVariable Long userId, @Valid @RequestBody UserConfigDTO userConfigDTO, Principal principal) throws EntityNotFoundException, OwnershipException {
		UserConfig userConfig = userService.updateUserConfig(userId, userConfigDTO, principal);
		return new ResponseEntity<>(userConfig, HttpStatus.OK);
	}
}
