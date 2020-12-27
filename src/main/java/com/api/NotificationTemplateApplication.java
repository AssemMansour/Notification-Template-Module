package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.company")
public class NotificationTemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationTemplateApplication.class, args);
	}

}
