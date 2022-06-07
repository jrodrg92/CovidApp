package com.app.covid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

	public Country create(String nameCountry, String nameRegion, String nameCity, Integer nPopulation) {
		
		// TODO Auto-generated method stub
		Country country= new Country();
		List<Region> region= new ArrayList<Region>();
		List<City> city= new ArrayList<City>();
		List<Incidence> incidences= new ArrayList<Incidence>();

		if(nameRegion==null || nameRegion.length()==0) {
			nameRegion = nameCity;
		}
		
		city.add(new City(nameCity, nPopulation, incidences));
		region.add(new Region(nameRegion, city));
		country.regions = region;
		country.setNameCountry(nameCountry);
		
		return country;
		
	}


	public void addRegion(String nameCountry, String nameRegion, String nameCity, Integer nPopulation) {
		// TODO Auto-generated method stub
		List<City> city= new ArrayList<City>();
		List<Incidence> incidences= new ArrayList<Incidence>();

		if(nameRegion==null || nameRegion.length()==0) {
			nameRegion = nameCity;
		}
		
		city.add(new City(nameCity, nPopulation, incidences));
		this.regions.add(new Region(nameCountry, city));
	}


	public List<City> getIncidenceBydate(Region reg, Date dateInit, Date dateEnd) {
		// TODO Auto-generated method stub
		
		List<City> citiesByRegion = new ArrayList<City>();
		
		for(Region region: this.regions) {
			if(region.equals(reg)) {
				
				region.getCities().forEach(citi -> {
					
					List<Incidence> date =  new ArrayList<Incidence>();
					
					if(citi.getIncidences()!=null) {
						for(Incidence inc: citi.getIncidences()) {
							if(dateInit.getTime()<=inc.getFecha().getTime() && dateEnd.getTime()>=inc.getFecha().getTime()) {
								date.add(inc);
							}
						}
						citiesByRegion.add(new City(citi.getNameCity(), citi.getNpopulation(), date));
					}
					
				});				
				
			}
		}
		
		return citiesByRegion;
		
	}


	public List<Region> getRegions() {
		// TODO Auto-generated method stub
		return regions;
	}
	
}
