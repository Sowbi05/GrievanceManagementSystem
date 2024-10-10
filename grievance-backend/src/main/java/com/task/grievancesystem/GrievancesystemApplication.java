package com.task.grievancesystem;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class GrievancesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrievancesystemApplication.class, args);
	}

}
