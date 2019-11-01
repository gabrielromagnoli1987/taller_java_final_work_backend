package com.petclinic.petclinic.services.impl;

import java.security.Principal;

import com.petclinic.petclinic.dtos.VisitDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.Visit;
import com.petclinic.petclinic.repositories.VisitRepository;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.UserService;
import com.petclinic.petclinic.services.VisitService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	PetService petService;

	@Autowired
	UserService userService;

	@Autowired
	VisitRepository visitRepository;

	public VisitServiceImpl() {
	}

	@Override
	public Visit createVisit(Long petId, VisitDTO visitDTO, Principal principal) throws OwnershipException {
		Pet pet = petService.getPetById(petId);
		petService.canEditPet(pet, principal);
		Visit visit = new Visit();
		BeanUtils.copyProperties(visitDTO, visit, "vetId");
		assignVetToVisit(visitDTO, visit);
		pet.addVisit(visit);
		return visitRepository.save(visit);
	}

	private void assignVetToVisit(VisitDTO visitDTO, Visit visit) {
		if (visitDTO.getVetId() != null) {
			User vet = userService.getUserById(visitDTO.getVetId());
			if (vet.getIsVetEnabled() == null || (vet.getIsVetEnabled() != null && ! vet.getIsVetEnabled())) {
				throw new IllegalArgumentException("Wrong vet id: " + visitDTO.getVetId());
			}
			visit.setVet(vet);
		} else {
			throw new IllegalArgumentException("Visit cannot be created without vet id");
		}
	}
}
