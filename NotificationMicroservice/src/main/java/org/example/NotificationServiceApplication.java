package org.example;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // This annotation enables Spring Boot's auto-configuration and component scanning
public class NotificationServiceApplication {

    public static void main(String[] args) {
        // This is the standard main method that launches the Spring Boot application
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

}
