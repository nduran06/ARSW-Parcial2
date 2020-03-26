package edu.eci.arsw.covid.services.impl;

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
import edu.eci.arsw.covid.model.Covid19Stats;
import edu.eci.arsw.covid.model.Data;
import edu.eci.arsw.covid.services.CovidServices;

@Service
public class CovidServicesImpl implements CovidServices{
	
	@Autowired
	private CovidCache covidCache;
	
	private List<Covid19Stats> saveInCache(HttpResponse<String> response) {
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		ApiResponse apiResponse=null;
	    apiResponse=gson.fromJson(response.getBody(), new TypeToken<ApiResponse>(){}.getType());
	    
		return apiResponse.getData().getCovid19Stats();
		
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

	

}
