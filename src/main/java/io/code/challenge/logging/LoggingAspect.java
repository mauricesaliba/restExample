package io.code.challenge.logging;

import org.aspectj.lang.JoinPoint;
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
		//Advice
		logger.info(" Check for user access ");
		logger.info(" Allowed execution for {}", joinPoint);
	}
}