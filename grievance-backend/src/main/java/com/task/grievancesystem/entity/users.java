package com.task.grievancesystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String pass;

    public users() {
    }

    public users(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass=pass;
    }
}
