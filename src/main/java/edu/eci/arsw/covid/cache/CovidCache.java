package edu.eci.arsw.covid.cache;

import java.util.List;

import edu.eci.arsw.covid.model.Data;

public interface CovidCache {
	
	public List<Data> getAllCovids();
	
	public List<Data> getCovidsByCountry(String name);
	
	public void saveCovids(String name, List<Data> airports);

	public Long getCacheTime(String name);

}
