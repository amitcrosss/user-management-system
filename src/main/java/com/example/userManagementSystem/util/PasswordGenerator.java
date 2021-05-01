package com.example.userManagementSystem.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PasswordGenerator {

	public static String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	public static String specialCharacters = "!@#$";
	public static String numbers = "1234567890";

	public String passwordGenerator() {

		int length = 8;
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();

		List<String> password = new ArrayList<String>();

		password.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())) + "");
		password.add(capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length())) + "");
		password.add(specialCharacters.charAt(random.nextInt(specialCharacters.length())) + "");
		password.add(numbers.charAt(random.nextInt(numbers.length())) + "");

		for (int i = 4; i < length; i++) {
			password.add(combinedChars.charAt(random.nextInt(combinedChars.length())) + "");
		}

		Collections.shuffle(password);
		String passWordStr = password.stream().collect(Collectors.joining());

		return passWordStr;

	}

	public boolean isValidPassword(String password) {
		if (password.length() >= 8 && password.chars().anyMatch(x -> lowerCaseLetters.indexOf(x) != -1)
				&& password.chars().anyMatch(x -> capitalCaseLetters.indexOf(x) != -1)
				&& password.chars().anyMatch(x -> specialCharacters.indexOf(x) != -1)
				&& password.chars().anyMatch(x -> numbers.indexOf(x) != -1)) {
			return true;
		}
		return false;
	}
}
