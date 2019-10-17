package com.petclinic.petclinic.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Disease {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private LocalDateTime localDateTime;

	@NotBlank
	private String description;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public Disease() {
	}

	public Disease(@NotNull LocalDateTime localDateTime, @NotBlank String description) {
		this.localDateTime = localDateTime;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@JsonIgnore
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Disease disease = (Disease) o;

		if (!Objects.equals(id, disease.id)) return false;
		if (!localDateTime.equals(disease.localDateTime)) return false;
		if (!Objects.equals(description, disease.description)) return false;
		return Objects.equals(pet, disease.pet);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + localDateTime.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (pet != null ? pet.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Disease{" +
				"description='" + description + '\'' +
				'}';
	}
}
