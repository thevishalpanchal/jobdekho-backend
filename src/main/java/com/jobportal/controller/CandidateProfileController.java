package com.jobportal.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.entity.CandidateProfile;
import com.jobportal.entity.User;
import com.jobportal.repository.CandidateProfileRepository;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class CandidateProfileController {

	@Autowired
	private CandidateProfileRepository repo;

	@PostMapping("/upload")

	public CandidateProfile saveProfile(

	    @RequestParam("fullName")
	    String fullName,

	    @RequestParam("email")
	    String email,

	    @RequestParam("headline")
	    String headline,

	    @RequestParam("location")
	    String location,

	    @RequestParam("phone")
	    String phone,

	    @RequestParam("linkedin")
	    String linkedin,

	    @RequestParam("github")
	    String github,

	    @RequestParam("portfolio")
	    String portfolio,

	    @RequestParam("bio")
	    String bio,

	    @RequestParam("skills")
	    String skills,

	    @RequestParam("education")
	    String education,

	    @RequestParam("experience")
	    String experience,

	    @RequestParam("userId")
	    Long userId,

	    @RequestParam(
	    		value = "file",
	    		required = false
	    		)

	    		MultipartFile file,

	    @RequestParam(
	    	    		value = "profileImage",
	    	    		required = false
	    	    		)

	    	    		MultipartFile profileImage
	   

	) throws IOException {

		

		String uploadDir = "C:/uploads/";

		File directory = new File(uploadDir);

		if (!directory.exists()) {

			directory.mkdirs();

		}

		String fileName = null;

		if(

		file != null

		&&

		!file.isEmpty()

		){

		    fileName =
		    file.getOriginalFilename();

		    file.transferTo(

		    new File(
		    uploadDir + fileName
		    )

		    );

		}
		

		CandidateProfile profile;

		Optional<CandidateProfile> existingProfile = repo.findByUserId(userId);
		
		System.out.println(
				repo.findByUserId(userId)
				);

		if (existingProfile.isPresent()) {

			profile = existingProfile.get();

		} else {

			profile = new CandidateProfile();

		}

		profile.setSkills(

				new java.util.ArrayList<>(

				java.util.Arrays.asList(

				skills.split(",")

				)

				)

				);

		profile.setFullName(fullName);

		profile.setEmail(email);

		profile.setHeadline(headline);

		profile.setLocation(location);

		profile.setPhone(phone);

		profile.setLinkedin(linkedin);

		profile.setGithub(github);

		profile.setPortfolio(portfolio);

		profile.setBio(bio);
		
		profile.setEducation(education);

		profile.setExperience(experience);
		
		if(

				profileImage != null

				&&

				!profileImage.isEmpty()

				){

				    String imageName =

				    profileImage
				    .getOriginalFilename();

				    profileImage.transferTo(

				    new File(
				    uploadDir + imageName
				    )

				    );

				    profile.setProfileImage(
				    imageName
				    );

				}

		if(fileName != null){

		    profile.setResume(

		    uploadDir + fileName

		    );

		}

		

		User user = new User();

		user.setId(userId);

		profile.setUser(user);

		return repo.save(profile);

	}
	
	@GetMapping("/{userId}")
	public CandidateProfile getProfile(
	        @PathVariable Long userId){

	    return repo.findByUserId(userId)
	            .orElse(null);

	}
	
}