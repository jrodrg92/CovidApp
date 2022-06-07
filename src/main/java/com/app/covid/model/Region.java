package com.app.covid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6185452501056495657L;

	@JsonProperty
	private String nameRegion;
	
	@JsonProperty
	private List<City> cities;

	public void addCity(String nameCity, Integer nPopulation) {
		List<Incidence> incidences= new ArrayList<>();
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
