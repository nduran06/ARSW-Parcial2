package edu.eci.arsw.covid.model;

public class ApiResponse {
	
	private boolean error;
	private int statusCode;
	private String message;
	private Data data;
	
	public ApiResponse() {
		
	}
	
	public boolean getError() {
		return this.error;
	}
	
	public void setError(boolean error) {
		this.error=error;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode=statusCode;
	}

	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
	
	public Data getData() {
		return this.data;
	}
	
	public void setData(Data data) {
		this.data=data;
	}
	
}
