package com.smartplacement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="placement_results")
public class PlacementResult {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @OneToOne(optional=false, fetch=FetchType.EAGER) private Application application;
    private LocalDate joiningDate;
    private String finalPackage;
    private String result;
    public PlacementResult() {}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Application getApplication(){return application;} public void setApplication(Application v){application=v;}
    public LocalDate getJoiningDate(){return joiningDate;} public void setJoiningDate(LocalDate v){joiningDate=v;}
    public String getFinalPackage(){return finalPackage;} public void setFinalPackage(String v){finalPackage=v;}
    public String getResult(){return result;} public void setResult(String v){result=v;}
}
