package com.petclinic.petclinic.services.impl;

import java.security.Principal;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import com.petclinic.petclinic.dtos.FutureEventDTO;
import com.petclinic.petclinic.models.FutureEvent;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.repositories.FutureEventRepository;
import com.petclinic.petclinic.services.FutureEventService;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutureEventServiceImpl implements FutureEventService {

	@Autowired
	PetService petService;

	@Autowired
	UserService userService;

	@Autowired
	FutureEventRepository futureEventRepository;

	@Override
	public FutureEvent createFutureEvent(FutureEventDTO futureEventDTO, Principal principal) {
		if (futureEventDTO.getLocalDateTime().isBefore(LocalDateTime.now())) {
			throw new DateTimeException("The date time must be future");
		}
		Pet pet = petService.getPetById(futureEventDTO.getPetId());
		User user = userService.getUserByEmail(principal.getName());
		FutureEvent futureEvent = new FutureEvent();
		BeanUtils.copyProperties(futureEventDTO, futureEvent, "petId");
		futureEvent.setPet(pet);
		user.addFutureEventToUser(futureEvent);
		return futureEventRepository.save(futureEvent);
	}
}
