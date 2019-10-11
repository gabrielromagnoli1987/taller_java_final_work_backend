package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Dewormed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DewormedRepository extends CrudRepository<Dewormed, Long> {
}
