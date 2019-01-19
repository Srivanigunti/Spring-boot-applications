package com.capgemini.mmbankapp.account.factory;

import com.capgemini.mmbankapp.account.SavingsAccount;

public final class AccountFactory {
	
	private static AccountFactory factory = new AccountFactory();

	private AccountFactory() {
		
	}
	
	public static AccountFactory getInstance() {
		return factory;
	}

	public SavingsAccount createNewSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		return new SavingsAccount(accountHolderName, accountBalance, salary);
	}

	public SavingsAccount deleteAccount(int accountNumber) {
		
		return null;
	}
}
