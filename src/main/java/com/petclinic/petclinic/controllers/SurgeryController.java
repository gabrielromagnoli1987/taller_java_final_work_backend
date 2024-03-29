package com.petclinic.petclinic.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.SurgeryDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Surgery;
import com.petclinic.petclinic.services.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class SurgeryController {

	@Autowired
	SurgeryService surgeryService;

	@PostMapping(path = "/{petId}/surgeries", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Surgery> addSurgery(@PathVariable Long petId, @Valid @RequestBody SurgeryDTO surgeryDTO, Principal principal) throws OwnershipException {
		Surgery surgery = surgeryService.addSurgery(petId, surgeryDTO, principal);
		return new ResponseEntity<>(surgery, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{petId}/surgeries/{surgeryId}")
	public ResponseEntity<Map<String, String>> deleteSurgery(@PathVariable Long petId, @PathVariable Long surgeryId, Principal principal) throws OwnershipException {
		String message = surgeryService.deleteSurgery(petId, surgeryId, principal);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
