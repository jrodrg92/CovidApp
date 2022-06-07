package com.app.covid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.app.covid.controller.model.IncidenceInput;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9126875998711064866L;

	@JsonProperty
	private String nameCity;
	
	@JsonProperty
	private int npopulation;
	
	@JsonProperty
	private List<Incidence> incidences;

	public void addIncidence(IncidenceInput incidence) {
		if(incidences==null) {
			incidences= new ArrayList<>();
		}
		incidences.add(new Incidence(Integer.parseInt(incidence.getNincidence()), incidence.getIncidenceDate()));
	}
	
}
