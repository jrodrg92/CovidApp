package com.app.covid.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.app.covid.controller.model.IncidenceInput;
import com.app.covid.controller.model.NewCityInput;
import com.app.covid.controller.model.SearchInput;
import com.app.covid.model.Country;
import com.app.covid.service.CovidAppService;

@RestController
@RequestMapping("/covid/incidence")
public class CovidAppController<T> {

	@Autowired(required = false)
	private CovidAppService service;
	
	@PostMapping("/addCity")
	public void simulateIncidence(@RequestBody NewCityInput city) {
		
		service.addCityAsJson(city);
		
	}
	
	@PostMapping("/addIncidence")
	public ResponseEntity<T> addIncidence ( @RequestBody IncidenceInput input ) throws RestClientException {
		
		if(!service.addIncidenceToCity(input.getCity(), input)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}

	}
	
	@GetMapping("/values")
	public List<Country> getAllData(){
		return service.getAllDAta();
	}
	
	@GetMapping("/calculate")
	public BigDecimal getIncidence( @RequestBody SearchInput input ) {
		return service.calculateIncidence(input);
	}
	
}
