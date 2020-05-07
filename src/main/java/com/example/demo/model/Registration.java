package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "usr")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String username;
    private String password;
    private String full_name;
    private String email;

    public Registration() {}

    public Registration(String username, String password, String full_name, String email) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}