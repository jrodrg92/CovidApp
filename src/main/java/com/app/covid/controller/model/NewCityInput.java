package com.app.covid.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NewCityInput {

	private String nameCountry;
	
	private String nameRegion;
	
	private String nameCity;
			
	private String npopulation;
	
}
