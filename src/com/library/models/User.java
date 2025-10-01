package com.library.models;

import com.library.datastructures.UserHistory;

public class User {
    private String userId;
    private String name;
    private String email;
    private UserHistory borrowHistory;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.borrowHistory = new UserHistory();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public UserHistory getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(UserHistory borrowHistory) {
        this.borrowHistory = borrowHistory;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowHistory=" + borrowHistory +
                '}';
    }
}
