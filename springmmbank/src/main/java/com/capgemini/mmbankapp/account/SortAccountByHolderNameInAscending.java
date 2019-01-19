package com.capgemini.mmbankapp.account;

import java.util.Comparator;

public class SortAccountByHolderNameInAscending extends SavingsAccount implements Comparator<SavingsAccount> {

	@Override
	public int compare(SavingsAccount savingsAccount1,SavingsAccount savingsAccount2) {
		
		return (savingsAccount1.getBankAccount().getAccountHolderName().compareToIgnoreCase(savingsAccount2.getBankAccount().getAccountHolderName()));
	}

}