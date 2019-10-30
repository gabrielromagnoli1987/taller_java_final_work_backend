package com.petclinic.petclinic.services;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.dtos.EnableVetDTO;
import com.petclinic.petclinic.dtos.UserDTO;
import com.petclinic.petclinic.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	Iterable<User> getAllUsers();

	Page<User> getUsers(Pageable pageable);

	Page<User> getVetUsers(Pageable pageable);

	Page<User> getActiveVetUsers(Pageable pageable);

	User getUserById(Long userId) throws EntityNotFoundException;

	User getUserByEmail(String userEmail) throws EntityNotFoundException;

	User createUser(UserDTO userDTO) throws EntityExistsException;

	User updateUser(UserDTO userDTO, Long userId) throws EntityNotFoundException, EntityExistsException;

	String deleteUserById(Long userId);

	User enableVetUser(Long userId, EnableVetDTO enableVetDTO);
}
