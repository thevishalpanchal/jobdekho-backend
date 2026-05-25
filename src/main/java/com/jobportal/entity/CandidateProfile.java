package com.jobportal.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate_profiles")

public class CandidateProfile {

    @Id
    @GeneratedValue(
    strategy = GenerationType.IDENTITY
    )

    private Long id;

    // SKILLS

    @ElementCollection
    private List<String> skills;

    // EDUCATION

    private String education;

    // EXPERIENCE

    private String experience;

    // RESUME URL / PATH

    private String resume;

    // USER

    @OneToOne

    @JoinColumn(name = "user_id")

    private User user;

    // CONSTRUCTOR

    public CandidateProfile() {
    }
    
    
    private String fullName;

    private String email;

    private String headline;

    private String location;

    private String phone;

    private String linkedin;

    private String github;

    private String portfolio;

    @Column(length = 1500)

    private String bio;

    private String profileImage;

    // ID

    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    // SKILLS

    public List<String> getSkills() {

        return skills;

    }

    public void setSkills(
    List<String> skills
    ) {

        this.skills = skills;

    }

    // EDUCATION

    public String getEducation() {

        return education;

    }

    public void setEducation(
    String education
    ) {

        this.education = education;

    }

    // EXPERIENCE

    public String getExperience() {

        return experience;

    }

    public void setExperience(
    String experience
    ) {

        this.experience = experience;

    }

    // RESUME

    public String getResume() {

        return resume;

    }

    public void setResume(
    String resume
    ) {

        this.resume = resume;

    }

    // USER

    public User getUser() {

        return user;

    }

    public void setUser(
    User user
    ) {

        this.user = user;

    }

}