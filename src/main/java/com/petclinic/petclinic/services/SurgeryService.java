package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.SurgeryDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Surgery;

public interface SurgeryService {

	Surgery addSurgery(Long petId, SurgeryDTO surgeryDTO, Principal principal) throws OwnershipException;

	String deleteSurgery(Long petId, Long surgeryId, Principal principal) throws OwnershipException;
}
