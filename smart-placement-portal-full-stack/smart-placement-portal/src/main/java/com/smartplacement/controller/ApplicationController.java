package com.smartplacement.controller;

import com.smartplacement.dto.ApplicationRequest;
import com.smartplacement.dto.StatusRequest;
import com.smartplacement.entity.*;
import com.smartplacement.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationRepository apps; private final StudentRepository students; private final PlacementDriveRepository drives; private final PlacementResultRepository results;
    public ApplicationController(ApplicationRepository a,StudentRepository s,PlacementDriveRepository d,PlacementResultRepository r){apps=a;students=s;drives=d;results=r;}
    private Map<String,Object> view(Application a){
        Map<String,Object> m=new LinkedHashMap<>();m.put("id",a.getId());m.put("studentId",a.getStudent().getId());m.put("student",a.getStudent().getName());m.put("email",a.getStudent().getEmail());m.put("company",a.getDrive().getCompany().getName());m.put("driveId",a.getDrive().getId());m.put("role",a.getDrive().getJobRole());m.put("package",a.getDrive().getPackageAmount());m.put("date",a.getAppliedDate());m.put("status",a.getStatus());return m;
    }
    @GetMapping public List<Map<String,Object>> all(){return apps.findAll().stream().map(this::view).toList();}
    @GetMapping("/student/{studentId}") public List<Map<String,Object>> byStudent(@PathVariable Long studentId){return apps.findByStudentId(studentId).stream().map(this::view).toList();}
    @PostMapping public ResponseEntity<?> create(@RequestBody ApplicationRequest req){
        if(apps.findByStudentIdAndDriveId(req.studentId(),req.driveId()).isPresent())return ResponseEntity.status(409).body(Map.of("message","You have already applied to this drive"));
        Optional<Student>s=students.findById(req.studentId());Optional<PlacementDrive>d=drives.findById(req.driveId());if(s.isEmpty()||d.isEmpty())return ResponseEntity.badRequest().body(Map.of("message","Student or drive not found"));
        if(s.get().getCgpa()!=null&&d.get().getMinCgpa()!=null&&s.get().getCgpa()<d.get().getMinCgpa())return ResponseEntity.badRequest().body(Map.of("message","Student does not meet the CGPA requirement"));
        Application a=new Application();a.setStudent(s.get());a.setDrive(d.get());a.setAppliedDate(LocalDate.now());a.setStatus("Applied");return ResponseEntity.status(HttpStatus.CREATED).body(view(apps.save(a)));
    }
    @PutMapping("/{id}/status") public ResponseEntity<?> status(@PathVariable Long id,@RequestBody StatusRequest req){
        Set<String> allowed=Set.of("Applied","Shortlisted","Interview","Selected","Rejected");if(!allowed.contains(req.status()))return ResponseEntity.badRequest().body(Map.of("message","Invalid status"));
        return apps.findById(id).map(a->{a.setStatus(req.status());Application saved=apps.save(a);if("Selected".equals(req.status())&&results.findByApplicationId(id).isEmpty()){PlacementResult r=new PlacementResult();r.setApplication(saved);r.setFinalPackage(saved.getDrive().getPackageAmount());r.setJoiningDate(LocalDate.now().plusMonths(6));r.setResult("Selected");results.save(r);}return ResponseEntity.ok(view(saved));}).orElseGet(()->ResponseEntity.notFound().build());
    }
}
