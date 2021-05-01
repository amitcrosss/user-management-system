package com.example.userManagementSystem.service;

import java.time.LocalDate;
import java.util.List;

import com.example.userManagementSystem.entity.Auth_User;

public interface AdminService {
	
	public boolean isValidAdmin(String userName, String password) throws Exception;

	public Auth_User createUser(String userName, String email, String firstName, String lastName, LocalDate dateOfBirth) throws Exception;

	public List<String> fetchAllUsername();

	public Auth_User fetchAllUserDetails(String username) throws Exception;

}
