package AccountExample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;



/**
 * 
 * solution for task: http://kata-log.rocks/banking-kata
 * 
 * @author Margrit Atalla
 *
 */

public class Account {

	/**
	 * LocalDateTime key: time stamp of deposit or withdrawal
	 * ArrayList contents:
	 * index 1: balance change amount
	 * index 2: new balance after change
	 */
	private TreeMap<LocalDateTime,ArrayList<Integer>> _balanceHistory=new TreeMap<LocalDateTime,ArrayList<Integer>>();

	/**
	 * current balance of account, initial value is 0
	 */
	private int _currentBalance=0;

	/**
	 * increases balance of account by amount
	 * and stores the balance change in the history
	 * @param amount must be greater 0, otherwise it is ignored. 
	 */
	public void deposit(int amount){
		if (amount <=0) return;

		// add entry to balance change history
		_balanceHistory.put(LocalDateTime.now(), 
				new ArrayList<Integer>(Arrays.asList(amount,_currentBalance+amount)));

		// increase balance
		_currentBalance+=amount;

	}

	/**
	 * decreases balance of account by amount
	 * and stores the balance change in the history
	 * @param amount must be greater 0, otherwise it is ignored.
	 */
	public void withdraw(int amount){
		if (amount <=0) return;

		// add entry to balance change history
		_balanceHistory.put(LocalDateTime.now(), 
				new ArrayList<Integer>(Arrays.asList(-amount,_currentBalance-amount)));

		// decrease balance
		_currentBalance-=amount;
	}


	/**
	 * fills the result with the contents of the balance history map,
	 * which is sorted by time stamp of balance change operation
	 * if the map is empty, only the header text is returned
	 * @return history table of balance changes
	 */
	public String printStatement(){

		// collect rows
		String rows=_balanceHistory.entrySet()
				.stream()
				.map(entry -> entry.getKey().format(DateTimeFormatter.ofPattern("dd.MM.yyyy  "))
						+String.format("%-+11d",entry.getValue().get(0))
						+entry.getValue().get(1))
						.collect(Collectors.joining("\n"));

		return "Date        Amount     Balance\n"+rows; 


	}

}
