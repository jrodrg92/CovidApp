package com.app.covid.controller.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class IncidenceInput {

	private String city;
	
	private Date incidenceDate;
	
	private String nincidence;
	
}
