package com.tech.user.dto;

public class CreateUserRequest {
    private String name;
    private String email;
    private String role; // CUSTOMER or THEATRE_OWNER
    // Getters and Setters
    public String getName() {
        return name;                
}

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
