package com.petclinic.petclinic.models;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Surgery {

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

	public Surgery() {
	}

	public Surgery(@NotNull LocalDateTime localDateTime, @NotBlank String description) {
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

		Surgery surgery = (Surgery) o;

		if (id != null ? !id.equals(surgery.id) : surgery.id != null) return false;
		if (!localDateTime.equals(surgery.localDateTime)) return false;
		if (description != null ? !description.equals(surgery.description) : surgery.description != null) return false;
		return pet != null ? pet.equals(surgery.pet) : surgery.pet == null;
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
		return "Surgery{" +
				"description='" + description + '\'' +
				'}';
	}
}
