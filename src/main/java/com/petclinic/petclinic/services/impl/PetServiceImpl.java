package com.petclinic.petclinic.services.impl;

import com.petclinic.petclinic.services.ImageService;
import com.petclinic.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	ImageService imageService;
}
