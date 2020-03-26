package edu.eci.arsw.covid.services;

import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.covid.model.ApiResponse;
import edu.eci.arsw.covid.model.Country;
import edu.eci.arsw.covid.model.Covid19Stats;
import edu.eci.arsw.covid.model.Data;


public interface CovidServices {
	
	public ArrayList<Country> getAllCovid();
	
	public ArrayList<Country> getCovidByCountry(String name);
	
	
}
