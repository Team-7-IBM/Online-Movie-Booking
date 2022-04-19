package com.ibm.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Customer;
import com.ibm.entity.User;
import com.ibm.exception.AccessForbiddenException;
import com.ibm.exception.CustomerNotFoundException;
import com.ibm.exception.UserCreationError;
import com.ibm.repo.CustomerRepository;
import com.ibm.service.UserService;

/**
 * @author Rohit Kumar Jha
 * @version 1.0
 * @category User
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * 
	 * @param user
	 * @return user
	 * @throws AccessForbiddenException
	 * @throws CustomerNotFoundException
	 * @throws UserCreationError
	 */
	@PostMapping("/adduser")
	public User addUser(@RequestBody User user)
			throws AccessForbiddenException, CustomerNotFoundException, UserCreationError {
		if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
			Customer customer = new Customer(user.getUsername(), null, null, null, user.getPassword());
			logger.info("-----------------Customer Added---------------------");
			customerRepository.saveAndFlush(customer);
			user.setCustomer(customer);
		}
		logger.info("-----------------User Added---------------------");
		return userService.addUser(user);
	}

	/**
	 * 
	 * @param user
	 * @return remove user
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/removeuser")
	public User removeUser(@RequestBody User user) throws AccessForbiddenException {
		logger.info("--------------------User Deleted------------------");
		return userService.removeUser(user);
	}

}
