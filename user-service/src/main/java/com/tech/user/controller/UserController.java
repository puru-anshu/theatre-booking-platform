package com.tech.user.controller;

import com.tech.user.dto.CreateUserRequest;
import com.tech.user.dto.UserResponse;
import com.tech.user.dto.LoginRequest;
import com.tech.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/auth/token")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    // validate credentials (dummy for now)

    String token = Jwts.builder()
        .setSubject("user-" + request.getUsername())
        .signWith(Keys.hmacShaKeyFor("YourSuperSecretKeyThatIsAtLeast32BytesLong".getBytes()))
        .compact();

    return ResponseEntity.ok(token);
}

}
