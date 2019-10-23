package com.petclinic.petclinic.dtos;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class ReproductionDTO {

	@NotNull
	private LocalDateTime localDateTime;

	@NotNull
	private Integer numberOfpuppies;

	public ReproductionDTO() {

	}

	public ReproductionDTO(@NotNull LocalDateTime localDateTime, @NotNull Integer numberOfpuppies) {
		this.localDateTime = localDateTime;
		this.numberOfpuppies = numberOfpuppies;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Integer getNumberOfpuppies() {
		return numberOfpuppies;
	}

	public void setNumberOfpuppies(Integer numberOfpuppies) {
		this.numberOfpuppies = numberOfpuppies;
	}
}
