package com.petclinic.petclinic.controllers;

import java.security.Principal;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.VisitDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Visit;
import com.petclinic.petclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class VisitController {

	@Autowired
	VisitService visitService;

	@PostMapping(path = "/{petId}/visits", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Visit> createVisit(@PathVariable Long petId, @Valid @RequestBody VisitDTO visitDTO, Principal principal) throws OwnershipException {
		Visit createdVisit = visitService.createVisit(petId, visitDTO, principal);
		return new ResponseEntity<>(createdVisit, HttpStatus.CREATED);
	}
}
