package com.petclinic.petclinic.controllers;

import java.security.Principal;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.PetDTO;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pets")
public class PetController {

	@Autowired
	PetService petService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO petDTO, @RequestParam("file") MultipartFile[] files, Principal principal) {
		principal.getName();
		return null;
//		Pet createdPet = petService.createPet(petDTO, files, principal);
//		return new ResponseEntity<>(createdPet, HttpStatus.CREATED;
	}
}
