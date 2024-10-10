package com.task.grievancesystem.service;

import com.task.grievancesystem.entity.grievance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.grievancesystem.repositories.userrepo;
import com.task.grievancesystem.entity.users;

import java.util.*;

@Service
public class userserv {

    @Autowired
    private userrepo usrepo;

    public users registerUser(String name, String email, String pass) {
        // Check if the email is already taken
        Optional<users> existingUser = usrepo.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Create a new user and save to the database
        users newUser = new users(name, email, pass);
        return usrepo.save(newUser);
    }


    public users loginUser(String name, String pass) {
        Optional<users> userOptional = usrepo.findByName(name);

        if (userOptional.isPresent()) {
            users user = userOptional.get();
            // Verify password (assuming plain text for simplicity; in production use hashed passwords)
            if (user.getPass().equals(pass)) {
                return user;
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }


    public Optional<List<users>> getAllUsers() {
        return usrepo.findAllOptional();
    }

    public Optional<users> findUserById(Long id) {
        return usrepo.findById(id);
    }

}
