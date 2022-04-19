package com.ibm.rest;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.pojo.Login;
import com.ibm.service.LoginService;

/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Login
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService logServ;

	/**
	 * verifies user name and password.
	 * 
	 * @param username
	 * @param password
	 * @return HTTP status
	 */
	@PostMapping("/login/{username}/{password}")
	public Login loginUser(@PathVariable String username, @PathVariable String password)throws Exception {
		Login login = new Login();
		try {
			login = logServ.loginWithData(username, password);
		} catch (Exception e) {
			logger.error("------------------LoginFailed---------------");
			JOptionPane.showMessageDialog(null, "Check Username/Password");
		}
		logger.info("-----------------Login Successful----------------");
		return login;
	}

	/**
	 * logs out the present user.
	 * 
	 * @return HTTP status
	 * @throws Exception
	 */
	@PostMapping("/Logout")
	public HttpStatus logOut() throws Exception {
		if (this.loginStatus()) {
			logServ.logoutPresentUser();
			return HttpStatus.ACCEPTED;
		} else {
			throw new Exception("User Not yet Lo gged In");
		}
	}

	/**
	 * Informs whether the user is logged in.
	 * 
	 * @return LoginStatus
	 */
	public boolean loginStatus() {
		return logServ.loginStatus();
	}

	/**
	 * Obtains the role of the user.
	 * 
	 * @return Role
	 */
	public String getRole() {
		return logServ.getRole();
	}

}