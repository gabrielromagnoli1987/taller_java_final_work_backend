package com.petclinic.petclinic.dtos;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petclinic.petclinic.models.constants.Sex;

public class PetDTO {

	@NotBlank
	private String name;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate bornDate;

	private String species;

	private String race;

	private Sex sex;

	private String color;

	private String observations;

	private Long vetId;

	public PetDTO() {
	}

	public PetDTO(@NotBlank String name, @NotNull LocalDate bornDate, String species, String race, Sex sex, String color, String observations, Long vetId) {
		this.name = name;
		this.bornDate = bornDate;
		this.species = species;
		this.race = race;
		this.sex = sex;
		this.color = color;
		this.observations = observations;
		this.vetId = vetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBornDate() {
		return bornDate;
	}

	public void setBornDate(LocalDate bornDate) {
		this.bornDate = bornDate;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getVetId() {
		return vetId;
	}

	public void setVetId(Long vetId) {
		this.vetId = vetId;
	}
}
