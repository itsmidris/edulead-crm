package com.itsmidris.edulead_crm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI().info(new Info()
                        .title("EduLead CRM API")
                        .description("""
                                        Enterprise Student Admission CRM

                                        Features

                                        • User Management

                                        • Lead Management

                                        • Call Logs

                                        • Follow Ups

                                        • Counseling

                                        • Dashboard

                                        Built with Spring Boot 4
                                        """)

                        .version("v1.0.0")

                        .contact(new Contact().name("Mohammad Imran Idrishi").email("idrishimdimran@gamil.com"))

                        .license(new License().name("Apache 2.0"))
                );
    }
}