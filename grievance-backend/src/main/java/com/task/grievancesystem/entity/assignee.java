package com.task.grievancesystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assignee")
public class assignee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String pass;

    public assignee() {
    }

    public assignee(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id=id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
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
