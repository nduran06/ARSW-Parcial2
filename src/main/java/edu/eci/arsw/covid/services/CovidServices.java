package edu.eci.arsw.covid.services;

import java.util.List;

import edu.eci.arsw.covid.model.ApiResponse;
import edu.eci.arsw.covid.model.Covid19Stats;
import edu.eci.arsw.covid.model.Data;


public interface CovidServices {
	
	public List<Covid19Stats> getAllCovid();
	
	public List<Covid19Stats> getCovidByCountry(String name);
	
	
}
