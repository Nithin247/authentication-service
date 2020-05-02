package com.prd.ws.start;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.prd")
@EnableScheduling
public class STARTER extends org.springframework.boot.web.support.SpringBootServletInitializer {
	
	static final Logger LOG = LogManager.getLogger(STARTER.class);
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class<?>[] {STARTER.class});
    }
	
	public static void main(String[] args) {
		SpringApplication.run(STARTER.class, args);
		System.out.println(System.getProperty("catalina.base"));
	}
}
