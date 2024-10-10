package com.task.grievancesystem.service;

import com.task.grievancesystem.entity.assignee;
import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.supervisor;
import com.task.grievancesystem.entity.users;
import com.task.grievancesystem.repositories.supervisorrepo;
import com.task.grievancesystem.repositories.grievancerepo;
import com.task.grievancesystem.repositories.assigneerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.*;

@Service
public class supervisorservice {

    @Autowired
    private supervisorrepo superrepo;

    @Autowired
    private grievancerepo grievrepo;

    @Autowired
    private assigneerepo assignrepo;

    public grievance assignGrievance(Long gid, Long aid) {

        grievance griev = grievrepo.findById(gid)
                .orElseThrow(() -> new NoSuchElementException("Grievance not found with id : " + gid));

        griev.setAsgn(aid);
        griev.setStatus("assigned");
        return grievrepo.save(griev);
    }

    public boolean loginSupervisor(String name, String password) {
        supervisor s = superrepo.findByName(name);

        if (s != null && s.getPassword().equals(password)) {
            return true;  // Valid credentials
        }
        return false;  // Invalid credentials
    }

}
