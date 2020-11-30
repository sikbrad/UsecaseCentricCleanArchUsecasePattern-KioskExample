package com.gqshop.kiosk.entrypoint.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerOrderingEntrypointRest implements CommandLineRunner{
	@GetMapping
	public String home() {
		return "API index called";
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CustomerOrderingEntrypointRest bean created");
	}

	
	
}
