package com.task.grievancesystem.repositories;

import com.task.grievancesystem.entity.supervisor;
import com.task.grievancesystem.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface supervisorrepo extends JpaRepository <supervisor, Long> {


    supervisor findByName(String name);
}
