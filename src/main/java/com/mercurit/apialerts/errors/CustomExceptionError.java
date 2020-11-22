package com.mercurit.apialerts.errors;

public class CustomExceptionError extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomExceptionError(final String message) {
		super(message);
	}

}
