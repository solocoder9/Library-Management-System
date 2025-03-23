package com.librarymanagement.controller;

import com.librarymanagement.dto.LoginDTO;
import com.librarymanagement.dto.UserDTO;
import com.librarymanagement.entity.User;
import com.librarymanagement.exception.DuplicateUserException;
import com.librarymanagement.repository.UserRepository;
import com.librarymanagement.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserDTO userDTO) {
        if (userRepository.findByUserid(userDTO.getUserid()).isPresent()) {
            throw new DuplicateUserException("User ID already exists");
        }

        User newUser = new User();
        newUser.setUserid(userDTO.getUserid());
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(userDTO.getRole().toUpperCase());
        
        userRepository.save(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDTO request) {
        Optional<User> userOptional = userRepository.findByUserid(request.getUserid());
        Map<String, Object> response = new HashMap<>();

        if (userOptional.isEmpty()) {
            response.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            response.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = jwtUtil.generateToken(user.getUserid(), user.getRole());
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("userid", user.getUserid());
        
        return ResponseEntity.ok(response);
    }
}