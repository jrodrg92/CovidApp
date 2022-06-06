package com.app.covid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.service.CovidAppService;

@RestController("/covid/incidence")
public class CovidAppController {

	@Autowired
	private static CovidAppService service;
	
	@GetMapping("/calculate")
	public void simulateIncidence() {
		
		
		
	}
	
}
