package com.smartplacement.controller;

import com.smartplacement.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final StudentRepository students;private final CompanyRepository companies;private final PlacementDriveRepository drives;private final ApplicationRepository apps;
    public DashboardController(StudentRepository s,CompanyRepository c,PlacementDriveRepository d,ApplicationRepository a){students=s;companies=c;drives=d;apps=a;}
    @GetMapping("/stats") public Map<String,Object> stats(){
        var all=apps.findAll();Map<String,Object>m=new LinkedHashMap<>();m.put("students",students.count());m.put("companies",companies.count());m.put("activeDrives",drives.findByStatus("Active").size());m.put("applications",all.size());m.put("shortlisted",all.stream().filter(a->"Shortlisted".equals(a.getStatus())).count());m.put("selected",all.stream().filter(a->"Selected".equals(a.getStatus())).count());return m;
    }
    @GetMapping("/student/{id}") public Map<String,Object> studentStats(@PathVariable Long id){var list=apps.findByStudentId(id);Map<String,Object>m=new LinkedHashMap<>();m.put("applications",list.size());m.put("shortlisted",list.stream().filter(a->"Shortlisted".equals(a.getStatus())||"Interview".equals(a.getStatus())).count());m.put("selected",list.stream().filter(a->"Selected".equals(a.getStatus())).count());m.put("activeDrives",drives.findByStatus("Active").size());return m;}
}
