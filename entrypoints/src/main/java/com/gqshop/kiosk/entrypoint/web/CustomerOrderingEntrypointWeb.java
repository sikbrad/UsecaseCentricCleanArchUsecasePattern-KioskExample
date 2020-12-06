package com.gqshop.kiosk.entrypoint.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	

	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping("/home")
	public String homePage(Model model){		
		String currProfiles = String.join(",", env.getActiveProfiles());
		if(currProfiles.length() == 0) {
			currProfiles = "(profile undecided - it is set as default)";
		}		
		model.addAttribute("profile", currProfiles);
		return "home";
	}
	
	
	@GetMapping("/")
	public String index(Model model) {		
		logger.info("entered index web");
		return "redirect:home";
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info("CustomerOrderingEntrypointWeb bean created");
		
	}
}
