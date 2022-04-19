package com.ibm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.User;
import com.ibm.exception.UserCreationError;
import com.ibm.repo.QueryClass;
import com.ibm.repo.UserRepository;
import com.ibm.validator.InputValidator;

/**
 * @author Rohit Kumar Jha
 * @version 1.0
 * @category User
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepo;

	@Autowired
	InputValidator validate;

	@Autowired
	QueryClass qcp;

	/**
	 * Add user object in database
	 * 
	 * @param user
	 * @return user object
	 * @throws UserCreationError
	 */
	@Override
	public User addUser(User user) throws UserCreationError {
		if (!validate.usernameValidator(user.getUsername()))
			throw new UserCreationError("Check Username !!!!");
		if (!validate.passwordValidator(user.getPassword()))
			throw new UserCreationError("Cannot register this User with this password");
		return userrepo.saveAndFlush(user);
	}

	/**
	 * Remove user object in database
	 * 
	 * @param user
	 * @return user object
	 */

	@Override
	public User removeUser(User user) {
		userrepo.delete(user);
		return user;
	}

}
