package com.task.grievancesystem.service;

import com.task.grievancesystem.entity.assignee;
import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.users;
import com.task.grievancesystem.repositories.assigneerepo;
import com.task.grievancesystem.repositories.grievancerepo;
import com.task.grievancesystem.repositories.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class grievanceserv {

    @Autowired
    private grievancerepo grievrepo;

    @Autowired
    private assigneerepo assignrepo;

    @Autowired
    private userrepo usrepo;

    public grievance addGrievance(Long userId, String type, String descptn) {

        // Create a new grievance and assign the user
        grievance griev = new grievance(type, descptn);
        griev.setUsrId(userId); // Set the user

        return grievrepo.save(griev);
    }

    // Service method to get grievances assigned to a specific assignee ID
    public List<grievance> getGrievancesByAssigneeId(Long asgn_id) {
        return grievrepo.findByAsgn(asgn_id);
    }

    public Optional<grievance> findGrievanceById(Long id) {
        return grievrepo.findById(id);
    }

    public List<grievance> getGrievanceByUserId(Long usr_id) {
        return grievrepo.findByUsrId(usr_id);
    }



    public Optional<List<grievance>> getAllGrievances() {
        return grievrepo.findAllOptional();
    }


    public void deleteGrievance(Long id) {
        grievance griev = grievrepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Grievance not found with id : " + id));
        grievrepo.delete(griev);
    }
}
