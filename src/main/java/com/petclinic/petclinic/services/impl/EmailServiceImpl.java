package com.petclinic.petclinic.services.impl;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.petclinic.petclinic.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Async
	@Override
	public void sendEmail(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		try {
			emailSender.send(message);
		} catch (MailException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Async
	@Override
	public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment("Invoice", file);

			emailSender.send(message);

		} catch (MessagingException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	// TODO send email with multiple attachments
	// https://www.quickprogrammingtips.com/spring-boot/how-to-send-email-from-spring-boot-applications.html
	// freemarker templates

}
