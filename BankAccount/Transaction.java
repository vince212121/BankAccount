/**
 * Program:			Transaction.java
 * Date:			Feb 9, 2020 
 * Author:			Vincent Li 
 * Description:		An abstract class that will be used to store the transactions a user makes
 */



public class Transaction
{
	// data memebers
	private String month;
	private int day;
	private String transaction;
	private double balance;
	private double amount;
	
	// zero arg constructor
	Transaction()
	{
		month = null;
		day = 0;
		transaction = null;
		balance = 0;
		amount = 0;
	}
	
	// getters
	public double getAmount()
	{
		return amount;
	}

	public String getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}

	public String getTransaction()
	{
		return transaction;
	}

	public double getBalance()
	{
		return balance;
	}
	
	/**
	* \fn:		set
	* \brief:	sets the data memebers
	* \param:	the data member types
	* \return:	the transaction
	*/
	public void set (String month, int day, String transaction, double amount, double balance)
	{
		this.month = month;
		this.day = day;
		this.transaction = transaction;
		this.balance = balance;
		this.amount = amount;
	}
	
	/**
	* \fn:		toString
	* \brief:	overriding the toString class to print a message
	* \param:	nothing
	* \return:	message
	*/
	public String toString()
	{
		// making formated versions
		String bal = String.format("%20s $%-8.2f", "Balance:", balance);
		String trans = String.format("%-15s $%-8.2f", transaction,  amount);
		String date = String.format("%-10s %-10s", month, day);
		return (date + trans + bal + "\n");
	}
}
