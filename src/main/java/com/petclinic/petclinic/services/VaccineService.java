package com.petclinic.petclinic.services;

import com.petclinic.petclinic.dtos.VaccineDTO;
import com.petclinic.petclinic.models.Vaccine;

public interface VaccineService {

	Vaccine addVaccine(Long petId, VaccineDTO vaccineDTO);

	String deleteVaccine(Long petId, Long vaccineId);
}
