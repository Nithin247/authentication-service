package com.prd.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfig Class.
 */
@Configuration
@ComponentScan({ "com.thg.gdews" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.config.annotation.web.configuration
	 * .WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/getProductDetails")
				.hasRole("USER1").antMatchers(HttpMethod.GET, "/api/getCOnfigDetails").hasRole("USER2").and().csrf()
				.disable().formLogin().disable();

	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("test1").password("pass1").roles("USER1");
		auth.inMemoryAuthentication().withUser("test1").password("pass2").roles("USER2");

	}

}
