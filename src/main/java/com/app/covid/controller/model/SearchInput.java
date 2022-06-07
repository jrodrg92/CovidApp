package com.app.covid.controller.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor	
public class SearchInput {

	private Date dateInit;
	
	private Date dateEnd;
	
	private boolean byRegion;
	
	private String targetName;	
	
}
