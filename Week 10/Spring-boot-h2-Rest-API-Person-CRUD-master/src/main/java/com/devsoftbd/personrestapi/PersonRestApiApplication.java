package com.devsoftbd.personrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PersonRestApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PersonRestApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/person-service/api/v1/**").allowedOrigins("*").allowedMethods("GET", "POST",
						"PUT", "DELETE");
			}
		};
	}

}
