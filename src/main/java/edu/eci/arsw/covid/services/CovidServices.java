package edu.eci.arsw.covid.services;

import java.util.List;

import edu.eci.arsw.covid.model.ApiResponse;
import edu.eci.arsw.covid.model.Data;


public interface CovidServices {
	
	public ApiResponse getAllCovid();
	
	public ApiResponse getCovidByCountry(String name);
	
	
}
