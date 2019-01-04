package com.krishna.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>LoggerUtility</h1>
 * <p>
 * This class has the methods to obtain logger
 * </p>
 * 
 * 
 * @version 0.1
 * @since 30 August,2017
 */
@Configuration
public class LoggerUtility {

	/**
	 * Method to write the logs
	 * 
	 * @return singleton instance of logger
	 */
	@Bean
	public Logger getLogger() {

		// Return file logger
		return LogManager.getLogger();
	}

}
