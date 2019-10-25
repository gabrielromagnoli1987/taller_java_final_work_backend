package com.petclinic.petclinic.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.dtos.PetDTO;
import com.petclinic.petclinic.dtos.VaccineDTO;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PetService {

	List<Pet> getAllPets();

	Page<Pet> getPets(Pageable pageable);

	Pet createPet(PetDTO petDTO, MultipartFile[] files, Principal principal) throws IOException;

	Pet getPetById(Long petId) throws EntityNotFoundException;

	Vaccine addVaccine(Long petId, VaccineDTO vaccineDTO);
}
