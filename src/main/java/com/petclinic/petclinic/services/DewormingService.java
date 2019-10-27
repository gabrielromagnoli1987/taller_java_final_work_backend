package com.petclinic.petclinic.services;

import com.petclinic.petclinic.dtos.DewormedDTO;
import com.petclinic.petclinic.models.Dewormed;

public interface DewormingService {

	Dewormed addDewormed(Long petId, DewormedDTO dewormedDTO);

	String deleteDewormed(Long petId, Long dewormedId);
}
