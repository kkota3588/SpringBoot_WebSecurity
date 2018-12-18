package com.krishna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringWebSecurityApplicationStarter {
	static {
		System.setProperty("spring.config.location",
				"file:D://MYAPPLICATIONS//GIT1//SpringWebSecurityApplication//src//main//resources//properties.yml");
	}
	public static void main(String args[]) {
		SpringApplication.run(SpringWebSecurityApplicationStarter.class, args);
	}
}
