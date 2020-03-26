package edu.eci.arsw.covid.connection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.util.StringUtils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class HTTPConnection {
	
	private final static String REQUEST_URL="https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats";  
	private final static String REQUEST_URL_LOCATION="https://restcountries-v1.p.rapidapi.com/name/";  

	private final static String HEADER_HOST="x-rapidapi-host";
	private final static String HEADER_KEY="x-rapidapi-key";
	
	private final static String RAPIAPI_HOST="covid-19-coronavirus-statistics.p.rapidapi.com";
	private final static String RAPIAPI_HOST_LOCATION="restcountries-v1.p.rapidapi.com";

	
	private final static String RAPIAPI_KEY="bcbfd49ec2msha2aa01a580781b8p110bcfjsnb3cc9ca0099f";
	
	private final static String charset = "UTF-8";


	/**
	 * 
	 * @param name
	 * @return
	 */
	public static HttpResponse<String> getResponseByCountry(String name){
		if(name.length()>2) {
			String[] names=name.split(" ");
			String ans=" ";
			for(String n:names) {
				n=n.toLowerCase();
				n= StringUtils.capitalize(n); 
				ans+=n+" ";
			}
			name=ans.trim();
		}
		String query=null;
		try {
			query = URLEncoder.encode(name, charset);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		HttpResponse<String> response=null;
		
	    try {
			response = Unirest.get(REQUEST_URL+ "?country=" + query)
			        .header(HEADER_HOST, RAPIAPI_HOST)
			        .header(HEADER_KEY,RAPIAPI_KEY).asString();
		} 
	    catch (UnirestException e) {
			e.printStackTrace();
		}
	    
		return response;
	}
	
	/**
	 * 
	 * @return
	 */
	public static HttpResponse<String> getResponseAll(){
		HttpResponse<String> response=null;
		
	    try {
			response = Unirest.get(REQUEST_URL)
			        .header(HEADER_HOST, RAPIAPI_HOST)
			        .header(HEADER_KEY,RAPIAPI_KEY).asString();
		} 
	    catch (UnirestException e) {
			e.printStackTrace();
		}
	    
		return response;
	}
	
	public static HttpResponse<JsonNode> getLocationResponseByCountry(String name){
		
		
		HttpResponse<JsonNode> response=null;
		
	    try {
			response = Unirest.get(REQUEST_URL_LOCATION + name)
			        .header(HEADER_HOST, RAPIAPI_HOST_LOCATION)
			        .header(HEADER_KEY,RAPIAPI_KEY).asJson();
		} 
	    catch (UnirestException e) {
			e.printStackTrace();
		}
	    
		return response;
	}

}
