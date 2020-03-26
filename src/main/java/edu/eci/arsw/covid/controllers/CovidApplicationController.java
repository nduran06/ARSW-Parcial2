package edu.eci.arsw.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.eci.arsw.covid.services.CovidServices;


@Controller
@RequestMapping(value = "/rapidapi")
public class CovidApplicationController {
	
	@Autowired
    private CovidServices covidServices;

	/**
	 * Retorna toda la información almacenada
	 * @return
	 */
	@RequestMapping(path="/covids", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCovid(){
		try {
			return new ResponseEntity<>(this.covidServices.getAllCovid(),HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
              
    }
	
	/**
	 * Retorna toda la información por países
	 * @return
	 */
	@RequestMapping(path="/covids/countries", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCovidForCountry(){
		try {
			return new ResponseEntity<>(this.covidServices.getAllCovidForCountry(),HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
              
    }
	
	/**
	 * Retorna la información por regiones del país dado
	 * @param country
	 * @return
	 */
	@GetMapping("/covids/country")
	public ResponseEntity<?> getCovidsByCountryName(@RequestParam String country){
		try {
			return new ResponseEntity<>(this.covidServices.getCovidByCountry(country),HttpStatus.ACCEPTED);  
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
              
    }
	
	/**
	 * Retorna la locación del parámetro dado
	 * @param name
	 * @return
	 */
	@GetMapping("/covids/location")
	public ResponseEntity<?> getAirportsByName(@RequestParam String name){
		try {
			return new ResponseEntity<>(this.covidServices.getLocation(name).toString(),HttpStatus.ACCEPTED);  
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
              
    }


}
