package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jobportal.entity.User;
import com.jobportal.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserRepository repo;

	
	
    @PostMapping("/register")
	public User register(@RequestBody User user) {

		return repo.save(user);

	}

	@PostMapping("/login")
	public Object login(@RequestBody User user) {

		User existingUser = repo.findByEmail(user.getEmail());

		if (existingUser == null) {

			return "User not registered";

		}

		if (existingUser.getPassword().equals(user.getPassword())) {

			return existingUser;

		}

		return "Invalid Password";
	}

	@GetMapping
	public List<User> getUsers() {
		return repo.findAll();

	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		repo.deleteById(id);

	}
}