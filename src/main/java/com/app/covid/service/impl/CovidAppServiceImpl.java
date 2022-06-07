package com.app.covid.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.app.covid.config.CovidAppAutoConfig;
import com.app.covid.controller.model.IncidenceInput;
import com.app.covid.controller.model.NewCityInput;
import com.app.covid.controller.model.SearchInput;
import com.app.covid.model.City;
import com.app.covid.model.Country;
import com.app.covid.model.Incidence;
import com.app.covid.service.CovidAppService;

@Service
public class CovidAppServiceImpl implements CovidAppService {

	@Autowired(required = false)
	private CovidAppAutoConfig config;
	
	public CovidAppServiceImpl() {
		/**/
	}
	
	@Override
	public void addCityAsJson(NewCityInput city) {
		if(config.exist(city)) {
			throw new RestClientException("The city exists already");
		}
		
	}

	@Override
	public List<Country> getAllDAta() {
		return config.getAllData();
	}

	@Override
	public void addIncidenceToCity(String city, IncidenceInput incidence) {
		City citi= config.getCity(city);
		if(citi==null) {
			throw new RestClientException("The city doesn't exists already");
		}
		else {
			citi.addIncidence(incidence);
		}
	}

	@Override
	public BigDecimal calculateIncidence(SearchInput input) {
		int resultado=0;
		if(input.isByRegion()) {
			resultado = calculeIncidenceByRegion(config.getCitiesByRegion(input.getTargetName(), input.getDateInit(), input.getDateEnd()));
		}
		else {
			resultado= getTotalByCity(config.getIncidenceOfcity(input.getTargetName(), input.getDateInit(), input.getDateEnd()));
		}
		
		return new BigDecimal(resultado);
		
	}

	private int calculeIncidenceByRegion(List<City> citiesByRegion) {
		int totalbyregion=0;
		for(City citi: citiesByRegion) {
			totalbyregion+= getTotalByCity(citi.getIncidences());
		}
		
		return totalbyregion;
	}

	private int getTotalByCity(List<Incidence> incidences) {		
		int totalbyCity=0;
		
		if(incidences==null) {
			return 0;
		}
		
		for(Incidence inc: incidences) {
			totalbyCity+=inc.getNincidence();
		}
		
		return totalbyCity;
	}
	

}
