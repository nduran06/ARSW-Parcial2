package edu.eci.arsw.covid.model;


public class Country {
	
	private String name;
	private Integer confirmed;
	private Integer deaths;
	private Integer recovered;
	
	public Country(String name,Integer confirmed, Integer deaths, Integer recovered) {
		this.name=name;
		this.confirmed=confirmed;
		this.deaths=deaths;
		this.recovered=recovered;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public Integer getConfirmed() {
		return this.confirmed;
	}
	
	public void setConfirmed(Integer confirmed) {
		this.confirmed+=confirmed;
	}
	
	
	public Integer getDeaths() {
		return this.deaths;
	}
	
	public void setDeaths(Integer deaths) {
		this.deaths+=deaths;
	}
	
	public Integer getRecovered() {
		return this.recovered;
	}
	
	public void setRecovered(Integer recovered) {
		this.recovered+=recovered;
	}
	
}
