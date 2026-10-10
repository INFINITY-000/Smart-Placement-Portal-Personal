package com.smartplacement.controller;

import com.smartplacement.entity.Company;
import com.smartplacement.repository.CompanyRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyRepository repo;
    public CompanyController(CompanyRepository repo){this.repo=repo;}
    @GetMapping public List<Company> all(){return repo.findAll();}
    @GetMapping("/{id}") public ResponseEntity<?> one(@PathVariable Long id){return repo.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
    @PostMapping public ResponseEntity<Company> create(@RequestBody Company c){c.setId(null);return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(c));}
    @PutMapping("/{id}") public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Company input){return repo.findById(id).map(c->{c.setName(input.getName());c.setIndustry(input.getIndustry());c.setLocation(input.getLocation());c.setWebsite(input.getWebsite());c.setHrEmail(input.getHrEmail());c.setDescription(input.getDescription());return ResponseEntity.ok(repo.save(c));}).orElseGet(()->ResponseEntity.notFound().build());}
    @DeleteMapping("/{id}") public ResponseEntity<?> delete(@PathVariable Long id){if(!repo.existsById(id))return ResponseEntity.notFound().build();repo.deleteById(id);return ResponseEntity.noContent().build();}
}
