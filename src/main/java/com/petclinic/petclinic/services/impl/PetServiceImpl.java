package com.petclinic.petclinic.services.impl;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.dtos.PetDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Image;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.repositories.PetRepository;
import com.petclinic.petclinic.services.ImageService;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public PetServiceImpl() {
	}

	@Override
	public List<Pet> getAllPets() {
		return (List<Pet>) petRepository.findAll();
	}

	@Override
	public Page<Pet> getPets(Pageable pageable) {
		return petRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
	}

	@Override
	public Pet createPet(PetDTO petDTO, MultipartFile[] files, Principal principal) throws IOException {
		Pet pet = new Pet();
		BeanUtils.copyProperties(petDTO, pet, "vetId");
		assignVetToPet(petDTO, pet);
		assignOwnerToPet(principal, pet);
		assignImagesToPet(files, pet);
		return petRepository.save(pet);
	}

	@Override
	public Pet getPetById(Long petId) throws EntityNotFoundException {
		Optional<Pet> pet = petRepository.findById(petId);
		return pet.orElseThrow(() -> new EntityNotFoundException("Pet with id: " + petId + " does not exists"));
	}

	@Override
	public Boolean canEditPet(Pet pet, Principal principal) throws OwnershipException {
		if (isPrincipalPetsOwner(pet, principal)) return Boolean.TRUE;
		if (isPrincipalPetsVet(pet, principal)) return Boolean.TRUE;
		throw new OwnershipException("The pet with id: " + pet.getId() + " is not yours");
	}

	@Override
	public Pet updatePet(Long petId, PetDTO petDTO, MultipartFile[] files, Principal principal) throws IOException {
		Pet pet = getPetById(petId);
		isPrincipalPetsOwner(pet, principal);
		BeanUtils.copyProperties(petDTO, pet, "vetId");
		assignVetToPet(petDTO, pet);
		assignImagesToPet(files, pet);
		return petRepository.save(pet);
	}

	private boolean isPrincipalPetsOwner(Pet pet, Principal principal) {
		return pet.getOwner().getEmail().equals(principal.getName());
	}

	private boolean isPrincipalPetsVet(Pet pet, Principal principal) {
		User user = userService.getUserByEmail(principal.getName());
		return pet.getVets().contains(user);
	}

	private void assignVetToPet(PetDTO petDTO, Pet pet) {
		if (petDTO.getVetId() != null) {
			User vet = userService.getUserById(petDTO.getVetId());
			if (vet.getIsVetEnabled() == null || (vet.getIsVetEnabled() != null && ! vet.getIsVetEnabled())) {
				throw new IllegalArgumentException("Wrong vet id: " + petDTO.getVetId());
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
