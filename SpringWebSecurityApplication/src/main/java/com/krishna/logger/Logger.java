package com.krishna.logger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>Logger</h1>
 * <p>
 * This interface is used to create custom logger annotation pattern
 * </p>
 * 
 * @version 0.1
 * @since 30 August,2017
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logger {

}