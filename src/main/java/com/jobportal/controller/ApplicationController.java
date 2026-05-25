package com.jobportal.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entity.Application;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.service.ATSService;
import com.jobportal.entity.CandidateProfile;
import com.jobportal.repository.CandidateProfileRepository;

@RestController
@RequestMapping("/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationRepository repo;
    
    @Autowired
    private ATSService atsService;

    @Autowired
    private CandidateProfileRepository
    profileRepo;

    @PostMapping
    public Application applyJob(
            @RequestBody Application application){

    	CandidateProfile profile =profileRepo.findByUserId(application.getCandidate().getId()).orElse(null);

    			if(profile != null){

    			    int score =

    			    atsService.calculateATS(

    			        application
    			        .getJob()
    			        .getRequiredSkills(),

    			        profile.getSkills()

    			    );

    			    application.setAtsScore(
    			        score
    			    );

    			}

    			return repo.save(application);

    }
    
    @GetMapping("/employer/{id}")

    public List<Application>
    getApplicants(

    @PathVariable Long id

    ){

        return repo
        .findByJobEmployerId(id);

    }
}