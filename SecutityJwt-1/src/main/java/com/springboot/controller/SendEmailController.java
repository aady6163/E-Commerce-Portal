package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.SendEmailService;

@RestController
public class SendEmailController {
	
	@Autowired
	private SendEmailService sendEmailService;

	@GetMapping("/sendEmail/{receiveEmail}")
	public void sendEmail(@PathVariable("receiveEmail") String receiveEmail) {

		
		sendEmailService.sendEmail(receiveEmail);
	}
}
