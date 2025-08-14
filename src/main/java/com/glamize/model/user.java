package com.glamize.model;

public class user {
    private String email;
    private String password; // Note: Store hashed password in real apps

    public user(String email, String password) {
        this.email = email;
        this.password = password;
    }

    
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
