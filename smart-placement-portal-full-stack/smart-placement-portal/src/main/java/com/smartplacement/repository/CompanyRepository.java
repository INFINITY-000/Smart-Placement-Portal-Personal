package com.smartplacement.repository;
import com.smartplacement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CompanyRepository extends JpaRepository<Company,Long>{}
