package com.smartplacement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "placement_drives")
public class PlacementDrive {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false, fetch=FetchType.EAGER) private Company company;
    @Column(nullable=false) private String jobRole;
    private String packageAmount;
    private String location;
    private Double minCgpa;
    private String eligibleBranches;
    private LocalDate driveDate;
    private LocalDate deadline;
    private String status;
    private Integer openings;
    @Column(length=2000) private String description;
    public PlacementDrive() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Company getCompany(){return company;} public void setCompany(Company v){company=v;}
    public String getJobRole(){return jobRole;} public void setJobRole(String v){jobRole=v;}
    public String getPackageAmount(){return packageAmount;} public void setPackageAmount(String v){packageAmount=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public Double getMinCgpa(){return minCgpa;} public void setMinCgpa(Double v){minCgpa=v;}
    public String getEligibleBranches(){return eligibleBranches;} public void setEligibleBranches(String v){eligibleBranches=v;}
    public LocalDate getDriveDate(){return driveDate;} public void setDriveDate(LocalDate v){driveDate=v;}
    public LocalDate getDeadline(){return deadline;} public void setDeadline(LocalDate v){deadline=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public Integer getOpenings(){return openings;} public void setOpenings(Integer v){openings=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
}
