package com.petclinic.petclinic.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {

	@NotBlank
	private String name;
	@NotBlank
	private String lastname;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String phone;
	@NotBlank
	private String password;
	@NotNull
	private Boolean isVet;
	private String resume;
	private String address;

	public UserDTO() {

	}

	public UserDTO(String name, String lastname, String email, String phone, String password, Boolean isVet) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.isVet = isVet;
	}

	public UserDTO(String name, String lastname, String email, String phone, String password, Boolean isVet, String resume) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.isVet = isVet;
		this.resume = resume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isVet() {
		return isVet;
	}

	public void setIsVet(Boolean isVet) {
		this.isVet = isVet;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
