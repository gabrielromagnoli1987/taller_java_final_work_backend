package com.petclinic.petclinic.services.impl;

import com.petclinic.petclinic.dtos.ReproductionDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.Reproduction;
import com.petclinic.petclinic.persistence.dao.ReproductionDAO;
import com.petclinic.petclinic.persistence.utils.DAOFactory;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.ReproductionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ReproductionServiceImpl implements ReproductionService {

	@Autowired
	PetService petService;

	ReproductionDAO reproductionDAO = DAOFactory.getReproductionDAO();

	public ReproductionServiceImpl() {
	}

	@Override
	public Reproduction addReproduction(Long petId, ReproductionDTO reproductionDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Reproduction reproduction = new Reproduction();
		BeanUtils.copyProperties(reproductionDTO, reproduction);
		pet.addReproduction(reproduction);
		return reproductionDAO.create(reproduction);
	}

	@Override
	public String deleteReproduction(Long petId, Long reproductionId, Principal principal) throws OwnershipException {
		try {
			Pet pet = petService.getPetById(petId);
			petService.canEditPet(pet, principal);
			Reproduction reproduction = new Reproduction();
			reproduction.setId(reproductionId);
			reproductionDAO.delete(reproduction);
			return "The reproduction with id: " + reproductionId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The reproduction with id: " + reproductionId + " doesn't exists");
		}
	}
}
