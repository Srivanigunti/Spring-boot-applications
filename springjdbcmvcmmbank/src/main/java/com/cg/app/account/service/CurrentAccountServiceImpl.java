package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.account.CurrentAccount;
import com.cg.app.account.dao.CurrentAccountDAO;
import com.cg.app.account.dao.CurrentAccountDAOImpl;
import com.cg.app.account.factory.AccountFactory;
import com.cg.app.account.util.DBUtil;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService{
	
	private AccountFactory factory;
	
	@Autowired
	private CurrentAccountDAO currentAccountDAO;
	
	public CurrentAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		currentAccountDAO = new CurrentAccountDAOImpl();
	}
	@Override
	public CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odlimit)
			throws ClassNotFoundException, SQLException {
		CurrentAccount account = factory.createNewCurrentAccount(accountHolderName, accountBalance, odlimit);
		currentAccountDAO.createNewAccount(account);
		return null;
	}
	@Override
	public List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException {
		return currentAccountDAO.getAllCurrentAccount();
	}
	@Override
	public void deposit(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException {
		/*if (amount > 0) {*/
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			currentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		/*
		 * }else { throw new InvalidInputException("Invalid Input Amount!"); }
		 */
	}
	@Override
	public void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		/* if (amount > 0 && currentBalance + account.getOdlimit() >= amount) { */
			currentBalance -= amount;
			currentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
	/*
		 * else { throw new
		 * InsufficientFundsException("Invalid Input or Insufficient Funds!"); }
		 */
	}
	@Override
	public void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
			withdraw(sender, amount);
	}
	@Override
	public boolean updateAccount(CurrentAccount account) throws ClassNotFoundException, SQLException {
		return currentAccountDAO.updateAccountType(account);
	}
	@Override
	public CurrentAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return  currentAccountDAO.deleteAccount(accountNumber);
	}
	@Override
	public CurrentAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return currentAccountDAO.getAccountById(accountNumber);
	}
	@Override
	public double checkBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return currentAccountDAO.checkBalance(accountNumber);
	}
	@Override
	public List<CurrentAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		return currentAccountDAO.sortByAccountHolderName();
	}
	@Override
	public List<CurrentAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) throws ClassNotFoundException, SQLException {
		return currentAccountDAO.sortByBalanceRange(minimumBalance,maximumBalance);
	}
	@Override
	public List<CurrentAccount> sortByAccountHolderNameDescending() throws SQLException, ClassNotFoundException {
		return currentAccountDAO.sortByAccountHolderNameDescending();
	}
	@Override
	public List<CurrentAccount> sortByBalanceRangeDescending(
			int minimumBalanceDescending, int maximumBalanceDescending) throws ClassNotFoundException, SQLException {
		return currentAccountDAO.sortByBalanceRangeDescending(minimumBalanceDescending,maximumBalanceDescending);
	}
	@Override
	public List<CurrentAccount> getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return currentAccountDAO.getAccountByName(accountHolderName);
	}
	@Override
	public List<CurrentAccount> getAllBelowBalance(int balanceNumber) throws ClassNotFoundException, SQLException {
		return currentAccountDAO.getAllBelowBalance(balanceNumber);
	}
	@Override
	public List<CurrentAccount> getAllAboveBalance(int balanceNumber) throws ClassNotFoundException, SQLException {
		return currentAccountDAO.getAllAboveBalance(balanceNumber);
	}
	@Override
	public List<CurrentAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {
		return currentAccountDAO.sortByAccountBalance();
	}
	@Override
	public List<CurrentAccount> sortByAccountBalanceDescending() throws ClassNotFoundException, SQLException {
		return currentAccountDAO.sortByAccountBalanceDescending();
	}
}