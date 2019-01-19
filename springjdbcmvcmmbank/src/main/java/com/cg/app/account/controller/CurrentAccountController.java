package com.cg.app.account.controller;

import java.sql.SQLException;
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

import com.cg.app.account.CurrentAccount;
import com.cg.app.account.service.CurrentAccountService;
import com.cg.app.exception.AccountNotFoundException;

@Controller
public class CurrentAccountController {
	@Autowired
	private CurrentAccountService currentAccountService;
	boolean toggle;

	@RequestMapping("/newCA")
	public String createNewsavingsAccountForm(Model model) {
		model.addAttribute("account", new CurrentAccount());
		return "CreateCurrentAccountForm";
	}

	@RequestMapping("/createAccountCurrent")
	public String createNewsavingsAccount(@ModelAttribute("account") CurrentAccount account, BindingResult result) throws ClassNotFoundException, SQLException {
		//currentAccountValidator.validate(account, result);
		if (result.hasErrors()) {
			return "CreateCurrentAccountForm";
		}
		currentAccountService.createNewAccount(account.getBankAccount().getAccountHolderName(),
				account.getBankAccount().getAccountBalance(), account. getOdlimit());
		return "redirect:getAllCurrent";
	}

	/*
	 * @RequestMapping(value="/afterSave",method=RequestMethod.GET) public String
	 * save(SessionStatus status) { status.setComplete(); return "details"; }
	 */
	@RequestMapping("/getAllCurrent")
	public String getAllCurrentAccounts(Model model){

		try {
			List<CurrentAccount> currentAccounts = currentAccountService.getAllCurrentAccount();
			model.addAttribute("accounts", currentAccounts);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "AccountDetailsCurrent";
	}

	@RequestMapping("/closeCurrent")
	public String closeAccountForm() {
		return "CloseAccountFormCurrent";
	}

	@RequestMapping("/closeAccountCurrent")
	public String getAllSavingAccounts(@RequestParam("accountNumber") int accountNumber) {
		try {
			try {
				currentAccountService.deleteAccount(accountNumber);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAllCurrent";
	}

	@RequestMapping("/withdrawCurrent")
	public String withdrawForm() {
		return "WithdrawFormCurrent";
	}

	@RequestMapping("/withdrawAmountCurrent")
	public String withdraw(@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") double amount) {

		try {
			CurrentAccount currentAccount = currentAccountService.getAccountById(accountNumber);
				currentAccountService.withdraw(currentAccount, amount);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		} catch (AccountNotFoundException e) {

			e.printStackTrace();
		}
		return "redirect:getAllCurrent";

	}

	@RequestMapping("/depositCurrent")
	public String depositForm() {
		return "DepositFormCurrent";
	}

	@RequestMapping("/depositAmountCurrent")
	public String deposit(@RequestParam("accountNumber") int accountNumber, @RequestParam("amount") double amount) {
		try {
			CurrentAccount currentAccount;
				currentAccount = currentAccountService.getAccountById(accountNumber);
				currentAccountService.deposit(currentAccount, amount);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAllCurrent";
	}

	@RequestMapping("/fundTansferCurrent")
	public String fundTransfer() {
		return "FundTransferFormCurrent";
	}

	@RequestMapping("/transferFundsCurrent")
	public String transfer(@RequestParam("sender") int sender, @RequestParam("receiver") int receiver,
			@RequestParam("amount") double amount) {
			try {
				CurrentAccount senderAccount = currentAccountService.getAccountById(sender);
				CurrentAccount receiverAccount = currentAccountService.getAccountById(receiver);
				currentAccountService.fundTransfer(senderAccount, receiverAccount, amount);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAllCurrent";
	}

	@RequestMapping("/currentBalanceFormCurrent")
	public String currentBalanceForm() {
		return "CurrentBalanceFormCurrent";
	}

	@RequestMapping("/currentBalanceCurrent")
	public String currentBalance(@RequestParam("accountNumber") int accountNumber, Model model) {
		double balance;
			try {
				balance = currentAccountService.checkBalance(accountNumber);
				model.addAttribute("balance", balance);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "DisplayBalanceCurrent";
	}

	@RequestMapping("/searchIdFormCurrent")
	public String searchByIdForm() {
		return "DisplayAccountFormCurrent";
	}

	@RequestMapping("/searchIdCurrent")
	public String searchById(@RequestParam("accountNumber") int accountNumber, Model model) {
		try {
			CurrentAccount account = currentAccountService.getAccountById(accountNumber);
				model.addAttribute("account", account);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "DisplayAccountCurrent";
	}

	@RequestMapping("/searchNameFormCurrent")
	public String searchByNameForm() {
		return "SearchByNameFormCurrent";
	}

	@RequestMapping("/searchNameCurrent")
	public String searchByName(@RequestParam("accountHolderName") String accountHolderName, Model model) {
		try {
			List<CurrentAccount> accounts = currentAccountService.getAccountByName(accountHolderName);
				model.addAttribute("accounts", accounts);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "DisplayByNameCurrent";
	}

	@RequestMapping("/updateAccountFormCurrent")
	public String updateAccountForm() {
		return "UpdateAccountFormCurrent";
	}
	@RequestMapping("/updateCurrent")
	public String update(@RequestParam("accountNumber") int accountNumber, Model model) {
			
			try {
				CurrentAccount currentAccount = currentAccountService.getAccountById(accountNumber);
				model.addAttribute("account", currentAccount);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	 catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "UpdateCurrent";
	}
	@RequestMapping("/updateAccountCurrent")
	public String updateAccount(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") double accountBalance, @RequestParam("odlimit") double odlimit) {
			try {
				CurrentAccount currentAccount = currentAccountService.getAccountById(accountNumber);
				currentAccount.getBankAccount().setAccountHolderName(accountHolderName);
				currentAccount.setOdlimit(odlimit);
				currentAccountService.updateAccount(currentAccount);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		 catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return "redirect:getAllCurrent";
	}
	@RequestMapping("/sortByNumberCurrent")
	public String sortByNumber(Model model) {
		toggle = !toggle;
		
		try {
			Collection<CurrentAccount> accountsNumber = currentAccountService.getAllCurrentAccount();
			Set<CurrentAccount> accountSet = new TreeSet<>(new Comparator<CurrentAccount>() {
				@Override
				public int compare(CurrentAccount arg0, CurrentAccount arg1) {
					int result = arg0.getBankAccount().getAccountNumber() - arg1.getBankAccount().getAccountNumber();
					if (toggle == true) {
						return result;
					} else
						return -result;
				}
			});
			accountSet.addAll(accountsNumber);
			model.addAttribute("accounts", accountSet);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "AccountDetailsCurrent";
	}
	@RequestMapping("/sortByNameCurrent")
	public String sortByName(Model model) {
		toggle = !toggle;
		try {
			Collection<CurrentAccount> accountsName = currentAccountService.getAllCurrentAccount();
			ArrayList<CurrentAccount> accountsNameList = new ArrayList<CurrentAccount>(accountsName);
			Collections.sort(accountsNameList, new Comparator<CurrentAccount>() {
				@Override
				public int compare(CurrentAccount arg0, CurrentAccount arg1) {
					int result = arg0.getBankAccount().getAccountHolderName()
							.compareTo(arg1.getBankAccount().getAccountHolderName());
					if (toggle == true) {
						return result;
					} else
						return -result;
				}
			});
			model.addAttribute("accounts", accountsNameList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "AccountDetailsCurrent";
	}

	@RequestMapping("/sortByBalanceCurrent")
	public String sortByBalance(Model model) {
		toggle = !toggle;
		
		try {
			Collection<CurrentAccount> accountsBal = currentAccountService.getAllCurrentAccount();
			ArrayList<CurrentAccount> accountsBalList = new ArrayList<CurrentAccount>(accountsBal);
			Collections.sort(accountsBalList, new Comparator<CurrentAccount>() {
				@Override
				public int compare(CurrentAccount arg0, CurrentAccount arg1) {
					int result = (int) (arg0.getBankAccount().getAccountBalance()
							- arg1.getBankAccount().getAccountBalance());
					if (toggle == true) {
						return result;
					} else
						return -result;
				}
			});
			model.addAttribute("accounts", accountsBalList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "AccountDetailsCurrent";
	}
	@RequestMapping("/sortByOdLimitCurrent")
	public String sortByOdLimit(Model model) {
		toggle = !toggle;
		try {
			Collection<CurrentAccount> accountsOdlimit = currentAccountService.getAllCurrentAccount();
			ArrayList<CurrentAccount> accountsOdlimitList = new ArrayList<CurrentAccount>(accountsOdlimit);
			Collections.sort(accountsOdlimitList, new Comparator<CurrentAccount>() {
				@Override
				public int compare(CurrentAccount arg0, CurrentAccount arg1) {
					int result = (int) (arg0.getOdlimit()
							- arg1.getOdlimit());
					if (toggle == true) {
						return result;
					} else
						return -result;
				}
			});
			model.addAttribute("accounts", accountsOdlimitList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "AccountDetailsCurrent";
	}
}
