package com.prd.ws.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prd.ws.setup.environment.Environment;
import com.prd.ws.setup.environment.EnvironmentLoader;

@Configuration
public class SetupConfig {
	
	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
		return new PropertyPlaceholderConfigurer();
	}
	
	@Bean
	public EnvironmentLoader environmentLoader(){
		return new EnvironmentLoader();
	}
	
	@Bean
	public Environment environment(){
		return Environment.INSTANCE;
	}
}
