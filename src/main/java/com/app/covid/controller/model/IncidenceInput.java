package com.app.covid.controller.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class IncidenceInput {

	private String city;
	
	private Date incidenceDate;
	
	private String nincidence;
	
}
