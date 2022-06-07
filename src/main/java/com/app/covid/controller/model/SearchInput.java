package com.app.covid.controller.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor	
public class SearchInput {

	private Date dateInit;
	
	private Date dateEnd;
	
	private boolean byRegion;
	
	private String targetName;	
	
}
