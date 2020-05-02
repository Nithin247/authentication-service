package com.prd.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prd.ws.out.Response;

@RestController
public class ConfigController {

	/**
	 * . log4j.Logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(ConfigController.class);

	/**
	 * Health check.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/api/getConfigDetails", method = RequestMethod.GET)
	public Response getConfigDetails() {
		LOGGER.info("Config Deatils end point called");
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setStatusCode(200);
		return response;
	}

}