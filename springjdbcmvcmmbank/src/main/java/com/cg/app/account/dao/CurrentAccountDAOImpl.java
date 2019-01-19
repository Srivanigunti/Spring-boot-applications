package com.cg.app.account.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.account.CurrentAccount;
import com.cg.app.account.mapper.CurrentAccountDetailsMapper;
import com.cg.app.exception.AccountNotFoundException;

@Repository
public class CurrentAccountDAOImpl implements CurrentAccountDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CurrentAccount createNewAccount(CurrentAccount account){
		jdbcTemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",
				new Object[] { account.getBankAccount().getAccountNumber(),
						account.getBankAccount().getAccountHolderName(), account.getBankAccount().getAccountBalance(),
						null, account.getOdlimit(), "CA" });
		return account;
	}
	@Override
	public List<CurrentAccount> getAllCurrentAccount()  {
		return jdbcTemplate.query("SELECT * FROM ACCOUNT WHERE od_limit>=?",new Object[] {0}, new CurrentAccountDetailsMapper());
	}
	@Override
	public void updateBalance(int accountNumber, double currentBalance) {
		jdbcTemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",
				new Object[] { currentBalance, accountNumber });
	}
	@Override
	public CurrentAccount getAccountById(int accountNumber)
			throws  AccountNotFoundException {
		CurrentAccount account =  jdbcTemplate.queryForObject("SELECT * FROM account where account_id=? AND od_limit>=?", new Object[] { accountNumber,0 },
				new CurrentAccountDetailsMapper());
		return account;

	}
	@Override
	public CurrentAccount deleteAccount(int accountNumber)
			throws AccountNotFoundException {
		jdbcTemplate.update("DELETE FROM account WHERE account_id=?", new Object[] { accountNumber });
		return null;
	}
	@Override
	public double checkBalance(int accountNumber)
			throws AccountNotFoundException{
		return jdbcTemplate.queryForObject("SELECT account_bal FROM account where account_id=?",
				new Object[] { accountNumber }, Double.class);
	}
	@Override
	public boolean updateAccountType(CurrentAccount account){

		jdbcTemplate.update("UPDATE ACCOUNT SET account_hn=?,od_limit=? WHERE account_id=?",
				new Object[] { account.getBankAccount().getAccountHolderName(), account.getOdlimit(),
						account.getBankAccount().getAccountNumber() });

		return false;
	}
	@Override
	public List<CurrentAccount> sortByAccountHolderName() {

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_hn", new CurrentAccountDetailsMapper());
	}

	@Override
	public List<CurrentAccount> sortByBalanceRange(int minimumBalance, int maximumBalance){

		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal",
				new Object[] { minimumBalance, maximumBalance }, new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> sortByAccountHolderNameDescending(){

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_hn DESC", new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> sortByBalanceRangeDescending(int minimumBalanceDescending, int maximumBalanceDescending){

		return jdbcTemplate.query("SELECT * FROM account WHERE account_bal BETWEEN ? and ? ORDER BY account_bal DESC",
				new Object[] { minimumBalanceDescending, maximumBalanceDescending }, new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> getAccountByName(String accountHolderName)
			throws AccountNotFoundException{

		return jdbcTemplate.query("SELECT * FROM account where account_hn=? AND od_limit>=?", new Object[] { accountHolderName,0 },
				new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> getAllBelowBalance(int balanceNumber) {

		return jdbcTemplate.query("SELECT * FROM ACCOUNT WHERE account_bal<=?", new Object[] { balanceNumber },
				new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> getAllAboveBalance(int balanceNumber) {

		return jdbcTemplate.query("SELECT * FROM ACCOUNT WHERE account_bal>=?", new Object[] { balanceNumber },
				new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> sortByAccountBalance(){

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_bal", new CurrentAccountDetailsMapper());
	}

	public List<CurrentAccount> sortByAccountBalanceDescending() {

		return jdbcTemplate.query("SELECT * FROM account ORDER BY account_bal DESC", new CurrentAccountDetailsMapper());
	}
}	