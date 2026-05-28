package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobController {

	@Autowired
	private JobRepository repo;

	@GetMapping
	public List<Job> getJobs() {

		return repo.findAll();

	}

	@GetMapping("/employer/{id}")
	public List<Job> getEmployerJobs(@PathVariable Long id) {

		return repo.findByEmployerId(id);

	}

	@PostMapping
	public Job addJob(@RequestBody Job job) {
		
		System.out.println(
				job.getRequiredSkills()
				);
		return repo.save(job);
	}

	@DeleteMapping("/{id}")
	public void deleteJob(

			@PathVariable Long id

	) {
		applicationRepo.deleteByJobId(id);
		repo.deleteById(id);

	}
}
