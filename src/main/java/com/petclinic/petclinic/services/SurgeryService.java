package com.petclinic.petclinic.services;

import com.petclinic.petclinic.dtos.SurgeryDTO;
import com.petclinic.petclinic.models.Surgery;

public interface SurgeryService {

	Surgery addSurgery(Long petId, SurgeryDTO surgeryDTO);

	String deleteSurgery(Long petId, Long surgeryId);
}
