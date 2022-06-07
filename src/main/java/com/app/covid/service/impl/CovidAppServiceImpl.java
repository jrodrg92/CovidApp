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
import com.app.covid.service.CovidAppService;

@Service
public class CovidAppServiceImpl implements CovidAppService {

	@Autowired(required = false)
	private CovidAppAutoConfig config;
	
	public CovidAppServiceImpl() {
		
	}
	
	@Override
	public void addCityAsJson(NewCityInput city) {
		// TODO Auto-generated method stub
		if(config.exist(city)) {
			throw new RestClientException("The city exists already");
		}
		
	}

	@Override
	public List<Country> getAllDAta() {
		// TODO Auto-generated method stub
		return config.getAllData();
	}

	@Override
	public void addIncidenceToCity(String city, IncidenceInput incidence) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

		if(input.isByRegion()) {
			calculeIncidenceByRegion(config.getCitiesByRegion(input.getTargetName(), input.getDateInit(), input.getDateEnd()));
		}
		
		return null;
		
	}

	private void calculeIncidenceByRegion(Object citiesByRegion) {
		// TODO Auto-generated method stub
		
	}
	

}
