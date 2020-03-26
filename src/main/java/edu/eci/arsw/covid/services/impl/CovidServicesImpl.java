package edu.eci.arsw.covid.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import edu.eci.arsw.covid.cache.CovidCache;
import edu.eci.arsw.covid.connection.HTTPConnection;
import edu.eci.arsw.covid.model.ApiResponse;
import edu.eci.arsw.covid.model.Country;
import edu.eci.arsw.covid.model.Covid19Stats;
import edu.eci.arsw.covid.model.Data;
import edu.eci.arsw.covid.model.Location;
import edu.eci.arsw.covid.services.CovidServices;

@Service
public class CovidServicesImpl implements CovidServices{
	
	@Autowired
	private CovidCache covidCache;
	
	private Country verifyExistingCountry(List<Country>countries, String name) {
		Country oldCountry=null;
		
		int length=countries.size();
		int cont=0;
		
		while(oldCountry==null && cont<length) {
			Country country=countries.get(cont);
			if(country.getName().equals(name)) {
				oldCountry=country;
			}
			cont++;
		}
		
		
		return oldCountry;
	}
	
	private ArrayList<Country> createCountries(List<Covid19Stats> covidList) {
		
		ArrayList<Country> countries=new ArrayList<Country>();
		
		for(Covid19Stats covids:covidList) {
			String countryName=covids.getCountry();
			
			Country oldCountry = verifyExistingCountry(countries,countryName);
			
			if(oldCountry==null) {
				Country newCountry=new Country(countryName, covids.getConfirmed(),
						covids.getDeaths(), covids.getRecovered());
				
				countries.add(newCountry);
			}
			else {
				oldCountry.setConfirmed(covids.getConfirmed());
				oldCountry.setDeaths(covids.getDeaths());
				oldCountry.setRecovered(covids.getRecovered());
			}
		}
		
		return countries;
		
	}
	
	private List<Covid19Stats> saveInCache(HttpResponse<String> response) {
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		ApiResponse apiResponse=null;
	    apiResponse=gson.fromJson(response.getBody(), new TypeToken<ApiResponse>(){}.getType());
	    
		return apiResponse.getData().getCovid19Stats();
		
	}
	
	
	@Override
	public List<Country> getAllCovidForCountry() {
		HttpResponse<String> response=HTTPConnection.getResponseAll();
		List<Covid19Stats> covids= saveInCache(response);
		
		return createCountries(covids);
	}
	
	
	@Override
	public List<Covid19Stats> getAllCovid() {
		HttpResponse<String> response=HTTPConnection.getResponseAll();
		return saveInCache(response);
	}
	
	
	@Override
	public List<Covid19Stats> getCovidByCountry(String name) {
		
		HttpResponse<String> response=HTTPConnection.getResponseByCountry(name);
		return saveInCache(response);
		
		/*Gson gson=new GsonBuilder().create();
	    List<Data> data=null;
	    
	    
	    List<Data> dataCache=this.getCovidByCountry(name);
	    
	    if(dataCache==null) {
	    	data=gson.fromJson(response.getBody(), new TypeToken<List<Data>>(){}.getType());
			this.covidCache.saveCovids(name, data);
	    }
	    else {
	    	Long cacheTime=this.covidCache.getCacheTime(name);
	    	Long dif=System.currentTimeMillis()-cacheTime;
	    	Long realTime=TimeUnit.SECONDS.convert(dif, TimeUnit.MILLISECONDS);
	    	
	    	if(realTime>300) {
	    		data=gson.fromJson(response.getBody(), new TypeToken<List<Data>>(){}.getType());
	    		this.covidCache.saveCovids(name, data);
	    	}
	    	else {
	    		data=this.covidCache.getCovidsByCountry(name);
	    	}
	    }*/
		
		
	}
	
	@Override
	public String getLocation(String name) {
		HttpResponse<String> response=HTTPConnection.getLocationResponseByCountry(name);
		System.out.println(response.getBody());
		return response.getBody();
	}

	

}
