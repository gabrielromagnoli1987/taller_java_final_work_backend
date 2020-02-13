package com.petclinic.petclinic.persistence.dao;

import com.petclinic.petclinic.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDAO extends GenericDAO<User> {

    Optional<User> findByEmail(String email);

    Page<User> findByIsVetEnabledIsNotNull(Pageable pageable);

    Page<User> findByIsVetEnabledTrue(Pageable pageable);

}
