package com.example.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class ContentManagementSyatemApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ContentManagementSyatemApplication.class, args);
	}

}
