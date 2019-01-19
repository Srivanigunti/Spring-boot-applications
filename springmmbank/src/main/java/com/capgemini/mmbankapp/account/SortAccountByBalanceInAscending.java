package com.capgemini.mmbankapp.account;

import java.util.Comparator;

public class SortAccountByBalanceInAscending extends SavingsAccount implements Comparator<SavingsAccount> {

	@Override
	public int compare(SavingsAccount savingsAccount1, SavingsAccount savingsAccount2) {
		if(savingsAccount1.getBankAccount().getAccountBalance() <savingsAccount2.getBankAccount().getAccountBalance())
		 return 1;
		else if(savingsAccount1.getBankAccount().getAccountBalance() >savingsAccount2.getBankAccount().getAccountBalance())
		return -1;
	return 0;
	}

}