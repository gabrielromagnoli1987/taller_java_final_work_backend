package com.petclinic.petclinic.dtos;

public class PublicRecordDTO {

	private Long petId;
	private String petImageLink;
	private String petName;
	private Integer petAge;
	private Long ownerId;
	private String ownerName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhone;
	private Long vetId;
	private String vetName;
	private String vetLastName;
	private String vetEmail;
	private String vetPhone;

	public PublicRecordDTO() {
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public String getPetImageLink() {
		return petImageLink;
	}

	public void setPetImageLink(String petImageLink) {
		this.petImageLink = petImageLink;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public Long getVetId() {
		return vetId;
	}

	public void setVetId(Long vetId) {
		this.vetId = vetId;
	}

	public String getVetName() {
		return vetName;
	}

	public void setVetName(String vetName) {
		this.vetName = vetName;
	}

	public String getVetLastName() {
		return vetLastName;
	}

	public void setVetLastName(String vetLastName) {
		this.vetLastName = vetLastName;
	}

	public String getVetEmail() {
		return vetEmail;
	}

	public void setVetEmail(String vetEmail) {
		this.vetEmail = vetEmail;
	}

	public String getVetPhone() {
		return vetPhone;
	}

	public void setVetPhone(String vetPhone) {
		this.vetPhone = vetPhone;
	}
}
