package com.petclinic.petclinic.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dewormed {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private LocalDateTime localDateTime;

	@NotBlank
	private String drug;

	private String result;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public Dewormed() {
	}

	public Dewormed(LocalDateTime localDateTime, String drug, String result) {
		this.localDateTime = localDateTime;
		this.drug = drug;
		this.result = result;
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

		Dewormed dewormed = (Dewormed) o;

		if (!Objects.equals(id, dewormed.id)) return false;
		if (!localDateTime.equals(dewormed.localDateTime)) return false;
		if (!Objects.equals(drug, dewormed.drug)) return false;
		if (!Objects.equals(result, dewormed.result)) return false;
		return Objects.equals(pet, dewormed.pet);
	}

	@Override
	public int hashCode() {
		int result1 = id != null ? id.hashCode() : 0;
		result1 = 31 * result1 + localDateTime.hashCode();
		result1 = 31 * result1 + (drug != null ? drug.hashCode() : 0);
		result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
		result1 = 31 * result1 + (pet != null ? pet.hashCode() : 0);
		return result1;
	}

	@Override
	public String toString() {
		return "Dewormed{" +
				"drug='" + drug + '\'' +
				", result='" + result + '\'' +
				'}';
	}
}
