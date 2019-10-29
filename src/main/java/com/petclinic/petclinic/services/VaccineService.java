package com.petclinic.petclinic.services;

import java.security.Principal;

import com.petclinic.petclinic.dtos.VaccineDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Vaccine;

public interface VaccineService {

	Vaccine addVaccine(Long petId, VaccineDTO vaccineDTO, Principal principal) throws OwnershipException;

	String deleteVaccine(Long petId, Long vaccineId, Principal principal) throws OwnershipException;
}
