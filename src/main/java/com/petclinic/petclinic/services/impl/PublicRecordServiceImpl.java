package com.petclinic.petclinic.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import com.petclinic.petclinic.dtos.PublicRecordDTO;
import com.petclinic.petclinic.models.Pet;
import com.petclinic.petclinic.models.User;
import com.petclinic.petclinic.models.UserConfig;
import com.petclinic.petclinic.services.PetService;
import com.petclinic.petclinic.services.PublicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublicRecordServiceImpl implements PublicRecordService {

	@Autowired
	PetService petService;

	public PublicRecordServiceImpl() {
	}

	@Override
	public Page<PublicRecordDTO> getPublicRecords(Pageable pageable) {
		Page<Pet> pets = petService.getPets(pageable);
		List<PublicRecordDTO> publicRecords = pets.stream().map(pet -> {
			PublicRecordDTO publicRecordDTO = new PublicRecordDTO();
			fillPublicRecordDTO(pet, publicRecordDTO);
			return publicRecordDTO;
		}).collect(Collectors.toList());
		return new PageImpl<>(publicRecords, pageable, publicRecords.size());
	}

	private void fillPublicRecordDTO(Pet pet, PublicRecordDTO publicRecordDTO) {
		User owner = pet.getOwner();
		UserConfig ownerUserConfig = owner.getUserConfig();
		User vet = pet.getVets().get(pet.getVets().size() - 1);
		UserConfig vetUserConfig = vet.getUserConfig();
		publicRecordDTO.setPetId(pet.getId());
		publicRecordDTO.setPetImageLink(pet.getImages().size() > 0 ? pet.getImages().get(0).getPath() : "");
		publicRecordDTO.setPetName(pet.getName());
		publicRecordDTO.setPetAge(Period.between(pet.getBornDate(), LocalDate.now()).getYears());
		publicRecordDTO.setOwnerId(owner.getId());
		publicRecordDTO.setOwnerName(owner.getFirstName());
		publicRecordDTO.setOwnerLastName(ownerUserConfig.getShowLastname() ? owner.getLastName() : "");
		publicRecordDTO.setOwnerEmail(ownerUserConfig.getShowEmail() ? owner.getEmail() : "");
		publicRecordDTO.setOwnerPhone(ownerUserConfig.getShowPhone() ? owner.getPhone() : "");
		publicRecordDTO.setVetId(vet.getId());
		publicRecordDTO.setVetName(vet.getFirstName());
		publicRecordDTO.setVetLastName(vetUserConfig.getShowLastname() ? vet.getLastName() : "");
		publicRecordDTO.setVetEmail(vetUserConfig.getShowEmail() ? vet.getEmail() : "");
		publicRecordDTO.setVetPhone(vetUserConfig.getShowPhone() ? vet.getPhone() : "");
	}
}
