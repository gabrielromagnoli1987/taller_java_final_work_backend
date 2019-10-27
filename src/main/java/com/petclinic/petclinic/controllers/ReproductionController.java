package com.petclinic.petclinic.controllers;

import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.ReproductionDTO;
import com.petclinic.petclinic.models.Reproduction;
import com.petclinic.petclinic.services.ReproductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class ReproductionController {

	@Autowired
	ReproductionService reproductionService;

	@PostMapping(path = "/{petId}/reproductions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reproduction> addReproduction(@PathVariable Long petId, @Valid @RequestBody ReproductionDTO reproductionDTO) {
		Reproduction reproduction = reproductionService.addReproduction(petId, reproductionDTO);
		return new ResponseEntity<>(reproduction, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{petId}/reproductions/{reproductionId}")
	public ResponseEntity<Map<String, String>> deleteReproduction(@PathVariable Long petId, @PathVariable Long reproductionId) {
		String message = reproductionService.deleteReproduction(petId, reproductionId);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
