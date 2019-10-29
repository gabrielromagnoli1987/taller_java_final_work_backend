package com.petclinic.petclinic.services;

import com.petclinic.petclinic.dtos.PublicRecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicRecordService {
	Page<PublicRecordDTO> getPublicRecords(Pageable pageable);
}
