package com.app.covid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.app.covid.controller.model.IncidenceInput;
import com.app.covid.controller.model.NewCityInput;
import com.app.covid.controller.model.SearchInput;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CovidApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getAllDataTest() {
	    try {
			mockMvc.perform(get("/covid/incidence/values"))
			        .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void getAllDataIncidenceByRegion() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		
		SearchInput in= new SearchInput();
		in.setByRegion(true);
		in.setTargetName("Andalucia");

	    try {
			in.setDateInit(formatter.parse("2021-12-11"));
			in.setDateEnd(formatter.parse("2021-12-15"));
	    	this.mockMvc.perform(
		            get("/covid/incidence/calculate")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(in)))
		            .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void getAllDataIncidenceByCity() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		
		SearchInput in= new SearchInput();
		in.setByRegion(false);
		in.setTargetName("Malaga");
	    try {
	    	in.setDateInit(formatter.parse("2021-12-11"));
			in.setDateEnd(formatter.parse("2021-12-15"));
			this.mockMvc.perform(
		            get("/covid/incidence/calculate")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(in)))
		            .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void addCountryTest() {
		
		NewCityInput n= new NewCityInput();
		n.setNameCountry("Portugal");
		n.setNameRegion("Oporto");
		n.setNameCity("Oporto");
		n.setNpopulation("1000000");
	    try {
			this.mockMvc.perform(
		            post("/covid/incidence/addCity")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(n)))
		            .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test
	void addRegionTest() {
		
		NewCityInput n= new NewCityInput();
		n.setNameCountry("Portugal");
		n.setNameRegion("Lisboa");
		n.setNameCity("Lisboa");
		n.setNpopulation("1000000");
	    try {
			this.mockMvc.perform(
		            post("/covid/incidence/addCity")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(n)))
		            .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test
	void addCityTest() {
		
		NewCityInput n= new NewCityInput();
		n.setNameCountry("Portugal");
		n.setNameRegion("Lisboa");
		n.setNameCity("Coimbra");
		n.setNpopulation("1000000");
	    try {
			this.mockMvc.perform(
		            post("/covid/incidence/addCity")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(n)))
		            .andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test
	void addIncidenceTest() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

		
		IncidenceInput n= new IncidenceInput();
		n.setCity("Oporto");
		n.setNincidence("1000000");
	    try {
	    	n.setIncidenceDate(formatter.parse("2021-12-11"));
			this.mockMvc.perform(
		            post("/covid/incidence/addIncidence")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(n)))
		            .andExpect(status().isAccepted());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Test
	void addIncidenceErrorTest() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

		
		IncidenceInput n= new IncidenceInput();
		n.setCity("Coimbra");
		n.setNincidence("1000000");
	    try {
	    	n.setIncidenceDate(formatter.parse("2021-12-11"));
			this.mockMvc.perform(
		            post("/covid/incidence/addIncidence")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(new ObjectMapper().writeValueAsString(n)))
		            .andExpect(status().isConflict());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
}
