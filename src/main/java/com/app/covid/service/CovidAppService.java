package com.app.covid.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.covid.controller.model.IncidenceInput;
import com.app.covid.controller.model.NewCityInput;
import com.app.covid.controller.model.SearchInput;
import com.app.covid.model.Country;

@Service
public interface CovidAppService {

	void addCityAsJson(NewCityInput city);

	List<Country> getAllDAta();

	void addIncidenceToCity(String city, IncidenceInput incidence);

	BigDecimal calculateIncidence(SearchInput input);

}
