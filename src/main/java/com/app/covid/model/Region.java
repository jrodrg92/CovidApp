package com.app.covid.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region{

	@JsonProperty
	private String nameRegion;
	
	@JsonProperty
	private List<City> cities;

	public void addCity(String nameCountry, String nameRegion, String nameCity, Integer nPopulation) {
		// TODO Auto-generated method stub
		List<Incidence> incidences= new ArrayList<Incidence>();
		cities.add(new City(nameCity, nPopulation, incidences));
	}

	public City getCityByName(String nameCity) {
		City citie = null;
		for(City citi: cities) {
			if(citi.getNameCity().trim().equals(nameCity.trim())) {
				citie=citi;
				break;
			}
		}
		return citie;
	}
	
}
