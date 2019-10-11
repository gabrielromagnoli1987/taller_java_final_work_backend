package com.petclinic.petclinic.services;

public interface EmailService {

	void sendEmail(String to, String subject, String text);

	void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment);

}
