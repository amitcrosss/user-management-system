package com.example.userManagementSystem.service;

import com.example.userManagementSystem.entity.Auth_User;

public interface UserService {

	public Auth_User userLogin(String username, String password) throws Exception;

	public void changePassword(String userName, String password, String newPassword) throws Exception;
}
