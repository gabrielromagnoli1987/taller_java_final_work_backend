package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.UserConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfigRepository extends CrudRepository<UserConfig, Long> {
}
