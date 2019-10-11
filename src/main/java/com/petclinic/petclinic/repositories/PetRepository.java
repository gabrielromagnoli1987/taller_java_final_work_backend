package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends PagingAndSortingRepository<Pet, Long> {
}
