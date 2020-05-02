package com.prd.ws.setup.environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.prd.ws.db.HibernateUtil;

/**
 * 
 */

public class EnvironmentLoader {

	
	static final Logger LOG = LogManager.getLogger(EnvironmentLoader.class);
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@Autowired
	private Environment environment;
	
	public void load() {
		init4j();
		initEnvironment();
		listEndPoints();
	}
	
	public void unLoad() {
		
	}
	
	public void init4j() {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("log4j.properties");
			Properties copy = PropertiesLoaderUtils.loadAllProperties("log4j.properties");
			Enumeration<Object> elements = copy.keys();
			while (elements.hasMoreElements()) {
				String key = (String) elements.nextElement();
				String value = copy.getProperty(key);
				if(value != null && value.contains("${catalina.base}/logs")){
					value = value.replace("${catalina.base}/logs", "${catalina.base}/logs" + 
							environment.getApplicationContext().getApplicationName());
					properties.put(key, value);
				}
		    }
			LOG.info("Log4j is setup....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initEnvironment() {
		LOG.info("Environment: " + environment.property(Environment.ENVIRONMENT));
		LOG.info("Hostname: " + environment.property(Environment.HOSTNAME));
		LOG.info("Site Url: " + environment.property(Environment.SITE_URL));
	}

	private void listEndPoints() {
		LOG.info("List of available endpoints as follows:");
		for(String endPoint: listAllEndPoints(handlerMapping))
			LOG.info(endPoint);
	}
	
	private List<String> listAllEndPoints(RequestMappingHandlerMapping handlerMapping){
    	Set<String> set = new HashSet<String>();
    	Map<RequestMappingInfo, HandlerMethod> handletMethods = handlerMapping.getHandlerMethods();
    	for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handletMethods.entrySet()) {
    		RequestMappingInfo mappingInfo = entry.getKey();
    		Set<String> pattern =  mappingInfo.getPatternsCondition().getPatterns();
			for (String uri : pattern) {
				set.add(uri);		
			}
    	}
    	List<String> list = new ArrayList<String>(set);
    	Collections.sort(list);
    	return list;
    }
}
