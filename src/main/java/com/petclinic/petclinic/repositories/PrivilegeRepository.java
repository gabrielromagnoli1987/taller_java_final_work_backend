package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

	Privilege findByName(String name);

}
