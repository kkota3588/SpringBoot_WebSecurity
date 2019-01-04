package com.krishna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.krishna.logger.LoggerUtility;

//@EnableAutoConfiguration
@SpringBootApplication
//public class SpringWebSecurityApplicationStarter implements ApplicationRunner {
public class SpringWebSecurityApplicationStarter {
//	static {
//		System.setProperty("spring.config.location",
//				"file:C://Users//kkota//git//Springboot_WebSecurity//SpringWebSecurityApplication//src//main//resources//properties.yml");
//	}
	@Autowired
	LoggerUtility loggerUtility;

	public static void main(String args[]) {
//		SpringApplication.run(SpringWebSecurityApplicationStarter.class, args);
		new SpringApplicationBuilder(SpringWebSecurityApplicationStarter.class)
				.properties("spring.config.name=application,properties").run(args);

	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		loggerUtility.getLogger().info(".........Spring Application running.............");
//
//	}
}
