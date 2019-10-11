package com.petclinic.petclinic.controllers;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
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
}