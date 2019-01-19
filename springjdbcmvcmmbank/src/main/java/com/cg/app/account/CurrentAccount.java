package com.cg.app.account;

import org.springframework.stereotype.Component;

@Component
public class CurrentAccount {
	private double odlimit;
	private BankAccount bankAccount;
	public CurrentAccount() {
	}
	public CurrentAccount(int accountNumber, String accountHolderName, double accountBalance, double odlimit) {
		bankAccount = new BankAccount(accountNumber, accountHolderName, accountBalance);
		this.odlimit = odlimit;
	}
	public CurrentAccount(String accountHolderName, double accountBalance, double odlimit) {
		bankAccount = new BankAccount(accountHolderName, accountBalance);
		this.odlimit = odlimit;
	}
	public double getOdlimit() {
		return odlimit;
	}
	public void setOdlimit(double odlimit) {
		this.odlimit = odlimit;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	@Override
	public String toString() {
		return "CurrentAccount [odlimit=" + odlimit + ", bankAccount=" + bankAccount + "]";
	}
}