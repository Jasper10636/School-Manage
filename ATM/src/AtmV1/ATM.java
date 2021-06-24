package AtmV1;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//init Scanner
		Scanner in = new Scanner(System.in);
		
		//init Bank
		Bank theBank = new Bank("Bank of What");
		
		//add a User, which also creates a savings account
		User aUser = theBank.addUser("John", "Doe", "1234");
		
		//Add a checking accounts for our user
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		//Login
		
		User curUser;
		while(true) {
			
			//Stay in the login prompt until successful login
			curUser = ATM.mainMenuPrompt(theBank, in);
			
			//Stay in main menu
			ATM.printUserMenu(curUser,in);
			
		}
	}
	
	public static User mainMenuPrompt(Bank theBank, Scanner in) {
		
		String UserID;
		String pin;
		User authUser;
		
		
		//prompt the user for user ID/pin combo until a 
		//correct one is reached
		do {
			
			System.out.println("\n\nWelcome to " + theBank.getName()+"\n\n");
			System.out.println("Enter user ID: ");
			UserID = in.nextLine();
			System.out.println("Enter pin: ");
			pin = in.nextLine();
			
			authUser =  theBank.userLogin(UserID, pin);
			if(authUser == null) {
				System.out.println("Incorrect user ID/pin combination. " +
			 "\n Please try again");
			}
		}while(authUser == null); //continue looping until successful login
		
		 return authUser;
	}
	
	public static void printUserMenu(User theUser, Scanner in) {
		
		//print a summary of the user's accounts
		theUser.printAccountSummary();
		
		//init
		int choice;
		
		do {
			System.out.println("\nWelcome " + theUser.getFirstName() +", what would "
					+ "you want to do?\n");
			System.out.println("  1) Show the account transaction history");
			System.out.println("  2) withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit");
			System.out.println("\n Enter the choice:");
			choice = in.nextInt();
			
			if(choice < 1 || choice > 5) {
				System.out.println("Invalid Choice. Please choose 1 - 5 ");
			}
		}while(choice < 1 || choice > 5);
		
		switch(choice) {
		
		case 1:
			ATM.showTransHistory(theUser, in);
			break;
		case 2:
			ATM.withdrawFunds(theUser,in);
			break;
		case 3:
			ATM.depositFunds(theUser,in);
			break;
		case 4:
			ATM.transferFunds(theUser,in);
			break;
		case 5:
			in.nextLine();
			break;
		}
		 
		
		//redisplay this menu unless the user wants to quits
		if(choice != 5) {
			ATM.printUserMenu(theUser, in);
			
		}
	}
	
	public static void showTransHistory(User theUser, Scanner in) {
		int theAcct;
		
		do {
			System.out.println("Enter the number ( 1 - " + theUser.numAccounts() +" ) of the account\n"
					+ "whose transactions you want to see: ");
			
			theAcct = in.nextInt()-1;
			
			if(theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again! ");
			}
		}while(theAcct < 0 || theAcct >= theUser.numAccounts());
		
		theUser.printAcctTransHistory(theAcct);
	}
	
	
	/**
	 * 
	 * @param theUser
	 * @param in
	 */
	public static void withdrawFunds(User theUser, Scanner in) {
		
		//inits
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		//get the account to transfer from 
		do {
			System.out.println("Enter the number ( 1 - " + theUser.numAccounts() +" ) of the account\n"
					+ "to withdraw from: ");
			
			fromAcct = in.nextInt()-1;
			if(fromAcct <0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again! ");
			}
			
		}while(fromAcct <0 || fromAcct >= theUser.numAccounts());
		
		acctBal = theUser.getAcctBalance(fromAcct);
		
		do {
			System.out.println("Enter the amount to withdraw : \n" 
						+ "Account Balance: " + acctBal);
			amount = in.nextDouble();
			
			if(amount < 0) {
				System.out.println("Amount must be greater than zero.\n");	
			}else if(amount >acctBal) {
				System.out.println("Amount number must not be greater than \n"+
							" balance of the "+ acctBal + " .\n");
			}
		}while(amount < 0 || amount > acctBal);
		
		//gobble up rest of previous input 
		in.nextLine();
		
		//get a memo
		System.out.println("Enter the memo :");
		memo = in.nextLine();
		
		// do the withdraw
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
	}
	
	/**
	 * 
	 * @param theUser
	 * @param in
	 */
	public static void depositFunds(User theUser, Scanner in) {
		
		//inits
		int toAcct;
		double amount;
		double acctBal;
		String memo;
		
		//get the account to transfer from 
		do {
			System.out.println("Enter the number ( 1 - " + theUser.numAccounts() +" ) of the account\n"
					+ "to deposit to: ");
			
			toAcct = in.nextInt()-1;
			if(toAcct <0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again! ");
			}
			
		}while(toAcct <0 || toAcct >= theUser.numAccounts());
		
		acctBal = theUser.getAcctBalance(toAcct);
		
		do {
			System.out.println("Enter the amount to deposit : \n" 
						+ "Account Balance: " + acctBal );
			amount = in.nextDouble();
			
			if(amount < 0) {
				System.out.println("Amount must be greater than zero.\n");	
			}
			
		}while(amount < 0);
		
		//gobble up rest of previous input 
		in.nextLine();
		
		//get a memo
		System.out.println("Enter the memo :");
		memo = in.nextLine();
		
		// do the withdraw
		theUser.addAcctTransaction(toAcct, amount, memo);
	}
	
	/**
	 * 
	 * @param theUser
	 * @param in
	 */
	public static void transferFunds(User theUser, Scanner in) {
		
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		do {
			System.out.println("Enter the number ( 1 - " + theUser.numAccounts() +" ) of the account\n"
					+ "to transfer from: ");
			
			fromAcct = in.nextInt()-1;
			if(fromAcct <0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again! ");
			}
			
		}while(fromAcct <0 || fromAcct >= theUser.numAccounts());
		
		acctBal = theUser.getAcctBalance(fromAcct);
		
		//get the account to transfer to
		do {
			System.out.println("Enter the number ( 1 - " + theUser.numAccounts() +" ) of the account\n"
					+ "to transfer from: ");
			
			toAcct = in.nextInt()-1;
			
			if(toAcct <0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again! ");
			}
			
		}while(toAcct <0 || toAcct >= theUser.numAccounts());
		
		//get the amount to transfer
		do {
			System.out.println("Enter the amount to transfer (max " + acctBal +" )\n");
			amount = in.nextDouble();
			
			if(amount < 0) {
				System.out.println("Amount must be greater than zero.\n");	
			}else if(amount >acctBal) {
				System.out.println("Amount number must not be greater than \n"+
							" balance of the "+ acctBal + " .\n");
			}
		}while(amount < 0 || amount > acctBal);
		
		//finally, do the transfer
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", 
				theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", 
				theUser.getAcctUUID(fromAcct)));
		
	}

}
