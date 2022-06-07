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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;

import com.app.covid.controller.model.NewCityInput;
import com.app.covid.model.City;
import com.app.covid.model.Country;
import com.app.covid.model.Incidence;
import com.app.covid.model.Region;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CovidAppAutoConfig {
	
    protected final Logger log = LogManager.getLogger();
	
	private List<Country> countries;
	
	private static final String FILE_PATH = "static/data.json";
	
	public CovidAppAutoConfig() throws URISyntaxException, JsonProcessingException {
		
		countries= new ArrayList<>();
		File f = getFile();
		StringBuilder data = extracted(f);
	        
        log.info(data);
        
        ObjectMapper objectMapper = new ObjectMapper();

        for(Country con: Arrays.asList(objectMapper.readValue(data.toString(), Country[].class))) {
        	countries.add(con);
        }

	}

	private StringBuilder extracted(File f) {
		List<String> lines;
		StringBuilder data = new StringBuilder();
		try {
		    lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
		    lines.forEach(data::append);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return data;
	}

	private File getFile() throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(FILE_PATH);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + FILE_PATH);
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
		return countries;
	}

	public boolean exist(NewCityInput city) {

		boolean exist = true;
		
		for(Country country: countries) {
			if(country.getNameCountry().toUpperCase().trim().equals(city.getNameCountry().toUpperCase().trim())) {
				exist = existRegion(country, city);
				break;
			}
			else {
				exist = false;
			}
		}
		
		if(!exist) {
			countries.add(Country.create(city.getNameCountry(), city.getNameRegion(), city.getNameCity(), Integer.parseInt(city.getNpopulation())));
		}
		
		return exist;
		
	}

	private boolean existRegion(Country country, NewCityInput city) {

		boolean exist = true;
		
		Region reg= getRegion(city.getNameRegion());
		if(reg!=null) {
			exist = existCity(reg, city);
		}
		else {
			country.addRegion(city.getNameRegion(), city.getNameCity(), Integer.parseInt(city.getNpopulation()));
			exist = false;
		}
		
		return exist;
		
	}

	private boolean existCity(Region region, NewCityInput city) {
	
		boolean exist = true;
					
		City cit= region.getCityByName(city.getNameCity());
		
		if(cit != null) {
			exist=true;
		}
		else {
			region.addCity(city.getNameCity(), Integer.parseInt(city.getNpopulation()));
			exist = false;
		}	
		
		
		return exist;
		
	}

	public City getCity(String city) {		
		City cit= null;
		
		for(Country countri: countries) {
			for(Region region: countri.getRegions()) {
				for(City citi: region.getCities()) {
					if(city.toUpperCase().trim().equals(citi.getNameCity().toUpperCase().trim())) {
						cit=citi; 
						break;
					}
				}
			}
		}
		
		return cit;
	}
	
	public Region getRegion(String region) {
		
		Region reg= null;
		
		for(Country countri: countries) {
			for(Region regi: countri.getRegions()) {
					if(regi.getNameRegion().toUpperCase().trim().equals(region.toUpperCase().trim())) {
						reg=regi; 
						break;
				}
			}
		}
		
		return reg;
	}

	public List<City> getCitiesByRegion(String targetName, Date dateInit, Date dateEnd) {
		Country country= getCountryByReg(targetName);
		Region reg= getRegion(targetName);
		List<City> cities= new ArrayList<>();
		if(reg==null) {
			throw new RestClientException("The Region doesn't exists already");
		}
		if(country!=null) {
			if(dateEnd==null) {
				cities= country.getIncidenceBydate(reg, dateInit, dateInit);
			}
			else {
				cities= country.getIncidenceBydate(reg, dateInit, dateEnd);
			}
		}
		
		return cities;
		
	}

	private Country getCountryByReg(String targetName) {
		Country count= null;
		
		for(Country countri: countries) {
			for(Region regi: countri.getRegions()) {
					if(regi.getNameRegion().toUpperCase().trim().equals(targetName.toUpperCase().trim())) {
						count=countri; 
						break;
				}
			}
		}
		
		return count;
	}

	public List<Incidence> getIncidenceOfcity(String targetName, Date dateInit, Date dateEnd) {
		City cit= getCity(targetName);
		if(cit==null) {
			throw new RestClientException("The city doesn't exists already");
		}
		if(cit.getIncidences()==null) {
			return new ArrayList<>();
		}
		else {
			List<Incidence> date =  new ArrayList<>();

			for(Incidence inc: cit.getIncidences()) {
				if(dateInit.getTime()<=inc.getFecha().getTime() && dateEnd.getTime()>=inc.getFecha().getTime()) {
					date.add(inc);
				}
			}
			
			return date;
		}
	}	
	
}
