package com.petclinic.petclinic.exception;

public class OwnershipException extends Exception {
	public OwnershipException(String errorMessage) {
		super(errorMessage);
	}
	public OwnershipException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
