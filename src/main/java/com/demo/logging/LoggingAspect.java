package com.demo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.demo.serviceimpl.ProductServiceImpl.*(..))")
	public void loggerBeforeAllMethods(JoinPoint joinPoint) {
		LOGGER.debug("+++++++++++++++ LoggingAspect.logBeforeAllMethods(): " + joinPoint.getSignature().getName());
	}
	
	@After("execution(* com.demo.serviceimpl.ProductServiceImpl.getAllProduct(..))")
	public void loggerAfterGetProduct(JoinPoint joinPoint) {
		System.out.println("+++++++++++++++ LoggingAspect.logBeforeGetProduct(): " + joinPoint.getSignature().getName());
	}
	
	@Before("execution(* com.demo.serviceimpl.ProductServiceImpl.findByProductId(..))")
	public void loggerBeforeGetProductById(JoinPoint joinPoint) {
		System.out.println("####################### LoggingAspect.logBeforefindByProductId" + joinPoint.getSignature().getName());
	}
	
}
