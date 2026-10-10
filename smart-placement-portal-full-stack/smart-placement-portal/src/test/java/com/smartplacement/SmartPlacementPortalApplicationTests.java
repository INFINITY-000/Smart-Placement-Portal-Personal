package com.smartplacement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Basic smoke test — verifies the Spring Boot application context
 * loads without errors. Run this to check everything is configured correctly.
 */
@SpringBootTest
class SmartPlacementPortalApplicationTests {

    @Test
    void contextLoads() {
        // If this test passes, it means Spring Boot started successfully
        // and all beans, configs, and database connections are working.
        System.out.println("Spring Boot context loaded successfully!");
    }

}
