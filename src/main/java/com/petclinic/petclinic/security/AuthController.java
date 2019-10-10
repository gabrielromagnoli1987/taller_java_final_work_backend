package com.petclinic.petclinic.security;

import java.util.Collections;
import java.util.Map;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.LoginDTO;
import com.petclinic.petclinic.dtos.UserDTO;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;


	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getEmail(),
						loginDTO.getPassword()
				)
		);

		String token = jwtTokenUtil.generateToken(authentication.getName(), authentication.getAuthorities());

		Map<String, String> map = Collections.singletonMap("token", token);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}


	/**
	 * @param userDTO
	 * @return
	 * @throws EntityExistsException Sign up. Creates a new user.
	 */
	@PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createCustomer(@Valid @RequestBody UserDTO userDTO) throws EntityExistsException {
		User createdUser = userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

}
