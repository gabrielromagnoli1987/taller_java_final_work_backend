package com.petclinic.petclinic.models.constants;

public enum Privileges {
	READ_PRIVILEGE(Constants.READ_PRIVILEGE),
	WRITE_PRIVILEGE(Constants.WRITE_PRIVILEGE);

	private final String privilege;

	Privileges(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return privilege;
	}
}
