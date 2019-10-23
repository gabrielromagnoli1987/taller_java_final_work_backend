package com.petclinic.petclinic.services.impl;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.petclinic.petclinic.dtos.PetDTO;
import com.petclinic.petclinic.models.Image;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.repositories.PetRepository;
import com.petclinic.petclinic.services.ImageService;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	ImageService imageService;

	@Autowired
	UserService userService;

	@Autowired
	PetRepository petRepository;

	@Override
	public List<Pet> getAllPets() {
		return (List<Pet>) petRepository.findAll();
	}

	@Override
	public Pet createPet(PetDTO petDTO, MultipartFile[] files, Principal principal) throws IOException {
		Pet pet = dtoToEntity(petDTO);
		assignVetToPet(petDTO, pet);
		assignOwnerToPet(principal, pet);
		assignImagesToPet(files, pet);
		return petRepository.save(pet);
	}

	private Pet dtoToEntity(PetDTO petDTO) {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet, "vetId");
		return pet;
	}

	private void assignVetToPet(PetDTO petDTO, Pet pet) {
		if (petDTO.getVetId() != null) {
			User vet = userService.getUserById(petDTO.getVetId());
			if (vet.getIsVetEnabled() == null || (vet.getIsVetEnabled() != null && ! vet.getIsVetEnabled())) {
				throw new IllegalArgumentException("Wrong vet id: " + petDTO);
			}
			vet.addPetToVet(pet);
		}
	}

	private void assignOwnerToPet(Principal principal, Pet pet) {
		User owner = userService.getUserByEmail(principal.getName());
		pet.setOwner(owner);
	}

	private void assignImagesToPet(MultipartFile[] files, Pet pet) throws IOException {
		List<Image> images = imageService.createImages(files);
		for (Image image : images) {
			pet.addImage(image);
		}
	}
}
