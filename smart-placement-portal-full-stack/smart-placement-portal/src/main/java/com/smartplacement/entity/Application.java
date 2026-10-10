package com.smartplacement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "applications", uniqueConstraints = @UniqueConstraint(columnNames={"student_id","drive_id"}))
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false, fetch=FetchType.EAGER) private Student student;
    @ManyToOne(optional=false, fetch=FetchType.EAGER) private PlacementDrive drive;
    private LocalDate appliedDate;
    @Column(nullable=false) private String status;
    public Application() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
    public PlacementDrive getDrive(){return drive;} public void setDrive(PlacementDrive v){drive=v;}
    public LocalDate getAppliedDate(){return appliedDate;} public void setAppliedDate(LocalDate v){appliedDate=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
}
