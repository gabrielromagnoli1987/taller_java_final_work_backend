package com.petclinic.petclinic.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.petclinic.petclinic.models.constants.Sex;

@Entity
public class Pet {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private LocalDate bornDate;

	private String species;

	private String race;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	private String color;

	private String observations;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonBackReference
	private User owner;

	@ManyToMany(mappedBy = "vetsPets")
	private List<User> vets = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Vaccine> vaccines = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Surgery> surgeries = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Disease> diseases = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Reproduction> reproductiveHistory = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Dewormed> deworming = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Visit> visits = new ArrayList<>();

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Image> images = new ArrayList<>();

	public Pet() {

	}

	public Pet(@NotBlank String name, @NotNull LocalDate bornDate) {
		this.name = name;
		this.bornDate = bornDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getVets() {
		return vets;
	}

	public void setVets(List<User> vets) {
		this.vets = vets;
	}

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}

	public List<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	public List<Reproduction> getReproductiveHistory() {
		return reproductiveHistory;
	}

	public void setReproductiveHistory(List<Reproduction> reproductiveHistory) {
		this.reproductiveHistory = reproductiveHistory;
	}

	public List<Dewormed> getDeworming() {
		return deworming;
	}

	public void setDeworming(List<Dewormed> deworming) {
		this.deworming = deworming;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pet pet = (Pet) o;

		if (id != null ? !id.equals(pet.id) : pet.id != null) return false;
		if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
		if (!bornDate.equals(pet.bornDate)) return false;
		if (species != null ? !species.equals(pet.species) : pet.species != null) return false;
		if (race != null ? !race.equals(pet.race) : pet.race != null) return false;
		if (sex != pet.sex) return false;
		if (color != null ? !color.equals(pet.color) : pet.color != null) return false;
		if (observations != null ? !observations.equals(pet.observations) : pet.observations != null) return false;
		if (owner != null ? !owner.equals(pet.owner) : pet.owner != null) return false;
		if (vets != null ? !vets.equals(pet.vets) : pet.vets != null) return false;
		if (vaccines != null ? !vaccines.equals(pet.vaccines) : pet.vaccines != null) return false;
		if (surgeries != null ? !surgeries.equals(pet.surgeries) : pet.surgeries != null) return false;
		if (diseases != null ? !diseases.equals(pet.diseases) : pet.diseases != null) return false;
		if (reproductiveHistory != null ? !reproductiveHistory.equals(pet.reproductiveHistory) : pet.reproductiveHistory != null)
			return false;
		if (deworming != null ? !deworming.equals(pet.deworming) : pet.deworming != null) return false;
		return visits != null ? visits.equals(pet.visits) : pet.visits == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + bornDate.hashCode();
		result = 31 * result + (species != null ? species.hashCode() : 0);
		result = 31 * result + (race != null ? race.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (color != null ? color.hashCode() : 0);
		result = 31 * result + (observations != null ? observations.hashCode() : 0);
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (vets != null ? vets.hashCode() : 0);
		result = 31 * result + (vaccines != null ? vaccines.hashCode() : 0);
		result = 31 * result + (surgeries != null ? surgeries.hashCode() : 0);
		result = 31 * result + (diseases != null ? diseases.hashCode() : 0);
		result = 31 * result + (reproductiveHistory != null ? reproductiveHistory.hashCode() : 0);
		result = 31 * result + (deworming != null ? deworming.hashCode() : 0);
		result = 31 * result + (visits != null ? visits.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Pet{" +
				"name='" + name + '\'' +
				", bornDate=" + bornDate +
				'}';
	}
}
