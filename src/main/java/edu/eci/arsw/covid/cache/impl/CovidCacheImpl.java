package edu.eci.arsw.covid.cache.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.eci.arsw.covid.cache.CovidCache;
import edu.eci.arsw.covid.model.Data;

@Service
public class CovidCacheImpl implements CovidCache{
	
	private ConcurrentHashMap<String,List<Data>> covidsMap;
	private ConcurrentHashMap<String, Long> covidsCacheTime;
	
	public CovidCacheImpl() {
		this.covidsMap=new ConcurrentHashMap<String,List<Data>>();
		this.covidsCacheTime=new ConcurrentHashMap<String, Long>();
	}

	@Override
	public List<Data> getAllCovids() {
		List<Data> covids=new ArrayList<Data>();
		this.covidsMap.forEach((k,v)->covids.addAll(v));
		
		return covids;
	}

	@Override
	public List<Data> getCovidsByCountry(String name) {
		return this.covidsMap.get(name);
	}

	@Override
	public void saveCovids(String name, List<Data>  data) {
		this.covidsMap.put(name, data);
		this.covidsCacheTime.put(name, System.currentTimeMillis());
		
	}

	@Override
	public Long getCacheTime(String name) {
		return this.covidsCacheTime.get(name);
	}

}
