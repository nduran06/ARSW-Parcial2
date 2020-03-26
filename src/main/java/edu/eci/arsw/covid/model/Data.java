package edu.eci.arsw.covid.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private CovidStats covidStats;
	
	public Data(){
		
	}
	
	
	public CovidStats getCovid19Stats() {
		return this.covidStats;
	}
	
	public void setCovid19StatsList(CovidStats covidStats) {
		this.covidStats=covidStats;
	}
	
	
	

}
