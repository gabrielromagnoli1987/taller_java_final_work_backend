package com.petclinic.petclinic.services.impl;

import java.security.Principal;

import com.petclinic.petclinic.dtos.ReproductionDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.Reproduction;
import com.petclinic.petclinic.repositories.ReproductionRepository;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.ReproductionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ReproductionServiceImpl implements ReproductionService {

	@Autowired
	PetService petService;

	@Autowired
	ReproductionRepository reproductionRepository;

	public ReproductionServiceImpl() {
	}

	@Override
	public Reproduction addReproduction(Long petId, ReproductionDTO reproductionDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Reproduction reproduction = new Reproduction();
		BeanUtils.copyProperties(reproductionDTO, reproduction);
		pet.addReproduction(reproduction);
		return reproductionRepository.save(reproduction);
	}

	@Override
	public String deleteReproduction(Long petId, Long reproductionId, Principal principal) throws OwnershipException {
		try {
			Pet pet = petService.getPetById(petId);
			petService.canEditPet(pet, principal);
			reproductionRepository.deleteById(reproductionId);
			return "The reproduction with id: " + reproductionId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The reproduction with id: " + reproductionId + " doesn't exists");
		}
	}
}
