package com.petclinic.petclinic.models.constants;

public enum Roles {
	ROLE_ADMIN(Constants.ROLE_ADMIN),
	ROLE_OWNER_USER(Constants.ROLE_OWNER_USER),
	ROLE_VET_USER(Constants.ROLE_VET_USER);

	private final String role;

	Roles(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}
}
