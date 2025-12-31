package com.anushka.schoolDemo;

import com.anushka.schoolDemo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SchoolDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolDemoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("teacher123"));

	}
}
