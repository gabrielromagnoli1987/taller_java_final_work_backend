package com.petclinic.petclinic.repositories;

import com.petclinic.petclinic.models.FutureEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureEventRepository extends CrudRepository<FutureEvent, Long> {
}
