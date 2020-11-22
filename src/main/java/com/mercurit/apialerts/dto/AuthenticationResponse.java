package com.mercurit.apialerts.dto;

public class AuthenticationResponse {
	
	private final String JWTToken;
	
	public AuthenticationResponse(String JWTToken) {
		this.JWTToken=JWTToken;
	}
	
	public String JWTToken() {
		return JWTToken;
	}

}
