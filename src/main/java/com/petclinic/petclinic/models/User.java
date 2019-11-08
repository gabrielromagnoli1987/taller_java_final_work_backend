package com.petclinic.petclinic.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@Email
	@Column(unique = true, nullable = false)
	private String email;

	@NotBlank
	private String phone;

	@NotBlank
	private String password;

	private String address;

	private String resume;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Pet> ownersPets = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "vets_pets",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"))
	private List<Pet> vetsPets = new ArrayList<>();

	@OneToMany(mappedBy = "vet", cascade = CascadeType.ALL)
	private List<Visit> visits = new ArrayList<>();

	@NotNull
	private Boolean isEnabled;

	private Boolean isVetEnabled;

	@ManyToMany
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles = new HashSet<>();

	@OneToOne
	private UserConfig userConfig;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<FutureEvent> futureEvents = new ArrayList<>();

	public User() {
	}

	public User(@NotBlank String firstName, @NotBlank String lastName, @NotBlank @Email String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<Pet> getOwnersPets() {
		return ownersPets;
	}

	public void setOwnersPets(List<Pet> ownersPets) {
		this.ownersPets = ownersPets;
	}

	public List<Pet> getVetsPets() {
		return vetsPets;
	}

	public void setVetsPets(List<Pet> vetsPets) {
		this.vetsPets = vetsPets;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public Boolean isEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsVetEnabled() {
		return isVetEnabled;
	}

	public void setIsVetEnabled(Boolean vetIsEnabled) {
		this.isVetEnabled = vetIsEnabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public UserConfig getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(UserConfig userConfig) {
		this.userConfig = userConfig;
	}

	public List<FutureEvent> getFutureEvents() {
		return futureEvents;
	}

	public void setFutureEvents(List<FutureEvent> futureEvents) {
		this.futureEvents = futureEvents;
	}

	public void addPetToVet(Pet pet) {
		vetsPets.add(pet);
		pet.getVets().add(this);
	}

	public void addFutureEventToUser(FutureEvent futureEvent) {
		this.futureEvents.add(futureEvent);
		futureEvent.setUser(this);
	}

	public void removeFutureEventFromUser(FutureEvent futureEvent) {
		this.futureEvents.remove(futureEvent);
		futureEvent.setUser(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return email.equals(user.email);
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
