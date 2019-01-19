package com.cg.app.account.service.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import com.cg.app.account.CurrentAccount;


public class CurrentAccountAspect {
	Logger logger = Logger.getLogger(SavingsAccountAspect.class.getName());

	@Around("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		CurrentAccount currentAccount = (CurrentAccount) param[0];
		double amount = (Double) param[1];
		 if (amount > 0 && currentAccount!=null) {
			pjp.proceed();
		}else if (currentAccount == null) {
			logger.warning("Account number doesnot exists!!");
		}  else {
			logger.warning("Deposit amount should be greater than 0");
		}
	}

	@Around("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		 
		Object[] param = pjp.getArgs();
		CurrentAccount currentAccount = (CurrentAccount) param[0];
		if (currentAccount != null) {
			
			double currentBalance = currentAccount.getBankAccount().getAccountBalance();
			double amount = (Double) param[1];

			if (amount > 0 && currentBalance + currentAccount.getOdlimit() >= amount) {
				pjp.proceed();
			}
			else {
				logger.warning("Withdraw amount should be less than availableBalance + odLimit and greater than 0");
			}
		}
		else {
			 logger.warning("Account number doesnot exists!!");
		}
		
	}

	@Around("execution(* com.cg.app.account.service.CurrentAccountServiceImpl.fundTransfer(..))")
	public void fundTransferValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		CurrentAccount sender = (CurrentAccount) param[0];
		double senderBalance = sender.getBankAccount().getAccountBalance();
		CurrentAccount receiver = (CurrentAccount) param[1];
		// double recieverBalance=receiver.getBankAccount().getAccountBalance();
		double amount = (Double) param[2];
		if (sender == null || receiver == null) {
			logger.warning("Check the account numbers you have entered!!");
		} else if (amount <= senderBalance) {
			pjp.proceed();
		} else {
			logger.warning("Withdraw amount should begreater than 0 and ");
		}
	}
}