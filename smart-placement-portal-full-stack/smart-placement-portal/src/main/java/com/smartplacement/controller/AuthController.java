package com.smartplacement.controller;

import com.smartplacement.dto.LoginRequest;
import com.smartplacement.dto.LoginResponse;
import com.smartplacement.entity.PlacementOfficer;
import com.smartplacement.entity.Student;
import com.smartplacement.repository.PlacementOfficerRepository;
import com.smartplacement.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final StudentRepository students;
    private final PlacementOfficerRepository officers;
    private final PasswordEncoder encoder;

    public AuthController(StudentRepository students, PlacementOfficerRepository officers, PasswordEncoder encoder) {
        this.students=students; this.officers=officers; this.encoder=encoder;
    }

    @PostMapping("/student/login")
    public ResponseEntity<?> studentLogin(@RequestBody LoginRequest request) {
        return students.findByEmail(request.email())
                .filter(s -> encoder.matches(request.password(), s.getPasswordHash()))
                .<ResponseEntity<?>>map(s -> ResponseEntity.ok(new LoginResponse(s.getId(), s.getName(), s.getEmail(), "Student", "student")))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("success",false,"message","Invalid student email or password")));
    }

    @PostMapping("/placement/login")
    public ResponseEntity<?> placementLogin(@RequestBody LoginRequest request) {
        return officers.findByEmail(request.email())
                .filter(o -> encoder.matches(request.password(), o.getPasswordHash()))
                .<ResponseEntity<?>>map(o -> ResponseEntity.ok(new LoginResponse(o.getId(), o.getName(), o.getEmail(), o.getRole(), "placement")))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("success",false,"message","Invalid placement cell email or password")));
    }
}
