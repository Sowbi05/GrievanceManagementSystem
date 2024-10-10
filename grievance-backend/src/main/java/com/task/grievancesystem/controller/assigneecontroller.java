package com.task.grievancesystem.controller;

import com.task.grievancesystem.entity.LoginResponse;
import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.task.grievancesystem.service.assigneeservice;
import com.task.grievancesystem.service.grievanceserv;
import com.task.grievancesystem.entity.assignee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignee")
public class assigneecontroller {

    @Autowired
    private assigneeservice assignserv;

    @Autowired
    private grievanceserv grievserv;

    @GetMapping
    public ResponseEntity<Optional<List<assignee>>> getAllAssignees() {
        Optional<List<assignee>> a = assignserv.getAllAssignees();
        if(a.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(a);
        }
    }

    @GetMapping("/{id}")
    public Optional<assignee> findAssigneeById(@PathVariable Long id) {
        return assignserv.findAssigneeById(id);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<grievance> updateGrievanceStatus(
            @PathVariable Long id,
            @RequestParam Long assigneeId,
            @RequestParam String status) {
        grievance updatedGrievance = assignserv.updateGrievanceStatus(id, assigneeId, status);
        return ResponseEntity.ok(updatedGrievance);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAssignee(@RequestBody assignee loginreq) {
        assignee loggedinAssignee = assignserv.loginAssignee(loginreq.getname(), loginreq.getPass());
        if (loggedinAssignee != null) { // Use loggedInUser instead of users
            // Return user_id and a success message
            LoginResponse response = new LoginResponse(loggedinAssignee.getid(), loggedinAssignee.getname(), "Login successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, null, "Invalid username or password"));
        }
    }

}
