package edu.eci.arsw.covid.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.covid"})
public class CovidAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidAPIApplication.class, args);
	}

}
