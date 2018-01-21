
/**
 * tests the Account class public methods
 * 
 * @author Margrit Atalla
 *
 */
public class TestAccount {

	public static void main(String[] args) {
		
		Account myAccount = new Account();
		
		// make any balance change operations
		//
		// increase acount balance by 4000777
		myAccount.deposit(4000777);
		
		// wait a little bit
		try {
		Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		// decrease account balance by 700
		// note, that decreasing works only with positive int argument
		myAccount.withdraw(700);
		
		// wait a little bit
		try {
			Thread.sleep(1000);
			}
			catch(Exception e){
				
			}
		
		// increase account balance by 2000
		myAccount.deposit(2000);
		
		// print the balance change history in required table format
		System.out.printf(myAccount.printStatement());
	}

}
