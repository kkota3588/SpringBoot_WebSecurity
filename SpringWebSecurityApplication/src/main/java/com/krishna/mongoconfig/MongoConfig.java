package com.krishna.mongoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@EnableAutoConfiguration
@Configuration
@Component
public class MongoConfig {
	@Value("${mongodb.url:#{null}}")
	private String MONGO_URL;

	@Bean
	public MongoClient mongo() throws Exception {
		MongoClientURI connectionString = new MongoClientURI(MONGO_URL);
		MongoClient mongoClient = new MongoClient(connectionString);
		return mongoClient;

	}
}
