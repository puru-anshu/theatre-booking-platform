package com.tech.user.service;

import com.tech.user.dto.CreateUserRequest;
import com.tech.user.dto.UserResponse;
import com.tech.user.model.User;
import com.tech.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse register(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user = userRepository.save(user);
        return mapToResponse(user);
    }

    public Optional<UserResponse> getUser(Long id) {
        return userRepository.findById(id).map(this::mapToResponse);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        return res;
    }
}
