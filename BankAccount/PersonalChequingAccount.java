/**
 * Program:			PersonalChequingAccount.java
 * Date:			Feb 4, 2020 
 * Author:			Vincent Li 
 * Description:		The users personal chequing account that stores their bank info
 * 
 */


import java.util.*;

public class PersonalChequingAccount extends BankAccount implements InterestPayable
{

	// Data members
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;

	// Need to find a way to make this work
	private ArrayList <Transaction> record = new ArrayList<Transaction>();

	// constants
	private final static double INT_RATE = 0.025;
	private final static double SERVICE_FEE = 0.85;

	// Zero arg constructor
	PersonalChequingAccount()
	{
		// call to super class
		// setting everything to null
		super(null, null, null);

		accountNumber = null;
		numberDeposits = 0;
		numberWithdrawals = 0;
		balance = 0.0;
		accountActive = false;

		// need to make it work
		//record = null;

	}

	// three arg constructor
	PersonalChequingAccount(String customerName, String month, double balance)
	{
		// call to super class
		super(customerName, "Chequing", month);

		// seting balances and other stuff
		this.balance = balance;

		// calling methods
		accountNumber = generateAccountNumber(); // creating a bank number 
		accountActive = isAccountActive(); // checking if they had enough to make an active account

	}
	// getters and setters
	public String getAccountNumber()
	{
		return accountNumber;
	}


	public int getNumberWithdrawals()
	{
		return numberWithdrawals;
	}

	public int getNumberDeposits()
	{
		return numberDeposits;
	}

	public double getBalance()
	{
		return balance;
	}

	public static double getIntRate()
	{
		return INT_RATE;
	}

	public static double getServiceFee()
	{
		return SERVICE_FEE;
	}

	// utility methods	
	/**
	 * \fn:		isAccountActive
	 * \brief:	Sets the account to active or inactive depending on the balance
	 * \param:	nothing
	 * \return:	boolean
	 */
	public boolean isAccountActive()
	{
		// if less than 25, the account is disabled
		if (balance < 25.00)
		{
			accountActive = false;
		}
		else // if they have more than 25, the account will remain active
		{
			accountActive = true;
		}
		return accountActive;
	}


	/**
	 * \fn:		deposit
	 * \brief:	used to deposit to users chequing account
	 * \param:	double, int
	 * \return:	nothing
	 */
	public void deposit(double depositAmount, int day)
	{
		// adds money to balance
		balance += depositAmount;

		// increment deposit number
		numberDeposits ++;

		// checks if they have over 25 dollars or not
		accountActive = isAccountActive();

		// record transaction
		recordTransaction(day,"Deposit:",depositAmount,balance);
	}

	/**
	 * \fn:		withdrawal
	 * \brief:	used to withdraw money from users chequing account
	 * \param:	double, int
	 * \return:	nothing
	 */
	public void withdrawal(double withdrawalAmount, int day)
	{
		// local variable
		String transactionMessage = "";

		balance -= withdrawalAmount;
		// checking the different states of withdrawal
		if (balance > 0 && accountActive == true)
		{
			transactionMessage = "Withdrawal:";
			numberWithdrawals++;

		}
		else if(balance < 0 && accountActive == true)
		{
			transactionMessage = "Transaction Cancelled. Insufficient funds.";

			// to make it so their balance is not negative since transaction is canacelled
			balance += withdrawalAmount;
		}
		else if (accountActive == false)
		{
			transactionMessage = "Transaction Cancelled. Account is inactive.";
			// to make it so their balance is not negative since transaction is canacelled
			balance += withdrawalAmount;
		}

		
		// calling methods
		accountActive = isAccountActive(); // checking if their account is still valid
		recordTransaction(day, transactionMessage, withdrawalAmount, balance); // recording transaction
	}

