package edu.eci.arsw.covid.cache;

import java.util.List;

import edu.eci.arsw.covid.model.Covid19Stats;
import edu.eci.arsw.covid.model.Data;

public interface CovidCache {
	
	/**
	 * 
	 * @return
	 */
	public List<Covid19Stats> getAllCovids();
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Covid19Stats> getCovidsByCountry(String name);
	
	
	/**
	 * 
	 * @param name
	 * @param airports
	 */
	public void saveCovids(String name, List<Covid19Stats> data);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Long getCacheTime(String name);

}
