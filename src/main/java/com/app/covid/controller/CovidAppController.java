package com.app.covid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.covid.controller.model.IncidenceInput;
import com.app.covid.controller.model.NewCityInput;
import com.app.covid.model.Country;
import com.app.covid.service.CovidAppService;

@RestController
@RequestMapping("/covid/incidence")
public class CovidAppController {

	@Autowired(required = false)
	private CovidAppService service;
	
	@PostMapping("/addCity")
	public void simulateIncidence(@RequestBody NewCityInput city) {
		
		service.addCityAsJson(city);
		
	}
	
	@PostMapping("/addIncidence/{city}")
	public void addIncidence (
				@PathVariable("city") String city,
				@RequestBody IncidenceInput input ) {
		
		service.addIncidenceToCity(city, input);
	
	}
	
	@GetMapping("/values")
	public List<Country> getAllData(){
		return service.getAllDAta();
	}
	
}
