package com.app.covid.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidence {

	@JsonProperty
	private Integer nincidence;
	
	@JsonProperty
	private Date fecha;
	
}
