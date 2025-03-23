package com.librarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.librarymanagement.dto.UserDTO;
import com.librarymanagement.entity.User;
import com.librarymanagement.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String registerUser(UserDTO userDTO) {
		User user = new User();
		user.setUserid(userDTO.getUserid());
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getRole());
		userRepository.save(user);
		return "User registered successfully!";
	}
}
