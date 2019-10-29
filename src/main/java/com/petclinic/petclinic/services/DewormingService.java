package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.DewormedDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Dewormed;

public interface DewormingService {

	Dewormed addDewormed(Long petId, DewormedDTO dewormedDTO, Principal principal) throws OwnershipException;

	String deleteDewormed(Long petId, Long dewormedId, Principal principal) throws OwnershipException;
}
