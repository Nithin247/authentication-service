package com.prd.ws.config;

import javax.sql.DataSource;

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TomcatConfig {
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {

			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
					Tomcat tomcat) {
				tomcat.enableNaming();
	            TomcatEmbeddedServletContainer container = 
	                    super.getTomcatEmbeddedServletContainer(tomcat);
	            for (Container child: container.getTomcat().getHost().findChildren()) {
	                if (child instanceof Context) {
	                    ClassLoader contextClassLoader = 
	                            ((Context)child).getLoader().getClassLoader();
	                    Thread.currentThread().setContextClassLoader(contextClassLoader);
	                    break;
	                }
	            }
	            return container;
			}

			@Override
			protected void postProcessContext(Context context) {
				ContextResource common = new ContextResource();
				common.setName("db/common");
				common.setType(DataSource.class.getName());
				common.setProperty("driverClassName", "oracle.jdbc.OracleDriver");
				common.setProperty("url", "");
				common.setProperty("username", "USER");
				common.setProperty("password", "PASS");
				context.getNamingResources().addResource(common);
			}
		};
	}
}
