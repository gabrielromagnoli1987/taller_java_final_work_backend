package com.petclinic.petclinic.controllers;

import com.petclinic.petclinic.dtos.PublicRecordDTO;
import com.petclinic.petclinic.services.PublicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public-records")
public class PublicRecordController {

	@Autowired
	PublicRecordService publicRecordService;

	@GetMapping
	public ResponseEntity<Page<PublicRecordDTO>> getPublicRecords(Pageable pageable) {
		Page<PublicRecordDTO> publicRecords = publicRecordService.getPublicRecords(pageable);
		return new ResponseEntity<>(publicRecords, HttpStatus.OK);
	}
}
