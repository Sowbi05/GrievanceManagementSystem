package com.task.grievancesystem.repositories;

import com.task.grievancesystem.entity.grievance;
import com.task.grievancesystem.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface userrepo  extends JpaRepository<users, Long> {
    
    Optional<users> findByName(String name);
    Optional<users> findByEmail(String email);

    default Optional<List<users>> findAllOptional() {
        List<users> u = findAll();
        return u.isEmpty() ? Optional.empty() : Optional.of(u);
    }

}
