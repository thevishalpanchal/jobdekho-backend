package com.jobportal.controller;

import java.io.IOException;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;

@RestController
@RequestMapping("/ats")
@CrossOrigin("*")

public class ATSController {

    @Autowired
    private JobRepository jobRepo;

    @PostMapping("/scan")

    public Map<String,Object> scanResume(

    @RequestParam("file")
    MultipartFile file

    ) throws IOException {

        // PDF CHECK

        if(

        !file.getContentType()

        .equals("application/pdf")

        ){

            throw new RuntimeException(
            "Only PDF files allowed"
            );

        }

        // EXTRACT PDF TEXT

        PDDocument document =

        PDDocument.load(
        file.getInputStream()
        );

        PDFTextStripper stripper =
        new PDFTextStripper();

        String resumeText =

        stripper.getText(document)
        .toLowerCase();

        document.close();

        // GET ALL JOBS

        List<Job> jobs =
        jobRepo.findAll();

        // STORE UNIQUE SKILLS

        Set<String> allSkills =
        new HashSet<>();

        for(Job job : jobs){

            if(

            job.getRequiredSkills()
            != null

            ){

                allSkills.addAll(
                job.getRequiredSkills()
                );

            }

        }

        // ATS LOGIC

        List<String> matched =
        new ArrayList<>();

        List<String> missing =
        new ArrayList<>();

        for(String skill : allSkills){

            if(

            resumeText.contains(

            skill.toLowerCase()

            )

            ){

                matched.add(skill);

            }

            else{

                missing.add(skill);

            }

        }

        // SCORE

        int score = 0;

        if(!allSkills.isEmpty()){

            score =

            (matched.size() * 100)

            /

            allSkills.size();

        }

        // RESPONSE

        Map<String,Object>
        response = new HashMap<>();

        response.put(
        "score",
        score
        );

        response.put(
        "matchedSkills",
        matched
        );

        response.put(
        "missingSkills",
        missing
        );

        return response;

    }

}