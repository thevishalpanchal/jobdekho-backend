package com.jobportal.entity;
import com.jobportal.entity.User;
import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private String location;
    private String salary;
    
    @ElementCollection
    private List<String> requiredSkills;

    @Column(length = 1000)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "employer_id")
    @JsonIgnoreProperties({"password"})
    private User employer;
    
    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<String>
    getRequiredSkills() {

        return requiredSkills;

    }

    public void setRequiredSkills(

    List<String> requiredSkills

    ) {

        this.requiredSkills =
        requiredSkills;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}