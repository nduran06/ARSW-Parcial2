package edu.eci.arsw.covid.cache;

import java.util.List;

import edu.eci.arsw.covid.model.Data;

public interface CovidCache {
	
	/**
	 * 
	 * @return
	 */
	public List<Data> getAllCovids();
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Data> getCovidsByCountry(String name);
	
	
	/**
	 * 
	 * @param name
	 * @param airports
	 */
	public void saveCovids(String name, List<Data> data);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Long getCacheTime(String name);

}
