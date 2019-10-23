package com.petclinic.petclinic.dtos;

public class UserConfigDTO {

	private Boolean showLastname;

	private Boolean showEmail;

	private Boolean showPhone;

	public UserConfigDTO() {
	}

	public UserConfigDTO(Boolean showLastname, Boolean showEmail, Boolean showPhone) {
		this.showLastname = showLastname;
		this.showEmail = showEmail;
		this.showPhone = showPhone;
	}

	public Boolean getShowLastname() {
		return showLastname;
	}

	public void setShowLastname(Boolean showLastname) {
		this.showLastname = showLastname;
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
