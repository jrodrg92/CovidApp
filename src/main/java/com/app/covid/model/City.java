package com.app.covid.model;

import java.util.List;

import com.app.covid.controller.model.IncidenceInput;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
	
	@JsonProperty
	private String nameCity;
	
	@JsonProperty
	private Integer nPopulation;
	
	@JsonProperty
	private List<Incidence> incidences;

	public void addIncidence(IncidenceInput incidence) {
		// TODO Auto-generated method stub
		incidences.add(new Incidence(incidence.getNIncidence(), incidence.getIncidenceDate()));
	}
	
}
