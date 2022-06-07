package com.app.covid.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;

import com.app.covid.controller.model.NewCityInput;
import com.app.covid.model.City;
import com.app.covid.model.Country;
import com.app.covid.model.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CovidAppAutoConfig {
	
	private List<Country> countries;
	
	private final String filePath = "static/data.json";
	
	public CovidAppAutoConfig() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		
		File f = getFile();
		StringBuffer data = extracted(f);
	        
        System.out.println(data.toString());
        
        ObjectMapper objectMapper = new ObjectMapper();

		countries = Arrays.asList(objectMapper.readValue(data.toString(), Country[].class));

	}

	private StringBuffer extracted(File f) {
		List<String> lines;
		StringBuffer data = new StringBuffer();
		try {
		    lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
		    lines.forEach(data::append);
		    //data.append(lines.toString());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return data;
	}

	private File getFile() throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(this.filePath);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + this.filePath);
        }
        else {
        	try {
				return new File(resource.toURI());
			} catch (URISyntaxException e) {
				throw new URISyntaxException("", "");
			}
        }
	}
	
	/**
	 * 
	 * @return all the cities with their data
	 */
	public List<Country> getAllData() {
		// TODO Auto-generated method stub
		return countries;
	}

	public boolean exist(NewCityInput city) {

		boolean exist = true;
		
		for(Country country: countries) {
			if(country.getNameCountry().trim().equals(city.getNameCountry().trim())) {
				exist = existRegion(country, city);
				break;
			}
			else {
				countries.add(country.create(city.getNameCountry(), city.getNameRegion(), city.getNameCity(), Integer.parseInt(city.getNpopulation())));
				exist = false;
				break;
			}
		}
		
		return exist;
		
	}

	private boolean existRegion(Country country, NewCityInput city) {

		boolean exist = true;
		
		for(Region region: country.getRegions()) {
			if(region.getNameRegion().trim().equals(city.getNameRegion().trim())) {
				exist = existCity(country, region, city);
				break;
			}
			else {
				country.addRegion(city.getNameCountry(), city.getNameRegion(), city.getNameCity(), Integer.parseInt(city.getNpopulation()));
				exist = false;
				break;
			}
			
		}
		
		return exist;
		
	}

	private boolean existCity(Country country, Region region, NewCityInput city) {
		// TODO Auto-generated method stub
		
		boolean exist = true;
		
		int cont = 0;
			
		City cit= region.getCityByName(city.getNameCity());
		
		if(cit != null) {
			exist=true;
		}
		else {
			region.addCity(city.getNameCountry(), city.getNameRegion(), city.getNameCity(), Integer.parseInt(city.getNpopulation()));
			exist = false;
		}	
		
		
		return exist;
		
	}

	public City getCity(String city) {
		// TODO Auto-generated method stub
		
		City cit= null;
		
		for(Country countri: countries) {
			for(Region region: countri.getRegions()) {
				for(City citi: region.getCities()) {
					if(city.trim().equals(citi.getNameCity().trim())) {
						cit=citi; 
						break;
					}
				}
			}
		}
		
		return cit;
	}
	
	public Region getRegion(String region) {
		// TODO Auto-generated method stub
		
		Region reg= null;
		
		for(Country countri: countries) {
			for(Region regi: countri.getRegions()) {
					if(regi.getNameRegion().trim().equals(region.trim())) {
						reg=regi; 
						break;
				}
			}
		}
		
		return reg;
	}

	public Object getCitiesByRegion(String targetName, Date dateInit, Date dateEnd) {
		// TODO Auto-generated method stub
		Country country= getCountryByReg(targetName);
		Region reg= getRegion(targetName);
		if(reg==null) {
			throw new RestClientException("The Region doesn't exists already");
		}
		if(dateEnd==null) {
			country.getIncidenceBydate(reg, dateInit, dateInit);
		}
		else {
			country.getIncidenceBydate(reg, dateInit, dateEnd);
		}
		
		return null;
	}

	private Country getCountryByReg(String targetName) {
		Country count= null;
		
		for(Country countri: countries) {
			for(Region regi: countri.getRegions()) {
					if(regi.getNameRegion().trim().equals(targetName.trim())) {
						count=countri; 
						break;
				}
			}
		}
		
		return count;
	}	
	
}
