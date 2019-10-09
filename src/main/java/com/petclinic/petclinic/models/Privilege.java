package com.petclinic.petclinic.models;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Privilege {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;

	public Privilege() {
	}

	public Privilege(@NotBlank String name) {
		this.name = name;
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Privilege privilege = (Privilege) o;

		if (id != null ? !id.equals(privilege.id) : privilege.id != null) return false;
		if (name != null ? !name.equals(privilege.name) : privilege.name != null) return false;
		return roles != null ? roles.equals(privilege.roles) : privilege.roles == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Privilege{" +
				"name='" + name + '\'' +
				'}';
	}
}
