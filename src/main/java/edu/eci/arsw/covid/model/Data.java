package edu.eci.arsw.covid.model;

import java.util.List;

public class Data {
	
	private String lastChecked;
	private List<Covid19Stats> covid19StatsList;
	
	public Data(){
		
	}
	
	public String getLastChecked() {
		return this.lastChecked;
	}
	
	public void setLastChecked(String lastChecked) {
		this.lastChecked=lastChecked;
	}
	
	public List<Covid19Stats> getCovid19StatsList() {
		return this.covid19StatsList;
	}
	
	public void setCovid19StatsList(List<Covid19Stats> covid19StatsList) {
		this.covid19StatsList=covid19StatsList;
	}
	

}
