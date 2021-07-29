/*
Name:		Vincent Li
Date:		Feb 4, 2020
Purpose:	A bank account class that stores the users account, name, card data, etc..
			The super class that can be used for a savings and chequing account
*/




public abstract class BankAccount
{
	//Data members
	private String customerName;
	private String accountType;
	private String month;
	
	// no arg
	BankAccount()
	{
		customerName = null;
		accountType = null;
		month = null;
	}
	
	// three-arg constructor
	BankAccount(String customerName, String accountType, String month)
	{
		this.customerName = customerName;
		this.accountType = accountType;
		this.month = month;
	}

	// Creating getter and setter methods
	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}
	
	// utility methods
	/**
	* \fn:		generateAccountNumber
	* \brief:	sets the user account number
	* \param:	none
	* \return:	String
	*/
	public abstract String generateAccountNumber();
	
	/**
	* \fn:		deposit
	* \brief:	accounts for how much money was deposited into the account and when it occured
	* \param:	double, int
	* \return:	void
	*/
	public abstract void deposit(double depositAmount, int day);
	
	/**
	* \fn:		withdrawal
	* \brief:	accounts for how much money is withdrawed and when it was done
	* \param:	double, int
	* \return:	void
	*/
	public abstract void withdrawal(double withdrawalAmount, int day);
	
	/**
	* \fn:		recordTransaction
	* \brief:	Takes the tranaction infomation and saves it onto the user account
	* \param:	int, string, double, double
	* \return:	void
	*/
	public abstract void recordTransaction(int day, String transaction, double amount, double balance );

	/**
	* \fn:		monthlyProcess
	* \brief:	This is used to get the monthly transactions of the users account
	* \param:	nothing
	* \return:	void
	*/
	public abstract void monthlyProcess();
	
	
	/**
	* \fn:		toString
	* \brief:	Overriding the toString method to print out the customer detail
	* \param:	none
	* \return:	String
	*/
	public String toString()
	{
		return ("Customer:\t" + this.customerName + "\nAccount Type:\t" + this.accountType + "\nCurrent Month:\t" + this.month);
	}
}
