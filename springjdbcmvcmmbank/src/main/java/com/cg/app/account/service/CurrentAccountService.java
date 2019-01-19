package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface CurrentAccountService {
	CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odlimit)
			throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException;
	void deposit(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException;
	void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException;
	void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount)
			throws ClassNotFoundException, SQLException;
	boolean updateAccount(CurrentAccount account) throws ClassNotFoundException, SQLException;
	CurrentAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;
	CurrentAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;
	double checkBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException;
	List<CurrentAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException;
	List<CurrentAccount> sortByAccountHolderNameDescending() throws SQLException, ClassNotFoundException;
	List<CurrentAccount> sortByBalanceRangeDescending(int minimumBalanceDescending, int maximumBalanceDescending)
			throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAccountByName(String accountHolderName)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;
	List<CurrentAccount> getAllBelowBalance(int balanceNumber) throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAllAboveBalance(int balanceNumber) throws ClassNotFoundException, SQLException;
	List<CurrentAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException;
	List<CurrentAccount> sortByAccountBalanceDescending() throws ClassNotFoundException, SQLException;
}