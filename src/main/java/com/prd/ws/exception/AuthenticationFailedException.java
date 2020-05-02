package com.prd.ws.exception;

import com.prd.ws.out.Response;

@SuppressWarnings("serial")
public class AuthenticationFailedException extends Exception {
	
	private Response response;

	public AuthenticationFailedException() {
		super();
	}
	
	public AuthenticationFailedException(Response response) {
		super();
		this.response = response;
		
	}
	
	public AuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationFailedException(String message) {
		super(message);
	}

	public AuthenticationFailedException(Throwable cause) {
		super(cause);
	}

	public Response getResponse() {
		return this.response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}

