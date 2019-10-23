package com.petclinic.petclinic.models;

import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Image {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "path cannot be empty")
	@Column(unique = true)
	private String path;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public Image() {
	}

	public Image(@NotBlank(message = "path cannot be empty") String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

		Image image = (Image) o;

		if (!Objects.equals(id, image.id)) return false;
		return Objects.equals(path, image.path);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (path != null ? path.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Image{" +
				"path='" + path + '\'' +
				'}';
	}
}
