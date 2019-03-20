package com.codependent.mutualauth.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@GetMapping("/")
	public String home(Principal principal){

		System.out.println("see outpuut from controller");
		System.out.println(principal.getName());
		String format = String.format("Hello %s!", principal.getName());
		return format;
	}
	
}
