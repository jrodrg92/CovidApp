package com.app.covid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Country implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1605857183578407010L;

	@JsonProperty
	private String nameCountry;
	
	@JsonProperty
	private List<Region> regions;

	public static Country create(String nameCountry, String nameRegion, String nameCity, Integer nPopulation) {
		Country country= new Country();
		List<Region> region= new ArrayList<>();
		List<City> city= new ArrayList<>();
		List<Incidence> incidences= new ArrayList<>();

		if(nameRegion==null || nameRegion.length()==0) {
			nameRegion = nameCity;
		}
		
		city.add(new City(nameCity, nPopulation, incidences));
		region.add(new Region(nameRegion, city));
		country.regions = region;
		country.setNameCountry(nameCountry);
		
		return country;
		
	}


	public void addRegion(String nameRegion, String nameCity, Integer nPopulation) {
		List<City> city= new ArrayList<>();
		List<Incidence> incidences= new ArrayList<>();

		if(nameRegion==null || nameRegion.length()==0) {
			nameRegion = nameCity;
		}
		
		city.add(new City(nameCity, nPopulation, incidences));
		this.regions.add(new Region(nameRegion, city));
	}


	public List<City> getIncidenceBydate(Region reg, Date dateInit, Date dateEnd) {		
		List<City> citiesByRegion = new ArrayList<>();
		
		for(Region region: this.regions) {
			if(region.equals(reg)) {
				
				region.getCities().forEach(citi -> 	
					createIncidencesperCity(dateInit, dateEnd, citiesByRegion, citi)
				);	
				
			}
		}
		
		return citiesByRegion;
		
	}


	private void createIncidencesperCity(Date dateInit, Date dateEnd, List<City> citiesByRegion, City citi) {
		List<Incidence> date =  new ArrayList<>();
		
		if(citi.getIncidences()!=null) {
			for(Incidence inc: citi.getIncidences()) {
				if(dateInit.getTime()<=inc.getFecha().getTime() && dateEnd.getTime()>=inc.getFecha().getTime()) {
					date.add(inc);
				}
			}
			citiesByRegion.add(new City(citi.getNameCity(), citi.getNpopulation(), date));
		}
	}


	public List<Region> getRegions() {
		return regions;
	}
	
}
