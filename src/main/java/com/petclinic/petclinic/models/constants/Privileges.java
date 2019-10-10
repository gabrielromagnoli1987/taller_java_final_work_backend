package com.petclinic.petclinic.models.constants;

public enum Privileges {
	READ_PRIVILEGE("READ_PRIVILEGE"),
	WRITE_PRIVILEGE("WRITE_PRIVILEGE");

	private final String privilege;

	Privileges(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return privilege;
	}
}
