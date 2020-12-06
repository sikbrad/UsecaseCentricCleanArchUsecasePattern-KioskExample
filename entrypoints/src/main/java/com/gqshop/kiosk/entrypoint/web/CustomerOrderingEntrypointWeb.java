package com.gqshop.kiosk.entrypoint.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerOrderingEntrypointWeb implements CommandLineRunner{

	@Autowired
	Environment env;
	
//	@RequestMapping("/home")
//	public String homePage(){
//		System.out.println("entered home web");
//		System.out.println(String.format("With profile of : %s", env.getProperty("gqshop.propertyname")));
//		return "index";
//	}
	@RequestMapping("/home")
	public String homePage(Model model){
//		System.out.println("entered home web");
//		System.out.println(String.format("With profile of : %s", env.getProperty("gqshop.propertyname")));
//		return "index";
		
		String currProfiles = String.join(",", env.getActiveProfiles());
		if(currProfiles.length() == 0) {
			currProfiles = "(profile undecided - it is set as default)";
		}
		
		model.addAttribute("profile", currProfiles);
		return "home";
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
