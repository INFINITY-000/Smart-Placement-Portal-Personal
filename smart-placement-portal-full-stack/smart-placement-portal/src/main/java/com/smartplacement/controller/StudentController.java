package com.smartplacement.controller;

import com.smartplacement.entity.Student;
import com.smartplacement.repository.StudentRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repo;
    public StudentController(StudentRepository repo){this.repo=repo;}
    private Map<String,Object> view(Student s){
        Map<String,Object> m=new LinkedHashMap<>();
        m.put("id",s.getId()); m.put("name",s.getName()); m.put("email",s.getEmail()); m.put("phone",s.getPhone());
        m.put("branch",s.getBranch()); m.put("year",s.getYear()); m.put("cgpa",s.getCgpa()); m.put("skills",s.getSkills());
        m.put("resume",s.getResume()); m.put("rollNo",s.getRollNo()); return m;
    }
    @GetMapping public List<Map<String,Object>> all(){return repo.findAll().stream().map(this::view).toList();}
    @GetMapping("/{id}") public ResponseEntity<?> one(@PathVariable Long id){return repo.findById(id).map(s->ResponseEntity.ok(view(s))).orElseGet(()->ResponseEntity.notFound().build());}
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Map<String,Object> body){
        return repo.findById(id).map(s->{
            if(body.containsKey("name"))s.setName((String)body.get("name")); if(body.containsKey("phone"))s.setPhone((String)body.get("phone"));
            if(body.containsKey("branch"))s.setBranch((String)body.get("branch")); if(body.containsKey("year"))s.setYear((String)body.get("year"));
            if(body.containsKey("skills"))s.setSkills((String)body.get("skills")); if(body.containsKey("cgpa"))s.setCgpa(Double.valueOf(body.get("cgpa").toString()));
            return ResponseEntity.ok(view(repo.save(s)));
        }).orElseGet(()->ResponseEntity.notFound().build());
    }
}
