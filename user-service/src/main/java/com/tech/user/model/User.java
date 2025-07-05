package com.tech.user.model;

import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.persistence.*;

@Entity
@Table(name = "users")

@SessionAttributes
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role; // CUSTOMER or THEATRE_OWNER

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
