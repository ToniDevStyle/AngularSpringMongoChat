package com.chat.controller;

import com.chat.service.AuthService;
import com.chat.utils.JwtUtil;
import com.chat.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;  // Inject JwtUtil for token generation

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Check if the username and password are provided
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password must not be empty.");
        }

        try {
            // Attempt login with the provided credentials
            User loggedInUser = authService.login(user.getUsername(), user.getPassword());

            if (loggedInUser != null) {
                // Generate JWT token for the logged-in user
                String token = jwtUtil.generateToken(loggedInUser.getUsername());
                return ResponseEntity.ok(token); // Return token with 200 OK status
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception ex) {
            // Return server error in case of any exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + ex.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        // Validate username and password
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password must not be empty.");
        }

        try {
            // Check if the user already exists
            if (authService.userExists(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists.");
            }

            // Register the new user
            User registeredUser = authService.register(user);

            if (registeredUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed.");
            }
        } catch (Exception ex) {
            // Return error message in case of any exception during registration
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + ex.getMessage());
        }
    }
}
