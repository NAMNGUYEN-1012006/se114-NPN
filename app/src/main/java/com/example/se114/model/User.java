package com.example.se114.model;

public class User {
    private String name;
    private String email;
    private String password;
    private String address;
    private String avatarUrl;
    private String description;

    public User(String name, String email, String password, String address, String avatarUrl, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.description = description;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}