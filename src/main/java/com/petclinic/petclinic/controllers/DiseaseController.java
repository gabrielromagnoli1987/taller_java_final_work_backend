package com.petclinic.petclinic.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.DiseaseDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Disease;
import com.petclinic.petclinic.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class DiseaseController {

	@Autowired
	DiseaseService diseaseService;

	@PostMapping(path = "/{petId}/diseases", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Disease> addDisease(@PathVariable Long petId, @Valid @RequestBody DiseaseDTO diseaseDTO, Principal principal) throws OwnershipException {
		Disease disease = diseaseService.addDisease(petId, diseaseDTO, principal);
		return new ResponseEntity<>(disease, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{petId}/diseases/{diseaseId}")
	public ResponseEntity<Map<String, String>> deleteDisease(@PathVariable Long petId, @PathVariable Long diseaseId, Principal principal) throws OwnershipException {
		String message = diseaseService.deleteDisease(petId, diseaseId, principal);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
