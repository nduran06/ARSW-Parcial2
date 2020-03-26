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
import edu.eci.arsw.covid.services.CovidServices;

@Service
public class CovidServicesImpl implements CovidServices{
	
	@Autowired
	private CovidCache covidCache;
	
	/**
	 * Verifica si el país dado según su nombre existe en la lista dada
	 * @param countries
	 * @param name
	 * @return
	 */
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
	
	/**
	 * Crea los países correspondientes al response
	 * @param covidList
	 * @return
	 */
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
	
	/**
	 * Ordena la información
	 * @param response
	 * @return
	 */
	private List<Covid19Stats> getCovidData(HttpResponse<String> response) {
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		
		ApiResponse apiResponse=null;
	    apiResponse=gson.fromJson(response.getBody(), new TypeToken<ApiResponse>(){}.getType());
	    
		return apiResponse.getData().getCovid19Stats();
		
	}
	
	/**
	 * Retorna toda la infomación por países
	 */
	@Override
	public List<Country> getAllCovidForCountry() {
		
		HttpResponse<String> response=HTTPConnection.getResponseAll();
		List<Covid19Stats> covids= getCovidData(response);
		
		return createCountries(covids);
	}
	
	/**
	 * Retorna toda la información almacenada
	 */
	@Override
	public List<Covid19Stats> getAllCovid() {
		HttpResponse<String> response=HTTPConnection.getResponseAll();
		return getCovidData(response);
	}
	
	
	/**
	 * Retorna la información por regiones del país dado
	 */
	@Override
	public List<Covid19Stats> getCovidByCountry(String name) {
		
		HttpResponse<String> response=HTTPConnection.getResponseByCountry(name);
		
		List<Covid19Stats> covids=null;
		if(this.covidCache.getCovidsByCountry(name)==null) {
			covids=getCovidData(response);
			this.covidCache.saveCovids(name, covids);
		}
		
		else {
			Long cacheTime=this.covidCache.getCacheTime(name);
	    	Long dif=System.currentTimeMillis()-cacheTime;
	    	Long realTime=TimeUnit.SECONDS.convert(dif, TimeUnit.MILLISECONDS);
	    	
	    	if(realTime>300) {
	    		covids=getCovidData(response);
	    		this.covidCache.saveCovids(name, covids);
	    	}
	    	else {
	    		covids=this.covidCache.getCovidsByCountry(name);
	    	}
		}
		
		return covids;
		
	}
	
	/**
	 * Retorna la locación del parámetro dado
	 */
	@Override
	public JsonNode getLocation(String name) {
		HttpResponse<JsonNode> response=HTTPConnection.getLocationResponseByCountry(name);
		System.out.println(response.getBody());
		return response.getBody();
	}

	

}
