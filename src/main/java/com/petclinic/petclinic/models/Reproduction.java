package com.petclinic.petclinic.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reproduction {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private LocalDateTime localDateTime;

	@NotNull
	private Integer numberOfpuppies;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public Reproduction() {
	}

	public Reproduction(@NotNull LocalDateTime localDateTime, @NotNull Integer numberOfpuppies) {
		this.localDateTime = localDateTime;
		this.numberOfpuppies = numberOfpuppies;
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

	public Integer getNumberOfpuppies() {
		return numberOfpuppies;
	}

	public void setNumberOfpuppies(Integer numberOfpuppies) {
		this.numberOfpuppies = numberOfpuppies;
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

		Reproduction that = (Reproduction) o;

		if (!Objects.equals(id, that.id)) return false;
		if (!localDateTime.equals(that.localDateTime)) return false;
		if (!numberOfpuppies.equals(that.numberOfpuppies)) return false;
		return Objects.equals(pet, that.pet);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + localDateTime.hashCode();
		result = 31 * result + numberOfpuppies.hashCode();
		result = 31 * result + (pet != null ? pet.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Reproduction{" +
				"localDateTime=" + localDateTime +
				", numberOfpuppies=" + numberOfpuppies +
				'}';
	}
}
