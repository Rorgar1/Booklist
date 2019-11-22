package com.launchcode.booklist.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 5, max = 15, message = "Please enter a username with 5-15 characters.")
    private String username;


    @NotNull
    @Size(min = 1, max = 15, message = "Please enter a password.")
    private String password;

    //email is optional, but if entered must be in valid format
    @Email
    private String email = "";

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Book> books;

    public User() {
    }

    public User(String username, String email, String password) {
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


}
