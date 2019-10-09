package com.petclinic.petclinic.models;

import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;

	@ManyToMany
	@JoinTable(
			name = "roles_privileges",
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;

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

		if (id != null ? !id.equals(role.id) : role.id != null) return false;
		if (name != null ? !name.equals(role.name) : role.name != null) return false;
		if (users != null ? !users.equals(role.users) : role.users != null) return false;
		return privileges != null ? privileges.equals(role.privileges) : role.privileges == null;
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
		return "Role{" +
				"name='" + name + '\'' +
				'}';
	}
}
