package com.petclinic.petclinic.models;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FutureEvent {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String name;

	@NotNull
	private LocalDateTime localDateTime;

	@OneToOne(optional = false)
	private Pet pet;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String description;

	public FutureEvent() {
	}

	public FutureEvent(@NotEmpty String name, @NotNull LocalDateTime localDateTime, Pet pet) {
		this.name = name;
		this.localDateTime = localDateTime;
		this.pet = pet;
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

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FutureEvent that = (FutureEvent) o;

		if (!Objects.equals(name, that.name)) return false;
		return Objects.equals(localDateTime, that.localDateTime);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
		return result;
	}
}
