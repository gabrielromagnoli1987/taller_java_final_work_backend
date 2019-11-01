package com.petclinic.petclinic.dtos;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class VisitDTO {

	@NotNull
	private LocalDateTime localDateTime;

	private Double weight;

	private String reason;

	private String diagnosis;

	private String indications;

	@NotNull
	private Long vetId;

	public VisitDTO() {

	}

	public VisitDTO(@NotNull LocalDateTime localDateTime, Double weight, String reason, String diagnosis, String indications) {
		this.localDateTime = localDateTime;
		this.weight = weight;
		this.reason = reason;
		this.diagnosis = diagnosis;
		this.indications = indications;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public Long getVetId() {
		return vetId;
	}

	public void setVetId(Long vetId) {
		this.vetId = vetId;
	}
}
