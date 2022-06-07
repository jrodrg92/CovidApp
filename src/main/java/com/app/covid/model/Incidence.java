package com.app.covid.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919690150279414316L;

	@JsonProperty
	private Integer nincidence;
	
	@JsonProperty
	private Date fecha;
	
}
