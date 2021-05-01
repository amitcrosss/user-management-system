package com.example.userManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userManagementSystem.entity.Auth_User;

public interface AuthUserRepo extends JpaRepository<Auth_User, String> {

	public Optional<Auth_User> findByUsernameAndIsAdmin(String userName, boolean isAdmin);

	public Optional<Auth_User> findByUsernameAndPassword(String userName, String password);

	public List<Auth_User> findAllByIsAdmin(boolean isAdmin);

}
