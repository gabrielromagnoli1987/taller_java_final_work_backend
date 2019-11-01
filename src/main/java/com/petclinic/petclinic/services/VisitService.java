package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.VisitDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Visit;

public interface VisitService {
	Visit createVisit(Long petId, VisitDTO visitDTO, Principal principal) throws OwnershipException;
}
