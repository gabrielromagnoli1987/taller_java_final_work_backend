package com.petclinic.petclinic.services.impl;

import com.petclinic.petclinic.dtos.DiseaseDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Disease;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.persistence.dao.DiseaseDAO;
import com.petclinic.petclinic.persistence.utils.DAOFactory;
import com.petclinic.petclinic.services.DiseaseService;
import com.petclinic.petclinic.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	PetService petService;

	DiseaseDAO diseaseDAO = DAOFactory.getDiseaseDAO();

	public DiseaseServiceImpl() {
	}

	@Override
	public Disease addDisease(Long petId, DiseaseDTO diseaseDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Disease disease = new Disease();
		BeanUtils.copyProperties(diseaseDTO, disease);
		pet.addDisease(disease);
		return diseaseDAO.create(disease);
	}

	@Override
	public String deleteDisease(Long petId, Long diseaseId, Principal principal) throws OwnershipException {
		try {
			Pet pet = petService.getPetById(petId);
			petService.canEditPet(pet, principal);
			Disease disease = new Disease();
			disease.setId(diseaseId);
			diseaseDAO.delete(disease);
			return "The disease with id: " + diseaseId + " was deleted.";
		} catch (EmptyResultDataAccessException e) {
			throw new IllegalArgumentException("The disease with id: " + diseaseId + " doesn't exists");
		}
	}
}
