package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.ReproductionDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Reproduction;

public interface ReproductionService {
	
	Reproduction addReproduction(Long petId, ReproductionDTO reproductionDTO, Principal principal) throws OwnershipException;

	String deleteReproduction(Long petId, Long reproductionId, Principal principal) throws OwnershipException;
}
