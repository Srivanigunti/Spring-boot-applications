package com.cg.app.account.dao;

import java.sql.SQLException;
import java.util.List;
import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface CurrentAccountDAO {
	CurrentAccount createNewAccount(CurrentAccount account) throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException;
	void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	CurrentAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;
	CurrentAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException;
	double checkBalance(int accountNumber) throws AccountNotFoundException, ClassNotFoundException, SQLException;
	boolean updateAccountType(CurrentAccount account) throws SQLException, ClassNotFoundException;
	List<CurrentAccount> sortByAccountHolderName() throws SQLException, ClassNotFoundException;
	List<CurrentAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws SQLException, ClassNotFoundException;
	List<CurrentAccount> sortByAccountHolderNameDescending() throws SQLException, ClassNotFoundException;
	List<CurrentAccount> sortByBalanceRangeDescending(int minimumBalanceDescending, int maximumBalanceDescending)
			throws ClassNotFoundException, SQLException;
	List<CurrentAccount> getAccountByName(String accountHolderName)
			throws SQLException, AccountNotFoundException, ClassNotFoundException;
	List<CurrentAccount> getAllBelowBalance(int balanceNumber) throws SQLException, ClassNotFoundException;
	List<CurrentAccount> getAllAboveBalance(int balanceNumber) throws SQLException, ClassNotFoundException;
	List<CurrentAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException;
	List<CurrentAccount> sortByAccountBalanceDescending() throws ClassNotFoundException, SQLException;
}