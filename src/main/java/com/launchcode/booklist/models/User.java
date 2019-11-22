package com.launchcode.booklist.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {
    @NotNull
    @Size(min = 5, max = 15)
    private String username;

    private String email;

    @NotNull
    private String password;
    private int userId;
    private int nextId;
    private Date myDate;


    public User() {
        userId = nextId;
        nextId++;
        this.myDate = new Date();
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getUserId() {
        return userId;
    }

    public Date getMyDate() {
        return myDate;
    }


}
