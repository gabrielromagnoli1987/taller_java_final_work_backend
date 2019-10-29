package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.DiseaseDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Disease;

public interface DiseaseService {

	Disease addDisease(Long petId, DiseaseDTO diseaseDTO, Principal principal) throws OwnershipException;

	String deleteDisease(Long petId, Long diseaseId, Principal principal) throws OwnershipException;
}
