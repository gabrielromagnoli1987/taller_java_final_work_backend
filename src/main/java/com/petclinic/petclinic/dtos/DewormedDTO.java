package com.petclinic.petclinic.dtos;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DewormedDTO {

	@NotNull
	private LocalDateTime localDateTime;

	@NotBlank
	private String drug;

	private String result;

	public DewormedDTO(){

	}

	public DewormedDTO(@NotNull LocalDateTime localDateTime, @NotBlank String drug, String result) {
		this.localDateTime = localDateTime;
		this.drug = drug;
		this.result = result;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
