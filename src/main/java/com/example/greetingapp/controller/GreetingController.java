package com.example.greetingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.model.User;
import com.example.greetingapp.service.IGreetingService;


@RestController
@RequestMapping("/greeting")
public class GreetingController {
	private static final String template = "Hello, %s!";

	@Autowired
	private IGreetingService greetingService;

	@GetMapping(value = { "", "/", "/home" })
	public Greeting getGreeting(@RequestParam(value = "fName", defaultValue = "") String firstName,
			@RequestParam(value = "lName", defaultValue = "") String lastName) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return greetingService.addGreeting(user);
	}

	@GetMapping(value = { "/{id}", "/home/{id}" })
	public String getGreeting(@PathVariable long id) {
		return greetingService.getGreetingById(id).getMessage();
	}

	@GetMapping("/getAll")
	public List<Greeting> getAllGreeting() {
		return greetingService.getAllGreetings();
	}

	@PutMapping("/put")
	public Greeting updateGreeting(@RequestParam(value = "id") long id,
			@RequestParam(value = "message", defaultValue = "") String message) {
		return greetingService.updateGreeting(id, message);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteGreeting(@PathVariable long id) {
		return greetingService.deleteGreeting(id);
	}
}