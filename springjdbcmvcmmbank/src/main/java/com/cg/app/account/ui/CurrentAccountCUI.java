package com.cg.app.account.ui;

import java.sql.SQLException;

import java.util.List;

import java.util.Scanner;

import com.cg.app.account.CurrentAccount;
import com.cg.app.account.service.CurrentAccountService;
import com.cg.app.account.service.CurrentAccountServiceImpl;
import com.cg.app.account.util.DBUtil;
import com.cg.app.exception.AccountNotFoundException;

public class CurrentAccountCUI {
	private static Scanner scanner = new Scanner(System.in);
	private static CurrentAccountService currentAccountService = new CurrentAccountServiceImpl();

	public static void startCurrentAccount() {
		do {
			System.out.println("****** Welcome to Money Money Bank********");
			System.out.println("1. Open New Current Account");
			System.out.println("2. Update Account");
			System.out.println("3. Close Account");
			System.out.println("4. Search Account");
			System.out.println("5. Withdraw");
			System.out.println("6. Deposit");
			System.out.println("7. FundTransfer");
			System.out.println("8. Check Current Balance");
			System.out.println("9. Get All Account Details");
			System.out.println("10. Sort Accounts");
			System.out.println("11. Exit");
			System.out.println("Make your choice: ");
			int choice = scanner.nextInt();
			performCurrentOperation(choice);
		} while (true);
	}

