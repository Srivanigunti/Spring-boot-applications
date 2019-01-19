package com.cg.app.account.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cg.app.account.SavingsAccount;

@Component
public class SavingsAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SavingsAccount account = (SavingsAccount) target;

		if (!account.getBankAccount().getAccountHolderName().matches("[a-zA-Z\\s]*$")) {
			errors.rejectValue("bankAccount.accountHolderName", "accountHolderName.chars",
					"* Employee name must contain characters only");
		}

		if (account.getBankAccount().getAccountHolderName().isEmpty()) {
			errors.rejectValue("bankAccount.accountHolderName", "accountHolderName.isempty",
					"* Employee name must not be empty");
		}
		if (account.getBankAccount().getAccountHolderName().length() < 2) {
			errors.rejectValue("bankAccount.accountHolderName", "accountHolderName.isempty",
					"* Employee name must have atleast 2 characters");
		}
		if (account.getBankAccount().getAccountBalance() < 0) {
			errors.rejectValue("bankAccount.accountBalance", "accountBalance.minimum", "Salary must be greater than 0");
		}
	}
}
