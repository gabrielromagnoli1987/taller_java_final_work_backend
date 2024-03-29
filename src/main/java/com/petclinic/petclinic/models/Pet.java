package com.petclinic.petclinic.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonIgnore
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

	public void addImage(Image image) {
		images.add(image);
		image.setPet(this);
	}

	public void removeImage(Image image) {
		images.remove(image);
		image.setPet(null);
	}

	public void addVaccine(Vaccine vaccine) {
		vaccines.add(vaccine);
		vaccine.setPet(this);
	}

	public void removeVaccine(Vaccine vaccine) {
		vaccines.remove(vaccine);
		vaccine.setPet(null);
	}

	public void addSurgery(Surgery surgery) {
		surgeries.add(surgery);
		surgery.setPet(this);
	}

	public void removeSurgery(Surgery surgery) {
		surgeries.remove(surgery);
		surgery.setPet(null);
	}

	public void addDisease(Disease disease) {
		diseases.add(disease);
		disease.setPet(this);
	}

	public void removeDisease(Disease disease) {
		diseases.remove(disease);
		disease.setPet(null);
	}

	public void addReproduction(Reproduction reproduction) {
		reproductiveHistory.add(reproduction);
		reproduction.setPet(this);
	}

	public void removeReproduction(Reproduction reproduction) {
		reproductiveHistory.remove(reproduction);
		reproduction.setPet(null);
	}

	public void addDewormed(Dewormed dewormed) {
		deworming.add(dewormed);
		dewormed.setPet(this);
	}

	public void removeDewormed(Dewormed dewormed) {
		deworming.remove(dewormed);
		dewormed.setPet(null);
	}

	public void addVisit(Visit visit) {
		visits.add(visit);
		visit.setPet(this);
	}

	public void removeVisit(Visit visit) {
		visits.remove(visit);
		visit.setPet(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pet pet = (Pet) o;

		if (!Objects.equals(id, pet.id)) return false;
		if (!Objects.equals(name, pet.name)) return false;
		if (!Objects.equals(bornDate, pet.bornDate)) return false;
		if (!Objects.equals(species, pet.species)) return false;
		if (!Objects.equals(race, pet.race)) return false;
		if (sex != pet.sex) return false;
		if (!Objects.equals(color, pet.color)) return false;
		return Objects.equals(observations, pet.observations);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (bornDate != null ? bornDate.hashCode() : 0);
		result = 31 * result + (species != null ? species.hashCode() : 0);
		result = 31 * result + (race != null ? race.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (color != null ? color.hashCode() : 0);
		result = 31 * result + (observations != null ? observations.hashCode() : 0);
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
