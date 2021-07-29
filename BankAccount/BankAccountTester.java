/**
 * Program:			BankAccountTester.java
 * Date:			Feb 9, 2020 
 * Author:			Vincent Li 
 * Description:		The bank account testing program for savings and chequing accounts
 */



public class BankAccountTester
{

	public static void main(String[] args)
	{
		// initialzing multi arg constructor
		System.out.println("Chequing Account:\n");
		BankAccount chequingAcc = new PersonalChequingAccount("Janice Joplin", "March", 2345.0);
		System.out.println(chequingAcc.toString()); // printing out the inital chequingAcc info

		// adding to data to account
		System.out.println("Now adding data to chequing account");
		chequingAcc.deposit(25.98, 5);
		chequingAcc.withdrawal(1300.0, 6);
		chequingAcc.withdrawal(1700.0, 10);
		chequingAcc.deposit(1050.0, 11);
		chequingAcc.withdrawal(1700.0, 11);
		chequingAcc.deposit(25.98, 25);
		chequingAcc.withdrawal(400.0, 26);
		chequingAcc.withdrawal(27.0, 28);
		chequingAcc.withdrawal(7.50, 28);

		// prints monthlyProcess
		System.out.println("Now printing Monthly Transaction");
		// printing out transactions of the month
		chequingAcc.monthlyProcess();

		// checking the account
		System.out.println("\nNow checking toString() method again\n");
		System.out.println(chequingAcc.toString());


		// initializing multi arg constructor for savings
		System.out.println("\nSavings Account:\n");
		BankAccount saving = new SavingAccount("Elvis Presly", "March", 6100);
		System.out.println(saving.toString()); // printing savings info

		// adds data to savings
		System.out.println("Now adding data to savings account");
		saving.deposit(500.0, 3);
		saving.withdrawal(1000.0, 6);
		saving.deposit(250.0, 15);
		saving.withdrawal(3000.0, 21);
		saving.withdrawal(825.0, 28);
		saving.deposit(250.0, 29);

		// prints the method monthlyProcess
		System.out.println("Now printing Monthly Transaction:");
		saving.monthlyProcess();

		// checking the account
		System.out.println("\nNow checking toString() method again\n");
		System.out.println(saving.toString());
	}

}
