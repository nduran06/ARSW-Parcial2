package edu.eci.arsw.covid.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private String lastChecked;
	private List<Covid19Stats> covid19Stats;
	
	public Data(){
		
	}
	
	public String getLastChecked() {
		return this.lastChecked;
	}
	
	public void setLastChecked(String lastChecked) {
		this.lastChecked=lastChecked;
	}
	
	
	public List<Covid19Stats> getCovid19Stats() {
		return this.covid19Stats;
	}
	
	public void setCovid19StatsList(List<Covid19Stats> covid19Stats) {
		this.covid19Stats=covid19Stats;
	}
	
	
	

}
