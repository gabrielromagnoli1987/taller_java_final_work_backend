package com.petclinic.petclinic.services.impl;

import java.security.Principal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.petclinic.petclinic.dtos.FutureEventDTO;
import com.petclinic.petclinic.exception.OwnershipException;
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

	@Override
	public String deleteFutureEvent(Long futureEventId, Principal principal) throws OwnershipException {
		Optional<FutureEvent> optionalFutureEvent = futureEventRepository.findById(futureEventId);
		FutureEvent futureEvent = optionalFutureEvent.orElseThrow(() -> new EntityNotFoundException("FutureEvent with id: " + futureEventId + " does not exists"));
		if (! futureEvent.getUser().getEmail().equals(principal.getName())) {
			throw new OwnershipException("That resource is not yours");
		}
		futureEventRepository.deleteById(futureEventId);
		return "FutureEvent with id: " + futureEventId + " was deleted";
	}
}
