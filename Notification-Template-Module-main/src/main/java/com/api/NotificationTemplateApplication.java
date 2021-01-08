package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.api.model")
@ComponentScan("com.api.controller")
@EnableJpaRepositories("com.api.repo")
public class NotificationTemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationTemplateApplication.class, args);
	}

}
