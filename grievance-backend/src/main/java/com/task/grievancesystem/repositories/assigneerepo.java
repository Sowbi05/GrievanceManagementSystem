package com.task.grievancesystem.repositories;

import com.task.grievancesystem.entity.assignee;
import com.task.grievancesystem.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface assigneerepo extends JpaRepository <assignee, Long> {

    default Optional<List<assignee>> findAllOptional() {
        List<assignee> a = findAll();
        return a.isEmpty() ? Optional.empty() : Optional.of(a);
    }

    Optional<assignee> findByName(String name);

    assignee findByEmail(String email);
}
