package edu.eci.arsw.covid.services;

import java.util.List;

import edu.eci.arsw.covid.model.Data;


public interface CovidServices {
	
	public List<Data> getAllCovid();
	
	public List<Data> getCovidByCountry(String name);
	
	
}