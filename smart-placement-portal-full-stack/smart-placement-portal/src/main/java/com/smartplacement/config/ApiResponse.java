package com.smartplacement.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiResponse — A utility class to build consistent API responses.
 *
 * Instead of returning raw data, every API response from our backend
 * will follow a consistent format:
 *   {
 *     "success": true,
 *     "message": "Application submitted successfully!",
 *     "data": { ... }
 *   }
 *
 * This makes the frontend JavaScript code simpler because it always
 * knows what structure to expect from the backend.
 */
public class ApiResponse {

    /**
     * Returns a success response with data.
     * Used when an operation is successful and we need to return some data.
     */
    public static ResponseEntity<Map<String, Object>> success(String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return ResponseEntity.ok(response);
    }

    /**
     * Returns a success response without data.
     * Used for operations like delete where we just confirm success.
     */
    public static ResponseEntity<Map<String, Object>> success(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    /**
     * Returns an error response.
     * Used when something goes wrong — invalid login, not eligible, etc.
     */
    public static ResponseEntity<Map<String, Object>> error(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Returns a 400 Bad Request error.
     * This is the most common error type — used for invalid input.
     */
    public static ResponseEntity<Map<String, Object>> badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns a 404 Not Found error.
     * Used when a student, company, or drive doesn't exist.
     */
    public static ResponseEntity<Map<String, Object>> notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }

}
