package com.petclinic.petclinic.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Collection<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "roles_privileges",
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges = new HashSet<>();

	public Role() {
	}

	public Role(@NotBlank String name) {
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

	@JsonIgnore
	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (!Objects.equals(id, role.id)) return false;
		if (!Objects.equals(name, role.name)) return false;
		if (!Objects.equals(users, role.users)) return false;
		return Objects.equals(privileges, role.privileges);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (users != null ? users.hashCode() : 0);
		result = 31 * result + (privileges != null ? privileges.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Roles{" +
				"name='" + name + '\'' +
				'}';
	}
}
