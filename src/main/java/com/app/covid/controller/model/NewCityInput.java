package com.app.covid.controller.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class NewCityInput {

	private String nameCountry;
	
	private String nameRegion;
	
	private String nameCity;
			
	private Integer nPopulation;
	
}
