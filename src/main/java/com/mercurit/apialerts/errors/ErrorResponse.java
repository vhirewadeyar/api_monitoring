package com.mercurit.apialerts.errors;

public class ErrorResponse {

	private int status;
	private String error;
	private String api;
	
	public ErrorResponse(int status,String error,String api) {
		
	}
	public ErrorResponse() {
		
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	
	
	
}
