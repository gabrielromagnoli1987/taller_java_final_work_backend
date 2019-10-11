package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Disease;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends CrudRepository<Disease, Long> {
}
