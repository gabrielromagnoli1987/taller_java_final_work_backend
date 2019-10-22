package com.petclinic.petclinic.dtos;

import javax.validation.constraints.NotNull;

public class EnableVetDTO {

	@NotNull
	private Boolean isEnable;

	public EnableVetDTO() {

	}

	public EnableVetDTO(@NotNull Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
}
