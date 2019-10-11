package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Reproduction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReproductionRepository extends CrudRepository<Reproduction, Long> {
}
