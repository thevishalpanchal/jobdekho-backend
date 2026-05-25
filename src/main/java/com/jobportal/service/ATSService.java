package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ATSService {

    public int calculateATS(

    List<String> requiredSkills,

    List<String> candidateSkills

    ) {

        // CHECK NULL

        if (

        requiredSkills == null

        ||

        requiredSkills.isEmpty()

        ||

        candidateSkills == null

        ||

        candidateSkills.isEmpty()

        ) {

            return 0;

        }

        int matched = 0;

        // LOOP REQUIRED SKILLS

        for (

        String skill :
        requiredSkills

        ) {

            // MATCH CHECK

            boolean found =

            candidateSkills
            .stream()

            .anyMatch(

            s ->

            s.trim()

            .equalsIgnoreCase(

            skill.trim()

            )

            );

            if(found){

                matched++;

            }

        }

        // CALCULATE %

        return (

        matched * 100

        )

        /

        requiredSkills.size();

    }

}