package com.petclinic.petclinic.services;

import com.petclinic.petclinic.dtos.DiseaseDTO;
import com.petclinic.petclinic.models.Disease;

public interface DiseaseService {

	Disease addDisease(Long petId, DiseaseDTO diseaseDTO);

	String deleteDisease(Long petId, Long diseaseId);
}
