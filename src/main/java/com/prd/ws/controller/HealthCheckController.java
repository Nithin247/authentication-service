package com.prd.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  /**
   * . log4j.Logger
   */
  private static final Logger LOGGER = LogManager.getLogger(HealthCheckController.class);


  /**
   * Health check.
   *
  * @return the string
   */
  @RequestMapping(value = "/health", method = RequestMethod.GET)
  public String health() {
    LOGGER.info("Health end point called");
    return "Alive";
  }

}