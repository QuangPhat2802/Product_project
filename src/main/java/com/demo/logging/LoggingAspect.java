package com.demo.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints
	 */

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)"
			+ " || within(@org.springframework.stereotype.Service *)"
			+ " || within(@org.springframework.stereotype.Repository *)")
	public void springBeanPointCut() {

	}

	@Pointcut("within(com.demo..*)"
			+ " || within(com.demo.serviceimpl..*)"
			+ " || within(com.demo.controller..*)")
	public void applicationPackagePointCut() {

	}

	@Around("applicationPackagePointCut() && springBeanPointCut()")
	public Object loggerBeforeAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
//		LOGGER.debug("+++++++++++++++ LoggingAspect.logBeforeAllMethods(): " + joinPoint.getSignature().getName());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter: {}.{}() with argument[s] = {}" + joinPoint.getSignature().getDeclaringTypeName()
					+ joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		}
		try {
			Object result = joinPoint.proceed();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Enter: {}.{}() with argument[s] = {}" + joinPoint.getSignature().getDeclaringTypeName()
						+ joinPoint.getSignature().getName(), result);
			}
			return result;
		} catch (IllegalAccessException e) {
			LOGGER.error("Illegal argument: {} in {}.{}()" + Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
		
	}

	@After("execution(* com.demo.serviceimpl.ProductServiceImpl.getAllProduct(..))")
	public void loggerAfterGetProduct(JoinPoint joinPoint) {
		LOGGER.debug("+++++++++++++++ LoggingAspect.logBeforeGetProduct(): " + joinPoint.getSignature().getName());
	}

	@Before("execution(* com.demo.serviceimpl.ProductServiceImpl.findByProductId(..))")
	public void loggerBeforeGetProductById(JoinPoint joinPoint) {
		System.out.println(
				"####################### LoggingAspect.logBeforefindByProductId" + joinPoint.getSignature().getName());
	}

}
