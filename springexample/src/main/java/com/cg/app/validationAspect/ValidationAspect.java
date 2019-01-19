package com.cg.app.validationAspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
	Logger logger = Logger.getLogger(ValidationAspect.class.getName());

	@Before("execution(* com.cg.app.calculator.Calculator.*(..))")
	public void log1() {
		logger.info("After - Logging the method");
	}

	@After("execution(* com.cg.app.calculator.Calculator.*(..))")
	public void log2() {
		logger.info("After - Logging the method");
	}

	@Around("execution(* com.cg.app.calculator.Calculator.*(..))")
	public Integer log3(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before - Logging the method");
		logger.info("Function Name is: " + pjp.getSignature());
		logger.info("Parameters are: ");
		Object[] params = pjp.getArgs();
		for (int i = 0; i < params.length; i++) {
			logger.info("Parameter value at index" + i + " is " + params[i]);
		}
		Object retVal = pjp.proceed();
		logger.info("After - Logging the method");
		return (Integer) retVal;
	}

	@AfterReturning(pointcut = "execution(* com.cg.app.calculator.Calculator.*(..))", returning = "retVal")
	public void log4(Integer retVal) {
		logger.info("Result = " + retVal);
	}

}
