package edu.eci.arsw.covid.cache.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.eci.arsw.covid.cache.CovidCache;
import edu.eci.arsw.covid.model.Data;

public class CovidCacheImpl implements CovidCache{
	
	private ConcurrentHashMap<String,List<Data>> covidsMap;
	private ConcurrentHashMap<String, Long> covidsCacheTime;
	
	public CovidCacheImpl() {
		
	}

	@Override
	public List<Data> getAllCovids() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Data> getCovidsByCountry(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCovids(String name, List<Data> airports) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getCacheTime(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
