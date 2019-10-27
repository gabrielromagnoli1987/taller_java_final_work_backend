package com.petclinic.petclinic.services;

import com.petclinic.petclinic.dtos.ReproductionDTO;
import com.petclinic.petclinic.models.Reproduction;

public interface ReproductionService {
	
	Reproduction addReproduction(Long petId, ReproductionDTO reproductionDTO);

	String deleteReproduction(Long petId, Long reproductionId);
}
