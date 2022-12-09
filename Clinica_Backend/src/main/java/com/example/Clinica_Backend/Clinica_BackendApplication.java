package com.example.Clinica_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class Clinica_BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Clinica_BackendApplication.class, args);
	}

}
