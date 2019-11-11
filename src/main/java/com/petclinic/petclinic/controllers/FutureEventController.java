package com.petclinic.petclinic.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import javax.validation.Valid;

import com.petclinic.petclinic.dtos.FutureEventDTO;
import com.petclinic.petclinic.models.FutureEvent;
import com.petclinic.petclinic.services.FutureEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@DeleteMapping(path = "/{futureEventId}")
	public ResponseEntity<Map<String, String>> deleteFutureEvent(@PathVariable Long futureEventId, Principal principal) {
		String message = futureEventService.deleteFutureEvent(futureEventId, principal);
		Map<String, String> map = Collections.singletonMap("message", message);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
