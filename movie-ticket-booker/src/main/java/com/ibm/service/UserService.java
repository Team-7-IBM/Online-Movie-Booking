package com.ibm.service;

import com.ibm.entity.User;
import com.ibm.exception.UserCreationError;

public interface UserService {

	public User addUser(User user) throws UserCreationError;

	public User removeUser(User user);

}
