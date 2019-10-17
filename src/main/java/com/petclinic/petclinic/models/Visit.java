package com.petclinic.petclinic.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Visit {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private LocalDateTime localDateTime;

	private Double weight;

	@ManyToOne
	@JoinColumn(name = "vet_id")
	private User vet;

	private String reason;

	private String diagnosis;

	private String indications;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public Visit() {
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@JsonIgnore
	public User getVet() {
		return vet;
	}

	public void setVet(User vet) {
		this.vet = vet;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
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

		Visit visit = (Visit) o;

		if (!Objects.equals(id, visit.id)) return false;
		if (!localDateTime.equals(visit.localDateTime)) return false;
		if (!Objects.equals(weight, visit.weight)) return false;
		if (!Objects.equals(vet, visit.vet)) return false;
		if (!Objects.equals(reason, visit.reason)) return false;
		if (!Objects.equals(diagnosis, visit.diagnosis)) return false;
		if (!Objects.equals(indications, visit.indications)) return false;
		return Objects.equals(pet, visit.pet);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + localDateTime.hashCode();
		result = 31 * result + (weight != null ? weight.hashCode() : 0);
		result = 31 * result + (vet != null ? vet.hashCode() : 0);
		result = 31 * result + (reason != null ? reason.hashCode() : 0);
		result = 31 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
		result = 31 * result + (indications != null ? indications.hashCode() : 0);
		result = 31 * result + (pet != null ? pet.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Visit{" +
				"localDateTime=" + localDateTime +
				", reason='" + reason + '\'' +
				'}';
	}
}
