package com.petclinic.petclinic.services.impl;

import com.petclinic.petclinic.dtos.DiseaseDTO;
import com.petclinic.petclinic.models.Disease;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.repositories.DiseaseRepository;
import com.petclinic.petclinic.services.DiseaseService;
import com.petclinic.petclinic.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	PetService petService;

	@Autowired
	DiseaseRepository diseaseRepository;

	@Override
	public Disease addDisease(Long petId, DiseaseDTO diseaseDTO) {
		Pet pet = petService.getPetById(petId);
		Disease disease = new Disease();
		BeanUtils.copyProperties(diseaseDTO, disease);
		pet.addDisease(disease);
		return diseaseRepository.save(disease);
	}

	@Override
	public String deleteDisease(Long petId, Long diseaseId) {
		try {
			diseaseRepository.deleteById(diseaseId);
			return "The disease with id: " + diseaseId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The disease with id: " + diseaseId + " doesn't exists");
		}
	}
}
