package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Surgery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurgeryRepository extends CrudRepository<Surgery, Long> {
}
