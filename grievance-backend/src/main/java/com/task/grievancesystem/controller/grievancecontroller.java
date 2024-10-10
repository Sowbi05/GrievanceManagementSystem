package com.task.grievancesystem.controller;

import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.users;
import com.task.grievancesystem.repositories.grievancerepo;
import com.task.grievancesystem.service.grievanceserv;
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
import org.springframework.web.bind.annotation.CrossOrigin;





@RestController
@RequestMapping("/grievance")

public class grievancecontroller {

    @Autowired
    private grievanceserv grievserv;

    @PostMapping("/add")
    public ResponseEntity<grievance> addGrievance(
            @RequestParam Long userId,
            @RequestBody grievance g) {
        grievance griev = grievserv.addGrievance(userId, g.getType(), g.getDescptn());
        return ResponseEntity.ok(griev);
    }




    @GetMapping("/{id}")
    public Optional<grievance> findGrievanceById(@PathVariable Long id) {

        return grievserv.findGrievanceById(id);
    }


    @GetMapping("/user")
    public List<grievance> getGrievanceByUserId(@RequestParam Long usr_id) {

        return grievserv.getGrievanceByUserId(usr_id);
    }



    @GetMapping
    public ResponseEntity<Optional<List<grievance>>> getAllGrievances() {
        Optional<List<grievance>> griev = grievserv.getAllGrievances();
        if(griev.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok(griev);
        }
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<grievance>> getAssignedGrievances(@RequestParam Long asgn_id) {
        List<grievance> grievances = grievserv.getGrievancesByAssigneeId(asgn_id);
        if (grievances.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no grievances found
        }
        return ResponseEntity.ok(grievances); // Return 200 OK with grievances
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrievance(@PathVariable Long id) {
        grievserv.deleteGrievance(id);
        return ResponseEntity.noContent().build();
    }
}
