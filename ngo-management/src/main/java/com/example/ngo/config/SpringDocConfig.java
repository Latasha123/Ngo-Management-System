package com.example.ngo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebMvc
public class SpringDocConfig {

	private final static String description = "The NGO Management System is a secure and role-based web application built with Spring Boot and MySQL.\r\n"
			+ "        It enables transparent and efficient management of donations, NGOs, fundraisers, volunteers, and donors.";
	private final static String securitySchemeName = "Bearer Auth";

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info().title("NGO Management Service").description(description).termsOfService("Test")
						.contact(new Contact().name("NGO Management Service")))
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
						.name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}
}
