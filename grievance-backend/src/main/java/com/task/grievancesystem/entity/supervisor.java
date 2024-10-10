package com.task.grievancesystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class supervisor {
    
    @Id
    private Long id;
    private String name;
    private String password;

    public supervisor(){

    }

    public supervisor(String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }
}
