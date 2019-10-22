package com.petclinic.petclinic.repositories;

import java.util.Optional;

import com.petclinic.petclinic.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
