package com.gqshop.kiosk.entrypoint.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerOrderingEntrypointWeb implements CommandLineRunner{

	@RequestMapping("/home")
	public String homePage(){
		System.out.println("entered home web");
		return "index";
	}
	
	@GetMapping("/")
	public String index(Model model) {		
		System.out.println("entered index web");
		return "redirect:home";
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("CustomerOrderingEntrypointWeb bean created");
		
	}
}
