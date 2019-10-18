package com.petclinic.petclinic.repositories;

import java.util.Collection;

import com.petclinic.petclinic.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

	Collection<Role> findByUsers_Id(Long userId);

}