	private static void performCurrentOperation(int choice) {
		CurrentAccount currentAccount = null;
		switch (choice) {
		case 1:
			acceptInput("CA");
			break;
		case 2:
			System.out.println("Enter your account number to update : ");
			int accountId = scanner.nextInt();
			try {
				currentAccount = currentAccountService.getAccountById(accountId);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e1) {
				e1.printStackTrace();
			}
			System.out.println("To Update Your Name Enter " + "1");
			System.out.println("To Update Your odlimit " + "2");
			System.out.println("To Update Your account " + "3");
			System.out.println("Redirect to start menu " + "4");
			int select = scanner.nextInt();
			selectOptionsToUpdate(select, currentAccount);
			break;
		case 3:
			closeAccount();
			break;
		case 4:
			searchAccount();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			deposit();
			break;
		case 7:
			fundTransfer();
			break;
		case 8:
			checkBalance();
			break;
		case 9:
			showAllAccounts();
			break;
		case 10:
			sortAccounts();
			break;
		case 11:
			try {
				DBUtil.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice!");
			break;
		}
	}

	private static void showAllAccounts() {
		List<CurrentAccount> currentAccounts;
		try {
			currentAccounts = currentAccountService.getAllCurrentAccount();
			for (CurrentAccount currentAccount : currentAccounts) {
				System.out.println(currentAccount);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static void checkBalance() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		try {
			double balance = currentAccountService.checkBalance(accountNumber);
			System.out.println("Available balance : " + balance + " rs");
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void fundTransfer() {
		System.out.println("Enter Account Sender's Number: ");
		int senderAccountNumber = scanner.nextInt();
		System.out.println("Enter Account Receiver's Number: ");
		int receiverAccountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		try {
			CurrentAccount senderCurrentAccount = currentAccountService.getAccountById(senderAccountNumber);
			CurrentAccount receiverCurrentAccount = currentAccountService.getAccountById(receiverAccountNumber);
			currentAccountService.fundTransfer(senderCurrentAccount, receiverCurrentAccount, amount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void deposit() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		CurrentAccount currentAccount = null;
		try {
			currentAccount = currentAccountService.getAccountById(accountNumber);
			currentAccountService.deposit(currentAccount, amount);
			DBUtil.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			try {
				DBUtil.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void withdraw() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		CurrentAccount currentAccount = null;
		try {
			currentAccount = currentAccountService.getAccountById(accountNumber);
			currentAccountService.withdraw(currentAccount, amount);
			DBUtil.commit();
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
			try {
				DBUtil.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			try {
				DBUtil.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void searchAccount() {
		do {
			System.out.println("+++Ways of Searching+++");
			System.out.println("1.Search By Account Id");
			System.out.println("2.Search By Account Holder Name");
			System.out.println("3.Enter amount to return accounts less than that amount");
			System.out.println("4.Enter amount to return accounts greater than that amount");
			System.out.println("5.Redirect to start menu");
			int search = scanner.nextInt();
			switch (search) {
			case 1:
				System.out.println("Enter account number to search");
				int accountNumber = scanner.nextInt();
				try {
					CurrentAccount currentAccount = currentAccountService.getAccountById(accountNumber);
					System.out.println(currentAccount);
				} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter Account Holder  to search");
				String accountHolderName = scanner.nextLine();
				accountHolderName = scanner.nextLine();
				List<CurrentAccount> currentAccount;
				try {
					currentAccount = currentAccountService.getAccountByName(accountHolderName);
					System.out.println(currentAccount);
				} catch (ClassNotFoundException | SQLException | AccountNotFoundException e1) {
					e1.printStackTrace();
				}
				
				break;
			case 3:
				System.out.println("Enter balance to get accounts with balance less than or equal to given balance");
				int balanceNumber = scanner.nextInt();
				try {
					List<CurrentAccount> currentListLess = currentAccountService.getAllBelowBalance(balanceNumber);
					for (CurrentAccount current : currentListLess) {
						System.out.println(current);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("Enter balance to get accounts with balance greater than or equal to given balance");
				int aboveBalanceNumber = scanner.nextInt();
				try {
					List<CurrentAccount> currentListAbove = currentAccountService
							.getAllAboveBalance(aboveBalanceNumber);
					for (CurrentAccount current : currentListAbove) {
						System.out.println(current);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case 5:
				startCurrentAccount();
				break;
			default:
				System.err.println("Invalid Choice!");
				break;
			}
		} while (true);
	}

	private static void closeAccount() {

		System.out.println("Enter Account Number to be closed : ");

		int deleteAccountNumber = scanner.nextInt();

		try {

			currentAccountService.deleteAccount(deleteAccountNumber);

		} catch (AccountNotFoundException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	private static void selectOptionsToUpdate(int select, CurrentAccount currentAccount) {

		switch (select) {

		case 1:

			System.out.println("Enter new name change your name : ");

			String changeName = scanner.nextLine();

			changeName = scanner.nextLine();

			currentAccount.getBankAccount().setAccountHolderName(changeName);

			try {

				boolean name = currentAccountService.updateAccount(currentAccount);

				if (name == true) {

					System.out.println("Name Changed for " + currentAccount.getBankAccount().getAccountNumber() + " to "

							+ changeName);

				}

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			}

			break;

		case 2:

			System.out.println("Update odlimit");

			double odlimit = scanner.nextDouble();

			currentAccount.setOdlimit(odlimit);

			try {

				boolean odLimit = currentAccountService.updateAccount(currentAccount);

				if (odLimit == true) {

					System.out.println("Odlimit of  " + currentAccount.getBankAccount().getAccountNumber()

							+ " Changed to " + odlimit);

				}

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			}

			break;

		case 3:

			System.out.println("Enter new name change your name : ");

			String changename = scanner.nextLine();

			changename = scanner.nextLine();

			currentAccount.getBankAccount().setAccountHolderName(changename);

			double odLimit = scanner.nextDouble();

			currentAccount.setOdlimit(odLimit);

			try {

				boolean salaryResult;

				salaryResult = currentAccountService.updateAccount(currentAccount);

				if (salaryResult == true) {

					System.out.println("Name and Odlimit for " + currentAccount.getBankAccount().getAccountNumber()

							+ " Changed to " + changename + " and " + odLimit);

				}

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();

			}

			break;

		case 4:

			startCurrentAccount();

			break;

		default:

			System.err.println("Invalid Choice!");

			break;

		}

	}

	private static void acceptInput(String type) {

		if (type.equalsIgnoreCase("CA")) {

			System.out.println("Enter your Full Name: ");

			String accountHolderName = scanner.nextLine();

			accountHolderName = scanner.nextLine();

			System.out.println("Enter Initial Balance(type na for Zero Balance): ");

			String accountBalanceStr = scanner.next();

			double accountBalance = 0.0;

			if (!accountBalanceStr.equalsIgnoreCase("na")) {

				accountBalance = Double.parseDouble(accountBalanceStr);

			}

			System.out.println("Enter odLimit");

			double odlimit = scanner.nextDouble();

			createCurrentAccount(accountHolderName, accountBalance, odlimit);

		}

	}

	private static void createCurrentAccount(String accountHolderName, double accountBalance, double odlimit) {

		try {

			currentAccountService.createNewAccount(accountHolderName, accountBalance, odlimit);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	private static void sortAccounts() {

		do {

			System.out.println("+++Ways of Sorting+++");

			System.out.println("1.Sort By Account Holder Name");

			System.out.println("2.Enter account balance range to sort in ascending order of the balance");

			System.out.println("3.Sort By Account Holder Name in descending order");

			System.out.println("4.Enter account balance range to sort in descending order of the balance");

			System.out.println("5.Sort By Account Balance");

			System.out.println("6.Sort By Account Balance in descending order");

			System.out.println("7.Redirect to start menu");

			int choose = scanner.nextInt();

			List<CurrentAccount> currentAccountsList = null;

			switch (choose) {

			case 1:

				try {

					currentAccountsList = currentAccountService.sortByAccountHolderName();

					for (CurrentAccount current : currentAccountsList) {

						System.out.println(current);

					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}

				break;

			case 2:

				System.out.println("Enter minimun range");

				int minimumBalance = scanner.nextInt();

				System.out.println("Enter maximum range");

				int maximumBalance = scanner.nextInt();

				try {

					currentAccountsList = currentAccountService.sortByBalanceRange(minimumBalance, maximumBalance);

					for (CurrentAccount current : currentAccountsList) {

						System.out.println(current);

					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}

				break;

			case 3:

				try {

					currentAccountsList = currentAccountService.sortByAccountHolderNameDescending();

					for (CurrentAccount current : currentAccountsList) {

						System.out.println(current);

					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}

				break;

			case 4:

				System.out.println("Enter minimun range");

				int minimumBalanceDescending = scanner.nextInt();

				System.out.println("Enter maximum range");

				int maximumBalanceDescending = scanner.nextInt();

				try {

					currentAccountsList = currentAccountService.sortByBalanceRangeDescending(minimumBalanceDescending,

							maximumBalanceDescending);

					for (CurrentAccount current : currentAccountsList) {

						System.out.println(current);

					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}

				break;

			case 5:

				try {

					currentAccountsList = currentAccountService.sortByAccountBalance();

					for (CurrentAccount current : currentAccountsList) {

						System.out.println(current);

					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}

				break;

			case 6:

				try {

					currentAccountsList = currentAccountService.sortByAccountBalanceDescending();

					for (CurrentAccount current : currentAccountsList) {

						System.out.println(current);

					}

				} catch (ClassNotFoundException | SQLException e) {

					e.printStackTrace();

				}

				break;

			case 7:

				startCurrentAccount();

				break;

			default:

				System.err.println("Invalid Choice!");

				break;

			}

		} while (true);

	}

}