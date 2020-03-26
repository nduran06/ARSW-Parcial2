package edu.eci.arsw.covid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.eci.arsw.covid.services.CovidServices;


@Controller
@RequestMapping(value = "/rapidapi")
public class CovidApplicationController {
	
	@Autowired
    private CovidServices covidServices;


	@RequestMapping(path="/covids", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCovid(){
		try {
			//System.out.println(this.covidServices.getAllCovid());
			//return new ResponseEntity<>(this.covidServices.getAllCovid(),HttpStatus.ACCEPTED);  
			
			return new ResponseEntity<>(this.covidServices.getAllCovid(),HttpStatus.ACCEPTED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
              
    }

}
