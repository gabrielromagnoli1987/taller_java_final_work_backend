package com.petclinic.petclinic.dtos;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DiseaseDTO {

	@NotNull
	private LocalDateTime localDateTime;

	@NotBlank
	private String description;

	public DiseaseDTO() {

	}

	public DiseaseDTO(@NotNull LocalDateTime localDateTime, @NotBlank String description) {
		this.localDateTime = localDateTime;
		this.description = description;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
