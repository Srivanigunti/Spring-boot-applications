package com.cg.app.account.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.app.account.CurrentAccount;

public class CurrentAccountDetailsMapper implements RowMapper<CurrentAccount> {

	@Override
	public CurrentAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		int accountNumber = rs.getInt(1);
		String accountHolderName = rs.getString("account_hn");
		double accountBalance = rs.getDouble(3);
		double odlimit =rs.getDouble(5);
		return new CurrentAccount(accountNumber, accountHolderName, accountBalance, odlimit);
	}

}
