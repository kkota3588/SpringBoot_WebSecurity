package com.krishna.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>LoggerAspect</h1>
 * <p>
 * This class is used to logging the all in the application
 * </p>
 * 
 * @version 0.1
 * @since 30 August,2017
 * */
@Aspect
@Component
public class LoggerAspect {

	@Autowired
	LoggerUtility loggerUtility;

	/**
	 * This method having the annotation called point cut with is configure
	 * Logger class and it should applied for all methods in the application
	 * 
	 * @param point
	 *            pointcut in the annotation
	 * @return need return statement to preceeding the around
	 * @throws Throwable
	 *             exception throwable
	 */
	@Around(" @annotation(Logger) && execution(* *(..)) ")
	public Object log(ProceedingJoinPoint point) throws Throwable {
		loggerUtility
				.getLogger()
				.info(point.getSignature().getDeclaringType() + " "
						+ point.getSignature().getName() + " method started...");
		Object test = point.proceed();
		loggerUtility.getLogger().info(
				point.getSignature().getDeclaringType() + " "
						+ point.getSignature().getName() + " method ended...");
		return test;
	}
}