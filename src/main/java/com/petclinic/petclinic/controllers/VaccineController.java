package com.petclinic.petclinic.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.VaccineDTO;
import com.petclinic.petclinic.exception.OwnershipException;
import com.petclinic.petclinic.models.Vaccine;
import com.petclinic.petclinic.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class VaccineController {

	@Autowired
	VaccineService vaccineService;

	@PostMapping(path = "/{petId}/vaccines", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vaccine> addVaccine(@PathVariable Long petId, @Valid @RequestBody VaccineDTO vaccineDTO, Principal principal) throws OwnershipException {
		Vaccine vaccine = vaccineService.addVaccine(petId, vaccineDTO, principal);
		return new ResponseEntity<>(vaccine, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{petId}/vaccines/{vaccineId}")
	public ResponseEntity<Map<String, String>> deleteVaccine(@PathVariable Long petId, @PathVariable Long vaccineId, Principal principal) throws OwnershipException {
		String message = vaccineService.deleteVaccine(petId, vaccineId, principal);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
