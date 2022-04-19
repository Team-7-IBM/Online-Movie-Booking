package com.ibm.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {

	public void registerAdmin(String username, String password) throws Exception;

}
