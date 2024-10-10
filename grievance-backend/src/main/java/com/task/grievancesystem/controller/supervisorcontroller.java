package com.task.grievancesystem.controller;

import com.task.grievancesystem.entity.assignee;
import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.supervisor;
import com.task.grievancesystem.entity.users;
import com.task.grievancesystem.repositories.grievancerepo;
import com.task.grievancesystem.service.assigneeservice;
import com.task.grievancesystem.service.supervisorservice;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/supervisor")

public class supervisorcontroller {

    @Autowired
    private supervisorservice superserv;

    @Autowired
    private assigneeservice assignserv;

    @PutMapping("/{gid}/assign")
    public ResponseEntity<grievance> assignGrievance(@PathVariable Long gid, @RequestParam Long aid) {
        grievance assgngriev = superserv.assignGrievance(gid, aid);
        return ResponseEntity.ok(assgngriev);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginSupervisor(@RequestParam String name, @RequestParam String password) {
        boolean isValid = superserv.loginSupervisor(name, password);
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/addAssignee")
    public ResponseEntity<String> addAssignee(@RequestBody assignee newAssignee) {
        assignee createdAssignee = assignserv.addAssignee(newAssignee);
        if (createdAssignee != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Assignee created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating assignee");
        }
    }

}
