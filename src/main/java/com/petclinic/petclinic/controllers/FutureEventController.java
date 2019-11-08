package com.petclinic.petclinic.controllers;

import java.security.Principal;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.FutureEventDTO;
import com.petclinic.petclinic.models.FutureEvent;
import com.petclinic.petclinic.services.FutureEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class FutureEventController {

	@Autowired
	FutureEventService futureEventService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FutureEvent> createFutureEvent(@Valid @RequestBody FutureEventDTO futureEventDTO, Principal principal) {
		FutureEvent createdFutureEvent = futureEventService.createFutureEvent(futureEventDTO, principal);
		return new ResponseEntity<>(createdFutureEvent, HttpStatus.CREATED);
	}
}
