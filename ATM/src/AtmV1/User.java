package AtmV1;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	// The first name of the user
	private String firstName;
	
	
	//The last name of the user
	private String lastName;
	
	
	//The ID number of the user
	private String uuid;
	
	
	//The MD5 hash of the user's pin number, Store user pin password
	private byte pinHash[];
	
	
	//The list of the accounts for this user
	private ArrayList<Account> accounts;
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @param theBank
	 */
	public User(String firstName, String lastName, String pin, Bank theBank) {
		
		// Set user's name
		this.firstName = firstName;
		this.lastName = lastName; 
		
		// Store the pin's MD5 hash, rather than the original value,
		// for security reasons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println("error, caugth NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get a new, unique universal ID for the user
		this.uuid = theBank.getNewUserUUID();
		
		
		//create empty list of accounts
		this.accounts = new ArrayList<Account>();
		
		
		//print log message
		System.out.println("New user " + lastName +", " + firstName + " with ID " + this.uuid + 
				" created.\n ");	
		
	}
	/**
	 * 
	 * @param anAcct
	 */
	public void addAccount(Account anAcct){
		this.accounts.add(anAcct);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUUID() {
		return this.uuid;
	}
	
	
	/**
	 * 
	 * @param aPin
	 * @return
	 */
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), 
					this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caugth NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	public void printAccountSummary() {
		System.out.println("\n\n" + this.firstName+"'s accounts summary");
		for(int a = 0; a< this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1,this.accounts.get(a).getSummaryLine());
		}
	}
	
	public int numAccounts() {
		return this.accounts.size();
	}
	
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}
	
	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account
	 * @param acctIdx the index of the account to use
	 * @return      the UUID of the account
	 */
	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
	 
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
}
