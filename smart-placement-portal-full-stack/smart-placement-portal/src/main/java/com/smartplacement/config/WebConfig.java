package com.smartplacement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig — Web MVC Configuration class.
 *
 * @Configuration tells Spring: "This class contains configuration settings."
 * It implements WebMvcConfigurer to customize Spring MVC behaviour.
 *
 * Two important things this class does:
 * 1. CORS Configuration — allows our HTML frontend (running on the same server)
 *    to make API calls to our backend without being blocked by the browser.
 * 2. View Controllers — maps URL paths directly to HTML files.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * CORS = Cross-Origin Resource Sharing
     * Without this, the browser may block JavaScript fetch() calls to our API.
     * We allow all origins (*) for development convenience.
     * In a real production app, you would restrict this to specific domains.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")       // Apply to all /api/* endpoints
                .allowedOrigins("*")         // Allow requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    /**
     * Maps the root URL "/" to the index.html landing page.
     * When someone visits http://localhost:8080, they see index.html
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }

}
