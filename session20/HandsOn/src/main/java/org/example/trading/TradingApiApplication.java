package org.example.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TradingApiApplication {

    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(TradingApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // CORS configuration to allow frontend to connect (e.g., from Angular or React)
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200", "https://trading.example.com") // adjust based on your front-end URL
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600); // max age for pre-flight requests
            }
        };
    }
}
