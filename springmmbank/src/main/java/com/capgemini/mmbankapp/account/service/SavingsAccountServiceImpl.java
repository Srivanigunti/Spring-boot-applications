package com.capgemini.mmbankapp.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.mmbankapp.account.SavingsAccount;
import com.capgemini.mmbankapp.account.dao.SavingsAccountDAO;
import com.capgemini.mmbankapp.account.dao.SavingsAccountDAOImpl;
import com.capgemini.mmbankapp.account.factory.AccountFactory;
import com.capgemini.mmbankapp.account.util.DBUtil;
import com.capgemini.mmbankapp.exception.AccountNotFoundException;
import com.capgemini.mmbankapp.exception.InsufficientFundsException;
import com.capgemini.mmbankapp.exception.InvalidInputException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
	}

	
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	
	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		}else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}
	
	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		} else {
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}
	}

	
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		try {
			withdraw(sender, amount);
			deposit(receiver, amount);
			DBUtil.commit();
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
			DBUtil.rollback();
		} catch(Exception e) {
			e.printStackTrace();
			DBUtil.rollback();
		}
	}

	/*@Override
	public SavingsAccount updateAccount( int accountNumber, String NewAccountHolderName) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.updateAccount(accountNumber,NewAccountHolderName);
	}*/
	
	public boolean updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.updateAccountType(account);
	}

	
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}
	
	
	public SavingsAccount getAccountByName(String accountHolderName) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountByName(accountHolderName);
	}
	
	
	public List<SavingsAccount> getAccountsBetweenTheSalary(double minimum, double maximum) throws ClassNotFoundException, SQLException  {
		return savingsAccountDAO.getAccountsBetweenTheSalary(minimum, maximum);
	}
	

	
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
	//	SavingsAccount account = factory.deleteAccount(accountNumber);
		
		return savingsAccountDAO.deleteAccount(accountNumber);
	}
	
	public double checkBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException  {
	
		
		return savingsAccountDAO.checkBalance(accountNumber);
	}

}