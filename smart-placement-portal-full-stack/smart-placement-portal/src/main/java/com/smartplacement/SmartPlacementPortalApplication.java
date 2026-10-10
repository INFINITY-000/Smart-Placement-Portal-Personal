package com.smartplacement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SmartPlacementPortalApplication — Entry Point of the entire application.
 *
 * @SpringBootApplication is a shortcut annotation that combines three things:
 *   1. @Configuration       — marks this class as a source of Spring configuration
 *   2. @EnableAutoConfiguration — tells Spring Boot to automatically set up things
 *      like database connections, web server, etc. based on what's in pom.xml
 *   3. @ComponentScan       — tells Spring to scan the entire package for
 *      classes annotated with @Controller, @Service, @Repository etc.
 *
 * When you run this class, Spring Boot:
 *   1. Starts an embedded Tomcat server on port 8080
 *   2. Connects to MySQL database
 *   3. Creates all required tables (based on our Entity classes)
 *   4. Registers all API endpoints (from our Controller classes)
 *   5. Serves HTML files from the 'static' folder
 */
@SpringBootApplication
public class SmartPlacementPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartPlacementPortalApplication.class, args);
        System.out.println("========================================");
        System.out.println("  Smart Placement Portal is RUNNING!    ");
        System.out.println("  Open: http://localhost:8080           ");
        System.out.println("========================================");
    }

}
