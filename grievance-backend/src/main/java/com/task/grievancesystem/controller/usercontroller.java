package com.task.grievancesystem.controller;

import com.task.grievancesystem.entity.grievance; // Assuming the correct name for Grievance entity is capitalized
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.task.grievancesystem.service.userserv; // Capitalized for convention
import com.task.grievancesystem.entity.users; // Ensure the entity is properly named
import com.task.grievancesystem.repositories.userrepo; // Ensure the repository is properly named
import com.task.grievancesystem.entity.LoginResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class usercontroller { // Capitalized for convention

    @Autowired
    private userserv userService; // Correct the service name

    @Autowired
    private userrepo userRepository; // Correct the repository name

    @PostMapping("/register")
    public ResponseEntity<users> registerUser(@RequestBody users u) {
        // Register the user with the directly provided User object
        users newUser = userService.registerUser(u.getName(), u.getEmail(), u.getPass());
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody users loginRequest) {
        users loggedInUser = userService.loginUser(loginRequest.getName(), loginRequest.getPass());
        if (loggedInUser != null) { // Use loggedInUser instead of users
            // Return user_id and a success message
            LoginResponse response = new LoginResponse(loggedInUser.getId(), loggedInUser.getName(), "Login successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, null, "Invalid username or password"));
        }
    }

    @GetMapping
    public ResponseEntity<Optional<List<users>>> getAllUsers() {
        Optional<List<users>> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<users> findUserById(@PathVariable Long id) {
        Optional<users> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
