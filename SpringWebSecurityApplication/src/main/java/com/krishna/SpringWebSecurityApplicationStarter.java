package com.krishna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

//@EnableAutoConfiguration
@SpringBootApplication
public class SpringWebSecurityApplicationStarter {
//	static {
//		System.setProperty("spring.config.location",
//				"file:C://Users//kkota//git//Springboot_WebSecurity//SpringWebSecurityApplication//src//main//resources//properties.yml");
//	}

	public static void main(String args[]) {
		new SpringApplicationBuilder(SpringWebSecurityApplicationStarter.class)
	    .properties("spring.config.name=application,properties")
	    .run(args);
//		SpringApplication.run(SpringWebSecurityApplicationStarter.class, args);
	}
}
