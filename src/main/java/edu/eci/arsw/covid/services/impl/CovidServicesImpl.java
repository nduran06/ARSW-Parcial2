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
import edu.eci.arsw.covid.model.Data;
import edu.eci.arsw.covid.services.CovidServices;

@Service
public class CovidServicesImpl implements CovidServices{
	
	@Autowired
	private CovidCache covidCache;
	
	@Override
	public Data getAllCovid() {
		Gson gson=new Gson();
		HttpResponse<JsonNode> response=HTTPConnection.getResponseAll();
		//System.out.println(response.getBody().toString());
		Data data = gson.fromJson(response.getBody().toString(), Data.class);
		
		
		return data;
	}
	
	
	@Override
	public List<Data> getCovidByCountry(String name) {
		
		HttpResponse<String> response=HTTPConnection.getResponseByCountry(name);
		
		
		Gson gson=new GsonBuilder().create();
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
	    }
		
	    
		return data;
	}

	

}
