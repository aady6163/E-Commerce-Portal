package com.springboot.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String receiveEmail) {
		try {
		MimeMessage message =	javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("adarshbhosale1993@gmail.com");
		helper.setTo(receiveEmail);
		helper.setSubject("We've Received Your Query");
		helper.setText("Dear User,\n\n"
	            + "I hope this email finds you well.\n\n"
	            + "I'm writing to inform you that we've successfully received your query. Thank you for reaching out to us.\n\n"
	            + "Our team is currently reviewing your inquiry and will get back to you as soon as possible with a response or any further information needed.\n\n"
	            + "If you have any urgent concerns or additional details to add, please feel free to reply to this email, and we'll prioritize your request accordingly.\n\n"
	            + "Thank you for your patience and understanding.\n\n"
	            + "Best regards,\n"
	            + "[Aadarsh Bhosake]\n"
	            + "[Developer This Website]\n"
	            );
		
		javaMailSender.send(message);
		
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