	/**
	 * \fn:		calcInterest
	 * \brief:	calculates interest fees
	 * \param:	nothing
	 * \return:	nothing
	 */
	public void calcInterest()
	{
		// getting the month
		String month = getMonth();

		// getting the day
		int day = 0;

		// determining the last day of the month
		switch(month)
		{
		case "January":
			day = 31;
			break;
		case "February":
			day = 28;
			break;
		case "March":
			day = 31;
			break;
		case "April":
			day = 30;
			break;
		case "May":
			day = 31;
			break;
		case "June":
			day = 30;
			break;
		case "July":
			day = 31;
			break;
		case "August":
			day = 31;
			break;
		case "September":
			day = 30;
			break;
		case "October":
			day = 31;
			break;
		case "November":
			day = 30;
			break;
		case "December":
			day = 31;
			break;
		default: 
			month = "null";
			day = 0;
		}

		// calculating interest
		double interest = 0;

		// if balance is greater than or equal to 1000, add the interest and the transaction
		if (balance >= 1000)
		{
			interest = (balance*(INT_RATE/12.0));
			balance += interest;
		}

		// records transaction
		recordTransaction(day, "Interest:", interest, balance);

	}

	/**
	 * \fn:		recordTransaction
	 * \brief:	records the transactions from the chequing
	 * \param:	int, string, double, double
	 * \return:	nothing
	 */
	public void recordTransaction(int day, String transaction, double amount, double balance)
	{
		// making a new object to set the data and get the month
		Transaction trans = new Transaction();
		String month = getMonth();
		// setting the info
		trans.set(month, day, transaction, amount, balance);

		// recording data to arrayList
		record.add(trans);
	}

	/**
	 * \fn:		printTransactions
	 * \brief:	prints the transactions
	 * \param:	nothing
	 * \return:	nothing
	 */
	public void printTransactions()
	{
		// printing the title
		System.out.println("**********************************************");
		System.out.println("Transaction Record for the Month of "+getMonth());
		System.out.println("**********************************************");
		// printing out record details using the toString method
		// using the replace function to get rid of the , [ ]
		System.out.println(" "+ record.toString().replace(",", "").replace("[", "").replace("]", ""));

	}

	/**
	 * \fn:		monthlyProcess
	 * \brief:	shows the users monthly processes
	 * \param:	nothing
	 * \return:	nothing
	 */
	public void monthlyProcess()
	{
		// getting the month
		String month = getMonth();

		// getting the day
		int day = 0;

		// determining the last day of the month
		switch(month)
		{
		case "January":
			day = 31;
			break;
		case "February":
			day = 28;
			break;
		case "March":
			day = 31;
			break;
		case "April":
			day = 30;
			break;
		case "May":
			day = 31;
			break;
		case "June":
			day = 30;
			break;
		case "July":
			day = 31;
			break;
		case "August":
			day = 31;
			break;
		case "September":
			day = 30;
			break;
		case "October":
			day = 31;
			break;
		case "November":
			day = 30;
			break;
		case "December":
			day = 31;
			break;
		default: 
			month = "null";
			day = 0;
		}

		// calculating service fee
		double service = 0;
		// adding to service if withdrawals exceed 4
		if (numberWithdrawals > 4)
		{
			service = numberWithdrawals * SERVICE_FEE;
			balance -= service; // subtracting fee
		}
		// calling methods 
		calcInterest(); // calculate the interest at the end of the month
		recordTransaction(day, "Service Fee", service, balance); // recording data
		printTransactions(); // printing data

		// updating account active 
		accountActive = isAccountActive();
	}

	/**
	 * \fn:		toString
	 * \brief:	overriding the toString method for our own uses
	 * \param:	nothing
	 * \return:	String
	 */
	public String toString()
	{
		// making a formatted string for balance so it always shows the 2 decimal places
		String bal = String.format("%.2f", balance);

		return ("Account Number: \t\t" + accountNumber + "\nNumber of Withdrawals: \t\t" +numberWithdrawals + "\nNumber of Deposits: \t\t"
				+ numberDeposits + "\nBalance: \t\t\t$" + bal + "\nAccount Active: \t\t" + accountActive + "\nAnnual Interest Rate: \t\t" 
				+ (INT_RATE*100) + "%\nMonthly Service Fee Rate:\t$" + SERVICE_FEE + " (applied to no. of withdrawals if withdrawals over 4)\n");
	}

	/**
	 * \fn:		generateAccountNumber
	 * \brief:	creates an account number
	 * \param:	nothing
	 * \return:	String
	 */
	public String generateAccountNumber()
	{
		// initializing the variables that holds the size and the card number
		int range = 6;
		String card = "002-623490-";

		// generating the 6 random numbers for the card
		for (int i = 0 ; i < range; i++)
		{
			int rand = (int)(Math.random()*10);
			card += rand;
		}
		// adds the transit number
		card += "-550";

		return card;

	}

}
