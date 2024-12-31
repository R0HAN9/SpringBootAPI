package com.YouTube.Backend;

// Importing necessary Spring Boot libraries for application setup and REST functionality
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main entry point for the YouTube Backend application.
 * This class sets up the Spring Boot application and defines a basic REST endpoint.
 */
@SpringBootApplication // Marks this class as the configuration and entry point for the Spring Boot application
@RestController // Combines @Controller and @ResponseBody for simplified REST API development
public class BackendApplication {

    /**
     * Main method to launch the Spring Boot application.
     * SpringApplication.run initializes the application context and starts the embedded server (e.g., Tomcat).
     *
     * @param args Command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args); // Bootstraps the application
    }

    /**
     * A simple GET endpoint for health check or introductory response.
     * In production, this can be replaced or extended to provide detailed service status.
     *
     * @return A greeting message ("Hello World!")
     */
    @GetMapping // Maps HTTP GET requests to this method
    public String sayHello() {
        return "Hello World!"; // Basic response to verify the service is running
    }
}
