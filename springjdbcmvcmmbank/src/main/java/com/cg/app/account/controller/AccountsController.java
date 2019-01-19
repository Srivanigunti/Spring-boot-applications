package com.cg.app.account.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.cg.app.account.SavingsAccount;
import com.cg.app.account.service.SavingsAccountService;
import com.cg.app.account.validator.SavingsAccountValidator;
import com.cg.app.exception.AccountNotFoundException;

//@SessionAttributes("account")

@Controller
public class AccountsController {

	@Autowired
	private SavingsAccountService savingsAccountService;
	@Autowired
	private SavingsAccountValidator savingsAccountValidator;

	boolean toggle;

	@RequestMapping("/newSA")
	public String createNewsavingsAccountForm(Model model) {
		model.addAttribute("account", new SavingsAccount());
		return "CreateSavingsAccountForm";
	}

	@RequestMapping("/createAccount")
	public String createNewsavingsAccount(@ModelAttribute("account") SavingsAccount account, BindingResult result) {
		savingsAccountValidator.validate(account, result);
		if (result.hasErrors()) {
			return "CreateSavingsAccountForm";
		}
		savingsAccountService.createNewAccount(account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(), account.isSalary());
		return "redirect:getAll";
	}

	/*
	 * @RequestMapping(value="/afterSave",method=RequestMethod.GET) public String
	 * save(SessionStatus status) { status.setComplete(); return "details"; }
	 */
	@RequestMapping("/getAll")
	public String getAllSavingAccounts(Model model) {
		List<SavingsAccount> savingsAccounts = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts", savingsAccounts);
		return "AccountDetails";
	}

	@RequestMapping("/close")
	public String closeAccountForm() {
		return "CloseAccountForm";
	}

	@RequestMapping("/closeAccount")
	public String getAllSavingAccounts(@RequestParam("accountNumber") int accountNumber) {
		try {
			savingsAccountService.deleteAccount(accountNumber);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAll";
	}

	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "WithdrawForm";
	}

	@RequestMapping("/withdrawAmount")
	public String withdraw(@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") double amount) {

		try {
			SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.withdraw(savingsAccount, amount);
		} catch (AccountNotFoundException e) {

			e.printStackTrace();
		}
		return "redirect:getAll";

	}

	@RequestMapping("/deposit")
	public String depositForm() {
		return "DepositForm";
	}

	@RequestMapping("/depositAmount")
	public String deposit(@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") double amount) {
		try {
			SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.deposit(savingsAccount, amount);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAll";
	}

	@RequestMapping("/fundTansfer")
	public String fundTransfer() {
		return "FundTransferForm";
	}

	@RequestMapping("/transferFunds")
	public String transfer(@RequestParam("sender") int sender, @RequestParam("receiver") int receiver,
			@RequestParam("amount") double amount) {
		try {
			SavingsAccount senderAccount = savingsAccountService.getAccountById(sender);
			SavingsAccount receiverAccount = savingsAccountService.getAccountById(receiver);
			savingsAccountService.fundTransfer(senderAccount, receiverAccount, amount);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}

		return "redirect:getAll";
	}

	@RequestMapping("/currentBalanceForm")
	public String currentBalanceForm() {
		return "CurrentBalanceForm";
	}

	@RequestMapping("/currentBalance")
	public String currentBalance(@RequestParam("accountNumber") int accountNumber, Model model) {
		double balance;
		try {
			balance = savingsAccountService.checkBalance(accountNumber);
			model.addAttribute("balance", balance);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "DisplayBalance";
	}

	@RequestMapping("/searchIdForm")
	public String searchByIdForm() {
		return "DisplayAccountForm";
	}

	@RequestMapping("/searchId")
	public String searchById(@RequestParam("accountNumber") int accountNumber, Model model) {
		try {
			SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
			model.addAttribute("account", account);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "DisplayAccount";
	}

	@RequestMapping("/searchNameForm")
	public String searchByNameForm() {
		return "SearchByNameForm";
	}

	@RequestMapping("/searchName")
	public String searchByName(@RequestParam("accountHolderName") String accountHolderName, Model model) {
		try {
			List<SavingsAccount> accounts = savingsAccountService.getAccountByName(accountHolderName);
			model.addAttribute("accounts", accounts);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "DisplayByName";
	}

	@RequestMapping("/updateAccountForm")
	public String updateAccountForm() {
		return "UpdateAccountForm";
	}
	@RequestMapping("/update")
	public String update(@RequestParam("accountNumber") int accountNumber, Model model) {
		try {
			SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
			model.addAttribute("account", savingsAccount);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "Update";
	}
	@RequestMapping("/updateAccount")
	public String updateAccount(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") double accountBalance, @RequestParam("salary") boolean salary) {
		try {
			SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccount.getBankAccount().setAccountHolderName(accountHolderName);
			savingsAccount.setSalary(salary);
			savingsAccountService.updateAccount(savingsAccount);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAll";
	}
	@RequestMapping("/sortByNumber")
	public String sortByNumber(Model model) {
		toggle = !toggle;
		Collection<SavingsAccount> accountsNumber;
		accountsNumber = savingsAccountService.getAllSavingsAccount();
		Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = arg0.getBankAccount().getAccountNumber() - arg1.getBankAccount().getAccountNumber();
				if (toggle == true) {
					return result;
				} else
					return -result;
			}
		});
		accountSet.addAll(accountsNumber);
		model.addAttribute("accounts", accountSet);
		return "AccountDetails";

	}

	@RequestMapping("/sortByName")
	public String sortByName(Model model) {
		toggle = !toggle;
		Collection<SavingsAccount> accountsName;
		accountsName = savingsAccountService.getAllSavingsAccount();
		ArrayList<SavingsAccount> accountsNameList = new ArrayList<SavingsAccount>(accountsName);
		Collections.sort(accountsNameList, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = arg0.getBankAccount().getAccountHolderName()
						.compareTo(arg1.getBankAccount().getAccountHolderName());
				if (toggle == true) {
					return result;
				} else
					return -result;
			}
		});
		model.addAttribute("accounts", accountsNameList);
		return "AccountDetails";
	}

	@RequestMapping("/sortByBalance")
	public String sortByBalance(Model model) {
		toggle = !toggle;
		Collection<SavingsAccount> accountsBal = savingsAccountService.getAllSavingsAccount();
		ArrayList<SavingsAccount> accountsBalList = new ArrayList<SavingsAccount>(accountsBal);
		Collections.sort(accountsBalList, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = (int) (arg0.getBankAccount().getAccountBalance()
						- arg1.getBankAccount().getAccountBalance());
				if (toggle == true) {
					return result;
				} else
					return -result;
			}
		});
		model.addAttribute("accounts", accountsBalList);
		return "AccountDetails";
	}
}