package com.ecomm.model;

public class User {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public User(String userId, String email, String firstName, String lastName, String password) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
