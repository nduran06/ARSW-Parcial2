package edu.eci.arsw.covid.model;

import java.util.Date;

public class Covid19Stats {
	private String city;
	private String province;
	private String country;
	private Date lastUpdate;
	private String keyId;
	private Integer confirmed;
	private Integer deaths;
	private Integer recovered;
	
	public Covid19Stats() {
		
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	public String getProvince() {
		return this.province;
	}
	
	public void setProvince(String province) {
		this.province=province;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country=country;
	}
	
	public Date getLastUpdate() {
		return this.lastUpdate;
	}
	
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate=lastUpdate;
	}
	
	public String getKeyId() {
		return this.keyId;
	}
	
	public void setKeyId(String keyId) {
		this.keyId=keyId;
	}
	
	public Integer getConfirmed() {
		return this.confirmed;
	}
	
	public void setConfirmed(Integer confirmed) {
		this.confirmed=confirmed;
	}
	
	public Integer getDeaths() {
		return this.deaths;
	}
	
	public void setDeaths(Integer deaths) {
		this.deaths=deaths;
	}
	
	public Integer getRecovered() {
		return this.recovered;
	}
	
	public void setRecovered(Integer recovered) {
		this.recovered=recovered;
	}

}


