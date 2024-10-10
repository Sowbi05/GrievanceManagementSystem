package com.task.grievancesystem.service;

import com.task.grievancesystem.entity.assignee;
import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.grievancesystem.repositories.assigneerepo;
import com.task.grievancesystem.repositories.grievancerepo;
import com.task.grievancesystem.entity.assignee;

import java.util.*;

@Service
public class assigneeservice {

    @Autowired
    private assigneerepo assirepo;

    @Autowired
    private grievancerepo grievrepo;

    public Optional<List<assignee>> getAllAssignees() {
        return assirepo.findAllOptional();
    }

    public Optional<assignee> findAssigneeById(Long id) {
        return assirepo.findById(id);
    }


    public grievance updateGrievanceStatus(Long id, Long assigneeId, String newStatus) {

        grievance griev = grievrepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Grievance not found with id : " + id));

        // Check if the assignee ID matches the current assignee of the grievance
        if (griev.getAsgn() == null || !griev.getAsgn().equals(assigneeId)) {
            throw new RuntimeException("Assignee not authorized to update this grievance");
        }

        // Update the status
        griev.setStatus(newStatus);

        // Save and return the updated grievance
        return grievrepo.save(griev);
    }

    public assignee loginAssignee(String name, String pass) {
        Optional<assignee> assign = assirepo.findByName(name);

        if (assign.isPresent()) {
            assignee assi = assign.get();
            // Verify password (assuming plain text for simplicity; in production use hashed passwords)
            if (assi.getPass().equals(pass)) {
                return assi;
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public assignee addAssignee(assignee assign) {
        // Check if the assignee with the same email already exists (Optional: business rule)

        // Save the new assignee in the database
        return assirepo.save(assign);
    }

}
