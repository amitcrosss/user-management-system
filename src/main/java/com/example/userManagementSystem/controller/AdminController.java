package com.example.userManagementSystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userManagementSystem.entity.Auth_User;
import com.example.userManagementSystem.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/admin/createUser")
	public ResponseEntity<String> createUser(@RequestBody Map<String, String> body) {
		Auth_User authUser;

		String adminUsername = body.get("username");
		String password = body.get("password");

		String newUserName = body.get("newUsername");
		String email = body.get("email");
		String firstName = body.get("firstName");
		String lastName = body.get("lastName");
		LocalDate dateOfBirth = LocalDate.parse(body.get("dateOfBirth"));
		try {
			adminService.isValidAdmin(adminUsername, password);
			
			authUser = adminService.createUser(newUserName, email, firstName, lastName, dateOfBirth);

			String response = "Username = " + authUser.getUsername() + " Password = " + authUser.getPassword();
			return new ResponseEntity<String>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/admin/fetchAllUser")
	public ResponseEntity<Object> fetchAllName(@RequestBody Map<String, String> body) {

		String adminUsername = body.get("username");
		String password = body.get("password");

		try {
			adminService.isValidAdmin(adminUsername, password);
			
			List<String> authUserList = adminService.fetchAllUsername();

			return new ResponseEntity<Object>(authUserList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/admin/fetchAllUserDetails/{findUsername}")
	public ResponseEntity<Object> fetchAllUserDetails(@PathVariable String findUsername, @RequestBody Map<String, String> body) {

		String adminUsername = body.get("username");
		String password = body.get("password");

		try {
			adminService.isValidAdmin(adminUsername, password);
			
			Auth_User authUser = adminService.fetchAllUserDetails(findUsername);

			return new ResponseEntity<Object>(authUser, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
