package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Vaccine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends CrudRepository<Vaccine, Long> {
}
