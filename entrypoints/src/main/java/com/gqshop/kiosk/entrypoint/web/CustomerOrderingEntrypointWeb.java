package com.gqshop.kiosk.entrypoint.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerOrderingEntrypointWeb implements CommandLineRunner{

	@RequestMapping("/")
	public String index(){
		System.out.println("entered index web");
		return "index";
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("CustomerOrderingEntrypointWeb bean created");
		
	}
}
