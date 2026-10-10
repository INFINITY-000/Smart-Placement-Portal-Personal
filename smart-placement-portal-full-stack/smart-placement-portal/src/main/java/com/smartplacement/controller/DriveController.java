package com.smartplacement.controller;

import com.smartplacement.entity.Company;
import com.smartplacement.entity.PlacementDrive;
import com.smartplacement.repository.CompanyRepository;
import com.smartplacement.repository.PlacementDriveRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/drives")
public class DriveController {
    private final PlacementDriveRepository drives; private final CompanyRepository companies;
    public DriveController(PlacementDriveRepository drives,CompanyRepository companies){this.drives=drives;this.companies=companies;}
    private Map<String,Object> view(PlacementDrive d){
        Map<String,Object> m=new LinkedHashMap<>();m.put("id",d.getId());m.put("companyId",d.getCompany().getId());m.put("company",d.getCompany().getName());
        m.put("role",d.getJobRole());m.put("package",d.getPackageAmount());m.put("location",d.getLocation());m.put("cgpa",d.getMinCgpa());
        m.put("eligibleBranches",d.getEligibleBranches());m.put("driveDate",d.getDriveDate());m.put("deadline",d.getDeadline());m.put("status",d.getStatus());m.put("openings",d.getOpenings());m.put("description",d.getDescription());return m;
    }
    @GetMapping public List<Map<String,Object>> all(){return drives.findAll().stream().map(this::view).toList();}
    @GetMapping("/active") public List<Map<String,Object>> active(){return drives.findByStatus("Active").stream().map(this::view).toList();}
    @PostMapping public ResponseEntity<?> create(@RequestBody Map<String,Object> b){
        Long companyId=Long.valueOf(b.get("companyId").toString()); Optional<Company> c=companies.findById(companyId); if(c.isEmpty())return ResponseEntity.badRequest().body(Map.of("message","Company not found"));
        PlacementDrive d=new PlacementDrive(); d.setCompany(c.get()); d.setJobRole((String)b.get("jobRole"));d.setPackageAmount((String)b.get("packageAmount"));d.setLocation((String)b.get("location"));
        d.setMinCgpa(Double.valueOf(b.get("minCgpa").toString()));d.setEligibleBranches((String)b.getOrDefault("eligibleBranches","CSE,IT,AI-DS"));d.setStatus("Active");
        if(b.get("openings")!=null)d.setOpenings(Integer.valueOf(b.get("openings").toString()));d.setDescription((String)b.get("description"));
        if(b.get("deadline")!=null)d.setDeadline(LocalDate.parse(b.get("deadline").toString()));if(b.get("driveDate")!=null)d.setDriveDate(LocalDate.parse(b.get("driveDate").toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(view(drives.save(d)));
    }
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Map<String,Object> b){return drives.findById(id).map(d->{
        if(b.get("companyId")!=null) companies.findById(Long.valueOf(b.get("companyId").toString())).ifPresent(d::setCompany);
        if(b.get("jobRole")!=null)d.setJobRole((String)b.get("jobRole"));if(b.get("packageAmount")!=null)d.setPackageAmount((String)b.get("packageAmount"));if(b.get("location")!=null)d.setLocation((String)b.get("location"));
        if(b.get("minCgpa")!=null)d.setMinCgpa(Double.valueOf(b.get("minCgpa").toString()));if(b.get("status")!=null)d.setStatus((String)b.get("status"));if(b.get("description")!=null)d.setDescription((String)b.get("description"));
        return ResponseEntity.ok(view(drives.save(d)));
    }).orElseGet(()->ResponseEntity.notFound().build());}
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){if(!drives.existsById(id))return ResponseEntity.notFound().build();drives.deleteById(id);return ResponseEntity.noContent().build();}
}
