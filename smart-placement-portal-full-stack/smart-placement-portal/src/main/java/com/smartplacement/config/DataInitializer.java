package com.smartplacement.config;

import com.smartplacement.entity.*;
import com.smartplacement.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(StudentRepository students, PlacementOfficerRepository officers, CompanyRepository companies, PlacementDriveRepository drives, ApplicationRepository apps, PasswordEncoder encoder){
        return args -> {
            if(students.count()==0){
                Student s=new Student();s.setName("Om Yadav");s.setEmail("omyadav@gmail.com");s.setPasswordHash(encoder.encode("student123"));s.setPhone("9876543210");s.setBranch("Computer Engineering");s.setYear("Second Year");s.setCgpa(8.2);s.setSkills("Java, React, Spring Boot, MySQL");s.setRollNo("CE2026001");students.save(s);
                Student p=new Student();p.setName("Priya Shah");p.setEmail("priya.shah@gmail.com");p.setPasswordHash(encoder.encode("priya123"));p.setBranch("AI & Data Science");p.setYear("Third Year");p.setCgpa(9.1);p.setSkills("Python, Machine Learning, SQL");students.save(p);
                Student a=new Student();a.setName("Arjun Patel");a.setEmail("arjun.patel@gmail.com");a.setPasswordHash(encoder.encode("arjun123"));a.setBranch("Information Technology");a.setYear("Final Year");a.setCgpa(8.7);a.setSkills("JavaScript, React, Node.js");students.save(a);
            }
            if(officers.count()==0){PlacementOfficer o=new PlacementOfficer();o.setName("Placement Administrator");o.setEmail("placement@gmail.com");o.setPasswordHash(encoder.encode("placement123"));o.setRole("Placement Officer");officers.save(o);}
            if(companies.count()==0){
                String[][] data={{"TCS","Information Technology","Mumbai","hr@tcs.com"},{"Infosys","Data & Analytics","Pune","careers@infosys.com"},{"Accenture","Artificial Intelligence","Bangalore","jobs@accenture.com"},{"Capgemini","Software Development","Pune","hr@capgemini.com"},{"Wipro","Cloud Services","Mumbai","careers@wipro.com"}};
                for(String[] x:data){Company c=new Company();c.setName(x[0]);c.setIndustry(x[1]);c.setLocation(x[2]);c.setHrEmail(x[3]);c.setWebsite(x[0].toLowerCase()+".com");c.setDescription(x[0]+" campus recruitment opportunity.");companies.save(c);}
            }
            if(drives.count()==0){
                Company t=companies.findAll().stream().filter(c->c.getName().equals("TCS")).findFirst().orElseThrow();Company i=companies.findAll().stream().filter(c->c.getName().equals("Infosys")).findFirst().orElseThrow();Company ac=companies.findAll().stream().filter(c->c.getName().equals("Accenture")).findFirst().orElseThrow();
                add(drives,t,"Java Developer","₹8 LPA","Mumbai",7.0,"CSE,IT,AI-DS",8,LocalDate.of(2026,10,15),"Java development role for software engineering candidates.");
                add(drives,i,"Data Analyst","₹9 LPA","Pune",7.5,"CSE,IT,AI-DS",10,LocalDate.of(2026,10,18),"Data analysis and business intelligence opportunity.");
                add(drives,ac,"AI/ML Intern","₹6 LPA","Bangalore",7.0,"CSE,AI-DS",5,LocalDate.of(2026,10,20),"Artificial intelligence and machine learning internship.");
            }
            if(apps.count()==0){
                Student om=students.findByEmail("omyadav@gmail.com").orElseThrow();
                Student priya=students.findByEmail("priya.shah@gmail.com").orElseThrow();
                Student arjun=students.findByEmail("arjun.patel@gmail.com").orElseThrow();
                PlacementDrive tcs=drives.findAll().stream().filter(d->d.getCompany().getName().equals("TCS")).findFirst().orElseThrow();
                PlacementDrive inf=drives.findAll().stream().filter(d->d.getCompany().getName().equals("Infosys")).findFirst().orElseThrow();
                PlacementDrive acc=drives.findAll().stream().filter(d->d.getCompany().getName().equals("Accenture")).findFirst().orElseThrow();
                addApp(apps,om,tcs,"Shortlisted",LocalDate.of(2026,9,28));
                addApp(apps,priya,inf,"Selected",LocalDate.of(2026,9,27));
                addApp(apps,arjun,acc,"Applied",LocalDate.of(2026,9,26));
            }
        };
    }
    private void add(PlacementDriveRepository r,Company c,String role,String pkg,String loc,double cgpa,String branches,int openings,LocalDate deadline,String desc){PlacementDrive d=new PlacementDrive();d.setCompany(c);d.setJobRole(role);d.setPackageAmount(pkg);d.setLocation(loc);d.setMinCgpa(cgpa);d.setEligibleBranches(branches);d.setOpenings(openings);d.setDeadline(deadline);d.setDriveDate(deadline.plusDays(7));d.setStatus("Active");d.setDescription(desc);r.save(d);}
    private void addApp(ApplicationRepository r,Student s,PlacementDrive d,String status,LocalDate date){Application a=new Application();a.setStudent(s);a.setDrive(d);a.setStatus(status);a.setAppliedDate(date);r.save(a);}
}
