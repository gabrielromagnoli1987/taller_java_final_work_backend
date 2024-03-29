package com.petclinic.petclinic.services.impl;

import java.security.Principal;

import com.petclinic.petclinic.dtos.DewormedDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Dewormed;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.repositories.DewormedRepository;
import com.petclinic.petclinic.services.DewormingService;
import com.petclinic.petclinic.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DewormingServiceImpl implements DewormingService {

	@Autowired
	PetService petService;

	@Autowired
	DewormedRepository dewormedRepository;

	public DewormingServiceImpl() {
	}

	@Override
	public Dewormed addDewormed(Long petId, DewormedDTO dewormedDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Dewormed dewormed = new Dewormed();
		BeanUtils.copyProperties(dewormedDTO, dewormed);
		pet.addDewormed(dewormed);
		return dewormedRepository.save(dewormed);
	}

	@Override
	public String deleteDewormed(Long petId, Long dewormedId, Principal principal) throws OwnershipException {
		try {
			Pet pet = petService.getPetById(petId);
			petService.canEditPet(pet, principal);
			dewormedRepository.deleteById(dewormedId);
			return "The dewormed with id: " + dewormedId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The dewormed with id: " + dewormedId + " doesn't exists");
		}
	}
}
