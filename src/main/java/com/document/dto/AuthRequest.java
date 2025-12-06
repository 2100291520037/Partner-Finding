package com.document.dto;

import jakarta.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

