package com.petclinic.petclinic.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.PetDTO;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.constants.Constants;
import com.petclinic.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pets")
public class PetController {

	@Autowired
	PetService petService;

	@GetMapping("/all")
	public ResponseEntity<List<Pet>> getAllPets() {
		List<Pet> pets = petService.getAllPets();
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Secured(Constants.ROLE_OWNER_USER)
	public ResponseEntity<Pet> createPet(@Valid @RequestPart("petDTO") PetDTO petDTO, @RequestPart("file") MultipartFile[] files, Principal principal) throws IOException {
		Pet createdPet = petService.createPet(petDTO, files, principal);
		return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
	}
}
