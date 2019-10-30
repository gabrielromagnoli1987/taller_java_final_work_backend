package com.petclinic.petclinic.services.impl;

import java.security.Principal;

import com.petclinic.petclinic.dtos.VaccineDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.Vaccine;
import com.petclinic.petclinic.repositories.VaccineRepository;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.VaccineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	PetService petService;

	@Autowired
	VaccineRepository vaccineRepository;

	public VaccineServiceImpl() {
	}

	@Override
	public Vaccine addVaccine(Long petId, VaccineDTO vaccineDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Vaccine vaccine = new Vaccine();
		BeanUtils.copyProperties(vaccineDTO, vaccine);
		pet.addVaccine(vaccine);
		return vaccineRepository.save(vaccine);
	}

	@Override
	public String deleteVaccine(Long petId, Long vaccineId, Principal principal) throws OwnershipException {
		try {
			Pet pet = petService.getPetById(petId);
			petService.canEditPet(pet, principal);
			vaccineRepository.deleteById(vaccineId);
			return "The vaccine with id: " + vaccineId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The vaccine with id: " + vaccineId + " doesn't exists");
		}
	}
}
