package AtmV1;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	/**
	 *  Create a new Bank object with the empty lists of users and accounts
	 * @param Name the name of the Bank
	 */
	public Bank(String Name) {
		
		this.name = Name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();

	}
	
	public String getNewUserUUID() {
		
		//inits
		String uuid;
		
		Random rng = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		//continue looping until we get a unique ID
		do {
			
			//generate the number
			uuid = "";
			for(int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString(); 
			}
			
			
			//check to make sure it's unique
			for(User u : this.users) {
				if(uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNewAccountUUID() {
		
		//inits
		String uuid;
		
		Random rng = new Random();
		int len = 10;
		boolean nonUnique = false;
		
		//continue loopinh until we get a unique ID
		do {
			
			//generate the number
			uuid = "";
			for(int c = 0; c < len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString(); 
			}
			
			
			//check to make sure it's unique
			for(Account a : this.accounts) {
				if(uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
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
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */
	public User addUser(String firstName, String lastName, String pin) {
		

		//create a new user object and add it to out list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		//create a saving accounts for the user and add to User and
		// Bank accounts list
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newUser;
	}
	
	/**
	 * 
	 * @param userID
	 * @param pin
	 * @return
	 */
	public User userLogin(String userID, String pin) {
		
		//Search through list of users
		for(User u : this.users) {
			
			//check if userID is correct
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
			
		}
		//if we haven't found the user or have an incorrect pin
		return null;
		
	}
	
	public String getName() {
		return this.name;
	}
}
