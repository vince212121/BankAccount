/**
 * Program:			SavingAccount.java
 * Date:			Feb 9, 2020 
 * Author:			Vincent Li 
 * Description:		The savings account information that is used for the users savings account
 */


import java.util.ArrayList;

public class SavingAccount extends BankAccount implements InterestPayable
{
	// data memebers
	private String accountNumber;
	private int numberWithdrawals;
	private int numberDeposits;
	private double balance;
	private boolean accountActive;
	private ArrayList<Transaction> record = new ArrayList<Transaction>();

	// constants
	private final static double INT_RATE = 0.03;

	// zero arg constructor
	SavingAccount()
	{
		// setting everything in main class to null
		super(null, null, null);

		// setting data members to null
		accountActive = false;
		numberWithdrawals = 0;
		balance = 0.0;
		numberDeposits = 0;
	}

	// 3 arg constructor
	SavingAccount(String customerName, String month, double balance)
	{
		// setting customer name, month and balance to super class
		super(customerName, "Saving", month);

		// setting balance
		this.balance = balance; 
		// setting account number
		accountNumber = generateAccountNumber();
		// setting active or inactive for account 
		accountActive = isAccountActive();

	}

	// getters 
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


	/**
	 * \fn:		isAccountActive
	 * \brief:	Sets the account to active or inactive depending on the balance
	 * \param:	nothing
	 * \return:	boolean
	 */
	public boolean isAccountActive()
	{
		// if balance is less than 25, the account is invalid
		if(balance < 25)
		{
			accountActive = false;
		}
		else // if they have more than 25, the account remains active
		{
			accountActive = true;
		}

		return accountActive;
	}

	/**
	 * \fn:		generateAccountNumber
	 * \brief:	creates an account number
	 * \param:	nothing
	 * \return:	String of the account number
	 */
	public String generateAccountNumber()
	{
		int range = 6; // creating a size that will hold the 6 random digits
		String card = "002-623490-";

		// generating the 6 random numbers for the card
		for (int i = 0 ; i < range; i++)
		{
			int rand = (int)(Math.random()*10);
			card += rand;
		}
		// adds the transit number
		card += "-575";

		return card;
	}


	/**
	 * \fn:		deposit
	 * \brief:	used to deposit to savings	
	 * \param:	double, int
	 * \return:	nothing
	 */
	public void deposit (double depositAmount, int day)
	{
		// adds money to balance
		balance += depositAmount;

		// increment deposit number
		numberDeposits ++;

		// checks if they have over 25 dollars or not
		accountActive = isAccountActive();

		// record transaction
		recordTransaction(day,"deposit",depositAmount,balance);
	}

	/**
	 * \fn:		withdrawal
	 * \brief:	used to withdraw from savings
	 * \param:	double, int
	 * \return:	nothing
	 */
	public void withdrawal(double withdrawalAmount, int day)
	{
		// local variable
		String transactionMessage = "";

		// subtracting amount
		balance -= withdrawalAmount;
		// check statements for the condition of withdrawl
		if (balance > 0 && accountActive == true)
		{
			transactionMessage = "Withdrawal";
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
			transactionMessage = "Transaction Cancelled. Account is inactive";
			// to make it so their balance is not negative since transaction is canacelled
			balance += withdrawalAmount;
		}


		// calling methods to see if the acount is active and add the infomation
		accountActive = isAccountActive();
		recordTransaction(day, transactionMessage, withdrawalAmount, balance);
	}

	/**
	 * \fn:		calcInterest
	 * \brief:	calculates the interest
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
		int count = 0;
		int size = record.size();
		
		// if balance is greater than or equal to 1000, add the interest and the transaction
		if (balance >= 25)
		{
			// count the total amount of times where balance is >= 2000 to see if they can get the bonus
			for(int i = 0; i < size; i ++)
			{
				if (record.get(i).getBalance() >= 2000)
				{
					count++;
					
				}
			}
			
			// if they had 2000 they get extra interest
			if (count == size)
			{
				interest = (balance*((INT_RATE+0.0075)/12.0));
				balance += interest;
			}
			else // adding normally
			{
				interest = (balance*(INT_RATE/12.0));
				balance += interest;
			}
		}


		// recording transaction
		recordTransaction(day, "Interest", interest, balance);
	}

	/**
	 * \fn:		recordTransaction
	 * \brief:	Records every transaction made by user
	 * \param:	int, string, double, double
	 * \return:	recordTransactions
	 */
	public void recordTransaction (int day, String transaction, double amount, double balance)
	{
		// making a new object to set the data and get the month
		Transaction trans = new Transaction();
		String month = getMonth();
		// setting the infomation
		trans.set(month, day, transaction, amount, balance);

		// recording data to arrayList
		record.add(trans);
	}

	/**
	 * \fn:		printTransactions
	 * \brief:	prints the users transactions
	 * \param:	nothing
	 * \return:	nothing
	 */
	public void printTransactions()
	{
		// printing title
		System.out.println("**********************************************");
		System.out.println("Transaction Record for the Month of " +getMonth());
		System.out.println("**********************************************");
		// printing out record details using the toString method
		// using the replace function to get rid of the , [ ]
		System.out.println(" "+ record.toString().replace(",", "").replace("[", "").replace("]", ""));
	}

	/**
	 * \fn:		monthlyProcess
	 * \brief:	the users monthly process information
	 * \param:	nothing
	 * \return:	nothing
	 */
	public void monthlyProcess()
	{
		// calling calc interest method
		calcInterest();

		// printing transactions
		printTransactions();

		// checking if the account is still active
		accountActive = isAccountActive();
	}

	/**
	 * \fn:		toString
	 * \brief:	overriding the toString method for another use
	 * \param:	nothing
	 * \return:	String
	 */
	public String toString()
	{
		// formatting stuff to print out
		String bal = String.format("%.2f", balance);
		String interest = String.format("%.2f", (INT_RATE*100));

		return ("Account Number:\t\t\t" + accountNumber + "\nNumber of Withdrawals:\t\t" + numberWithdrawals +
				"\nNumber of Deposits:\t\t" + numberDeposits + "\nBalance:\t\t\t$" + bal + "\nAccount Active:\t\t\t" +
				accountActive + "\nAnnual Interest Rate:\t\t" + interest + "%\n");
	}


}
