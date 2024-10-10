package com.task.grievancesystem.repositories;

import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface grievancerepo extends JpaRepository <grievance, Long>{
    
    default Optional<List<grievance>> findAllOptional() {
        List<grievance> grievances = findAll();
        return grievances.isEmpty() ? Optional.empty() : Optional.of(grievances);
    }

    List<grievance> findByUsrId(Long usr_id);

    List<grievance> findByAsgn(Long asgn_id);

}
