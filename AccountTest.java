package AccountExample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 
 * test for task: http://kata-log.rocks/banking-kata
 * 
 * @author Margrit Atalla
 *
 */

public class AccountTest extends TestCase {

	Account myAccount1;
	Account myAccount2;
	Account myAccount3;
	String currentDate;
	String header;

	@Override public void setUp() throws Exception
	{
		myAccount1 = new Account();
		myAccount2 = new Account();
		myAccount3 = new Account();
		header = "Date        Amount     Balance\n";
		currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy  "));
		assertEquals ( "only header present for balance change operation table",
				header, myAccount1.printStatement());

	}

	@Override public void tearDown() throws Exception
	{
		myAccount1=null;
		myAccount2=null;
		myAccount3=null;
	}


	@Test
	public void testDeposit() {
		try {
			myAccount1.deposit(5678);
			assertEquals ("header and one row with amount 5678 and balance 5678", 
					header+currentDate+"+5678      5678",myAccount1.printStatement());
		}
		catch(Exception e) {fail("exception in testing deposit");}
	}

	@Test
	public void testWithdraw() {
		try {
			myAccount2.withdraw(123456);
			assertEquals ("header and one row with amount -123456 and balance -123456", 
					header+currentDate+"-123456    -123456",myAccount2.printStatement());
		}
		catch(Exception e) {fail("exception in testing withdraw");}
	}

	@Test
	public void testPrintStatement() {

		try {
			myAccount3.deposit(55000);

			// wait a little bit
			Thread.sleep(1000);

			myAccount3.withdraw(5000);

			// wait a little bit
			Thread.sleep(1000);

			myAccount3.deposit(10333);

			assertEquals ("header and 3 rows",
					header+currentDate+"+55000     55000\n"
						  +currentDate+"-5000      50000\n"
						  +currentDate+"+10333     60333", myAccount3.printStatement());

		}
		catch(Exception e) {fail("exception in testing printStatement");}
	}

}
