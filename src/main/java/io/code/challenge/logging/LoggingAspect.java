package io.code.challenge.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Aspect
@Configuration
public class LoggingAspect {
	
	Logger logger = LogManager.getLogger(this.getClass());

	@Before("execution(* io.code.challenge.service.*.*(..))")
	public void before(JoinPoint joinPoint){

		logger.info(" Executing: {}", joinPoint);
	}
	
	
	@AfterReturning(value = "execution(* io.code.challenge.service.*.*(..))", 
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("{} returned with value {}", joinPoint, result);
	}
}