package com.capgemini.mmbankapp.account.validation;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.capgemini.mmbankapp.account.SavingsAccount;

@Aspect
@Component
public class Validation {
	Logger logger = Logger.getLogger(Validation.class.getName());
	
	@Around("execution(* com.capgemini.mmbankapp.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void log2(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before logging the method in around");
		Object[] params = pjp.getArgs();
		SavingsAccount account = (SavingsAccount)params[0];
		double currentBalance = account.getBankAccount().getAccountBalance();
		
		if((Double)params[1]>0 && currentBalance>(Double)params[1]) {
			pjp.proceed();
			logger.info("Withdraw successfull");
		} else {
			logger.info("Invalid amount or Insufficient Funds...!!!!");

		}
	}
	
	@Around("execution(* com.capgemini.mmbankapp.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void log1(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before logging the method in around");
		Object[] params = pjp.getArgs();
		
		
		if((Double)params[1]>0 ) {
			pjp.proceed();
			logger.info("Deposit successfull");
		} else {
			logger.info("Invalid amount...!!!!");

		}
	}
}
