package com.app.covid.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class City {

	private String country;
	
	private String region;
	
	private String cityName;
	
	private Integer nPopulation;
	
}
