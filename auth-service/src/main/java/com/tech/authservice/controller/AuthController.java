package com.tech.authservice.controller;

import com.tech.authservice.dto.JwtResponse;
import com.tech.authservice.dto.LoginRequest;
import com.tech.authservice.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        logger.info("Received login request for user: " + request.getUsername());
        // Simulated login
        String token = jwtUtil.generateToken(request.getUsername(), request.getRole());
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestBody String token) {
        // Validate token (simplified, in real case you would decode and verify)
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build(); // Unauthorized      
        }
    }
}