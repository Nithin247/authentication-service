package com.prd.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prd.ws.out.Response;

@RestController
public class ProductController {

  /**
   * . log4j.Logger
   */
  private static final Logger LOGGER = LogManager.getLogger(ProductController.class);


  /**
   * Health check.
   *
  * @return the string
   */
  @RequestMapping(value = "/api/getProductDetails", method = RequestMethod.GET)
  public Response getProductDetails() {
    LOGGER.info("Product Deatils end point called");
    Response response = new Response();
    response.setStatus("SUCCESS");
    response.setStatusCode(200);
    return response;
  }

}