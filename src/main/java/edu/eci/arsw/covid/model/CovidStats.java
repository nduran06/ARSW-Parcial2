package edu.eci.arsw.covid.model;

import java.util.List;

public class CovidStats {
	
	private List<Covid19Stats> covid19Stats;
	
	public CovidStats() {
		
	}
	
	public List<Covid19Stats> getCovid19Stats() {
		return this.covid19Stats;
	}
	
	public void setCovid19StatsList(List<Covid19Stats> covid19Stats) {
		this.covid19Stats=covid19Stats;
	}
	

}
