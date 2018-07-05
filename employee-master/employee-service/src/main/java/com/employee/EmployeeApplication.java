package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * This class is used to start the Employee application.
 *
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class EmployeeApplication extends SpringBootServletInitializer {

	/**
	 * @param args : Java Arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeApplication.class);
	}
}