package com.petclinic.petclinic.dtos;

public class UserConfigDTO {

	private Boolean showLastName;

	private Boolean showEmail;

	private Boolean showPhone;

	public UserConfigDTO() {
	}

	public UserConfigDTO(Boolean showLastName, Boolean showEmail, Boolean showPhone) {
		this.showLastName = showLastName;
		this.showEmail = showEmail;
		this.showPhone = showPhone;
	}

	public Boolean getShowLastName() {
		return showLastName;
	}

	public void setShowLastName(Boolean showLastName) {
		this.showLastName = showLastName;
	}

	public Boolean getShowEmail() {
		return showEmail;
	}

	public void setShowEmail(Boolean showEmail) {
		this.showEmail = showEmail;
	}

	public Boolean getShowPhone() {
		return showPhone;
	}

	public void setShowPhone(Boolean showPhone) {
		this.showPhone = showPhone;
	}
}
