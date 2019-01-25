package com.sullivan.jeremy.databaseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootDatabaseAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatabaseAppApplication.class, args);
	}
}
