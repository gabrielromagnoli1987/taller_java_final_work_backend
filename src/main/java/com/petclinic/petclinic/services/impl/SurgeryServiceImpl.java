package com.petclinic.petclinic.services.impl;

import java.security.Principal;

import com.petclinic.petclinic.dtos.SurgeryDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.Surgery;
import com.petclinic.petclinic.repositories.SurgeryRepository;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.SurgeryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SurgeryServiceImpl implements SurgeryService {

	@Autowired
	PetService petService;

	@Autowired
	SurgeryRepository surgeryRepository;

	public SurgeryServiceImpl() {
	}

	@Override
	public Surgery addSurgery(Long petId, SurgeryDTO surgeryDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Surgery surgery = new Surgery();
		BeanUtils.copyProperties(surgeryDTO, surgery);
		pet.addSurgery(surgery);
		return surgeryRepository.save(surgery);
	}

	@Override
	public String deleteSurgery(Long petId, Long surgeryId, Principal principal) throws OwnershipException {
		try {
			Pet pet = petService.getPetById(petId);
			petService.canEditPet(pet, principal);
			surgeryRepository.deleteById(surgeryId);
			return "The surgery with id: " + surgeryId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The surgery with id: " + surgeryId + " doesn't exists");
		}
	}
}
