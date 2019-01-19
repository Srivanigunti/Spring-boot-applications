package com.cg.app.account.service.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrentAccountLogging {
	Logger logger = Logger.getLogger(CurrentAccountLogging.class.getName());

	@After("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.createNewAccount(..))")
	public void creatAccountLog(JoinPoint joinPoint) throws Exception {
		logger.info("Account created successfully!!");
	}

	@After("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.deleteAccount(..))")
	public void deleteAccountLog(JoinPoint joinPoint) throws Exception {
		logger.info("Account deleted successfully!!");
	}

	@After("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.updateAccount(..))")
	public void updateAccountLog(JoinPoint joinPoint) throws Exception {
		logger.info("Account updated successfully!!");
	}

	@After("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.withdraw(..))")
	public void withdrawLog(JoinPoint joinPoint) throws Exception {
		logger.info("Withdrawl successfull!!");
	}

	@After("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.deposit(..))")
	public void depositLog(JoinPoint joinPoint) throws Exception {
		logger.info("Deposit successfull!!");
	}

	@After("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.fundTransfer(..))")
	public void fundTransferLog(JoinPoint joinPoint) throws Exception {
		logger.info("Fund Transfer successfull!!");
	}
}
