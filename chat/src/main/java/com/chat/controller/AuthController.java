package com.chat.controller;

import com.chat.models.User;
import com.chat.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chat.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        log.info("Received registration request for user: {}", user.getUsername());

        try {
            User registeredUser = authService.register(user);
            log.info("Username: {}", user.getUsername());
            log.info("Encrypted Password: {}", user.getPassword());

            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during registration: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.info("Health check endpoint accessed.");
        return ResponseEntity.ok("Backend is running.");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User loggedInUser = authService.login(user.getUsername(), user.getPassword());

            if (loggedInUser != null) {
                // Generar y devolver el token JWT
                String token = JwtUtil.generateToken(loggedInUser.getUsername());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Server error: " + ex.getMessage());
        }
    }
}
