package com.prd.ws.out;

public class Response {

	/** The status. */
	private String status;

	/** The status code. */
	private int statusCode;

	/** The response body. */
	private Object responseBody;

	/** The error message. */
	private String errorMessage;

	/**
	 * . default constructer
	 */
	public Response() {
		// default constructer
	}

	/**
	 * Instantiates a new response.
	 *
	 * @param statuss       the statuss
	 * @param responseBodys the response bodys
	 */
	public Response(final String statuss, final int statusCod, final Object responseBodys) {
		super();
		this.status = statuss;
		this.statusCode = statusCod;
		this.responseBody = responseBodys;
	}

	/**
	 * Instantiates a new response.
	 *
	 * @param statuss       the statuss
	 * @param errorMessages the error message
	 */
	public Response(final String statuss, final int statusCod, final String errorMessages) {
		super();
		this.status = statuss;
		this.statusCode = statusCod;
		this.errorMessage = errorMessages;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param statusFlag the new status
	 */
	public void setStatus(final String statusFlag) {
		this.status = statusFlag;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessageValue the new error message
	 */
	public void setErrorMessage(final String errorMessageValue) {
		this.errorMessage = errorMessageValue;
	}

	/**
	 * Gets the response body.
	 *
	 * @return the response body
	 */
	public Object getResponseBody() {
		return responseBody;
	}

	/**
	 * Sets the response body.
	 *
	 * @param responseBodyValue the new response body
	 */
	public void setResponseBody(final Object responseBodyValue) {
		this.responseBody = responseBodyValue;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
