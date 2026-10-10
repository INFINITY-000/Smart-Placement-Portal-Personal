package com.smartplacement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String name;
    private String industry;
    private String location;
    private String website;
    private String hrEmail;
    @Column(length=1500) private String description;
    public Company() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getIndustry(){return industry;} public void setIndustry(String v){industry=v;}
    public String getLocation(){return location;} public void setLocation(String v){location=v;}
    public String getWebsite(){return website;} public void setWebsite(String v){website=v;}
    public String getHrEmail(){return hrEmail;} public void setHrEmail(String v){hrEmail=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
}
