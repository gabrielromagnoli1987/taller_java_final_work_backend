package com.petclinic.petclinic.controllers;

import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.DewormedDTO;
import com.petclinic.petclinic.models.Dewormed;
import com.petclinic.petclinic.services.DewormingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class DewormingController {

	@Autowired
	DewormingService dewormingService;

	@PostMapping(path = "/{petId}/deworming", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Dewormed> addDewormed(@PathVariable Long petId, @Valid @RequestBody DewormedDTO dewormedDTO) {
		Dewormed dewormed = dewormingService.addDewormed(petId, dewormedDTO);
		return new ResponseEntity<>(dewormed, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{petId}/deworming/{dewormedId}")
	public ResponseEntity<Map<String, String>> deleteDewormed(@PathVariable Long petId, @PathVariable Long dewormedId) {
		String message = dewormingService.deleteDewormed(petId, dewormedId);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
