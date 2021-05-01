package com.example.userManagementSystem.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userManagementSystem.entity.Auth_User;
import com.example.userManagementSystem.repository.AuthUserRepo;
import com.example.userManagementSystem.util.PasswordGenerator;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	PasswordGenerator pwdGenerator;

	@Autowired
	AuthUserRepo authUserRepo;

	@Override
	public Auth_User createUser(String newUserName, String email, String firstName, String lastName,
			LocalDate dateOfBirth) throws Exception {
		Auth_User authUser = new Auth_User();

		if (authUserRepo.findById(newUserName).isPresent()) {
			throw new Exception("User already exists");
		} else {
			authUser.setUsername(newUserName);
			authUser.setFirstName(firstName);
			authUser.setLastName(lastName);
			authUser.setEmail(email);

			ZoneId defaultZoneId = ZoneId.of("Z");
			Date date = Date.from(dateOfBirth.atStartOfDay(defaultZoneId).toInstant());
			authUser.setDateOfBirth(date);

			authUser.setPassword(pwdGenerator.passwordGenerator());
			authUser.setAdmin(false);

			Auth_User savedUser = authUserRepo.save(authUser);

			return savedUser;
		}
	}

	@Override
	public boolean isValidAdmin(String userName, String password) throws Exception {

		Optional<Auth_User> authUserOpt = authUserRepo.findByUsernameAndIsAdmin(userName, true);
		if (!authUserOpt.isPresent()) {
			throw new Exception("Invalid username or password");
		}
		return true;
	}

	@Override
	public List<String> fetchAllUsername() {
		List<Auth_User> authUserList = authUserRepo.findAllByIsAdmin(false);
		List<String> authUserStr = authUserList.stream().map(x -> x.getFirstName() + " " + x.getLastName()).collect(Collectors.toList());
		return authUserStr;
	}

	@Override
	public Auth_User fetchAllUserDetails(String username) throws Exception {
		Optional<Auth_User> authUserOpt = authUserRepo.findByUsernameAndIsAdmin(username, false);
		if (authUserOpt.isPresent()) {
			return authUserOpt.get();
		} else {
			throw new Exception("User doesn't exist");
		}
	}

}
