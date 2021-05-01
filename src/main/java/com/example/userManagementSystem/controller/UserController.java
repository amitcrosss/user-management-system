package com.example.userManagementSystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userManagementSystem.entity.Auth_User;
import com.example.userManagementSystem.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/userLogin")
	public ResponseEntity<String> userLogin(@RequestBody Map<String, String> body){
		String userName = body.get("username");
		String password = body.get("password");
		
		try {
			Auth_User authUser =  userService.userLogin(userName, password);
			String response = "Login successfull! Welcome " + authUser.getFirstName();
			return new ResponseEntity<String>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Map<String, String> body){
		String userName = body.get("username");
		String password = body.get("password");
		
		String newPassword = body.get("newPassword");
		
		try {
			userService.changePassword(userName, password, newPassword);
			String response = "Password updated successfully";
			return new ResponseEntity<String>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
