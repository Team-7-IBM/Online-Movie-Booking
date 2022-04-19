package com.ibm.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Customer;
import com.ibm.service.EmailSenderService;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	@Lazy
	@Autowired
	private EmailSenderService service;

	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@GetMapping("/send")
	public void triggerMail(@RequestParam Customer cust) {
		service.sendSimpleEmail(cust.getEmail(), "Dear Customer,\r\n"
				+ "This mail signifies that your email-id has been verified. \r\n"
				+ "Welcome to Cinebook, enjoy the movie booking experience.", "Hello from Cinebook");
		logger.info("Successfull");
	}
	
	@GetMapping("/sendticket")
	public void ticketMail(@RequestParam Customer cust) {
		service.sendSimpleEmail(cust.getEmail(), "Ticket confirmed ",cust.getCustomerName() + " Your booking is confirmed");
		logger.info("Successfull");
	}
}
