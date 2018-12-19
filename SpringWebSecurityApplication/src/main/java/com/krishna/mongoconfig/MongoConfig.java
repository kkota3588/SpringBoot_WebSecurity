package com.krishna.mongoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
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
