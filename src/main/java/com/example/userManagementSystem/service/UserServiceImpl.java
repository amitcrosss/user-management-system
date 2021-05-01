package com.example.userManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userManagementSystem.entity.Auth_User;
import com.example.userManagementSystem.repository.AuthUserRepo;
import com.example.userManagementSystem.util.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	AuthUserRepo authUserRepo;
	
	@Autowired
	PasswordGenerator pwdGenerator;
	
	@Override
	public Auth_User userLogin(String username, String password) throws Exception {

		Optional<Auth_User> authUser = authUserRepo.findByUsernameAndPassword(username, password);
		
		if(!authUser.isPresent()) {
			throw new Exception("Username or password is invalid");
		}
		return authUser.get();
	}

	@Override
	public void changePassword(String username, String password, String newPassword) throws Exception {

		Optional<Auth_User> authUserOpt = authUserRepo.findByUsernameAndPassword(username, password);
		Auth_User authUser;
		if(!authUserOpt.isPresent()) {
			throw new Exception("Username or password is invalid");
		}
		else {
			if(pwdGenerator.isValidPassword(newPassword)) {
				authUser = authUserOpt.get();
				authUser.setPassword(newPassword);
				authUserRepo.save(authUser);
			}
			else {
				throw new Exception("Password condition not satisfied");
			}
		}
	}
	
}
