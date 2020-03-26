package edu.eci.arsw.covid.model;

public class Location {
	
	private Double longitude;
	private Double latitude;
	
	public Location(Double longitude, Double latitude) {
		this.longitude=longitude;
		this.latitude=latitude;
	}
	
	public Location() {
		
	}
	
	public Double getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude=longitude;
	}
	
	public Double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude=latitude;
	}
}


