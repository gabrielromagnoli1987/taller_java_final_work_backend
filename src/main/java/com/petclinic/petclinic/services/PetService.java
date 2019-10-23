package com.petclinic.petclinic.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.petclinic.petclinic.dtos.PetDTO;
import com.petclinic.petclinic.models.Pet;
import org.springframework.web.multipart.MultipartFile;

public interface PetService {

	List<Pet> getAllPets();

	Pet createPet(PetDTO petDTO, MultipartFile[] files, Principal principal) throws IOException;

}
