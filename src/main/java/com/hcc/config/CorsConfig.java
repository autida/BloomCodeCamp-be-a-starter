package com.hcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000") // Replace with the origin of your React app
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Add the HTTP methods you need
                        .allowedHeaders("*") // Add any specific headers required by your requests
                        .allowCredentials(true); // If your requests require credentials (e.g., cookies)
            }
        };
    }
}

