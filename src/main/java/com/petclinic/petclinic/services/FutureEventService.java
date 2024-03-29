package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.FutureEventDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.FutureEvent;

public interface FutureEventService {

	FutureEvent createFutureEvent(FutureEventDTO futureEventDTO, Principal principal);

	String deleteFutureEvent(Long futureEventId, Principal principal) throws OwnershipException;
}
