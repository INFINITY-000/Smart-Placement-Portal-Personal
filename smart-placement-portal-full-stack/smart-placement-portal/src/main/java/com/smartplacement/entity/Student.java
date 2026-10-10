package com.smartplacement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String name;
    @Column(nullable=false, unique=true) private String email;
    @Column(nullable=false) private String passwordHash;
    private String phone;
    private String branch;
    private String year;
    private Double cgpa;
    @Column(length=1000) private String skills;
    private String resume;
    private String rollNo;

    public Student() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String v){passwordHash=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getBranch(){return branch;} public void setBranch(String v){branch=v;}
    public String getYear(){return year;} public void setYear(String v){year=v;}
    public Double getCgpa(){return cgpa;} public void setCgpa(Double v){cgpa=v;}
    public String getSkills(){return skills;} public void setSkills(String v){skills=v;}
    public String getResume(){return resume;} public void setResume(String v){resume=v;}
    public String getRollNo(){return rollNo;} public void setRollNo(String v){rollNo=v;}
}
