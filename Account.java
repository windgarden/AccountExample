import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * 
 * solution for task: http://kata-log.rocks/banking-kata
 * 
 * compiled with java 1.8
 * 
 * @author Margrit Atalla
 *
 */

public class Account {

	/**
         *  deposit type of balance modification is ASCII value of +
	 */
	final int DEPOSIT = 43;
	
	/**
         *  withdraw type of balance modification is ASCII value of -
	 */
	final int WITHDRAW = 45;
	
	/**
	 * Long key: date of deposit or withdraw
	 * int[] values:
	 * index 0: type of change: deposit (43) , withdraw (45)
	 * index 1: balance change value
	 * index 2: new balance after change
	 */
	private TreeMap<LocalDateTime,int[]> changeHistory=new TreeMap<LocalDateTime,int[]>();
	
	/**
	 * current balance of account, initial value is 0
	 */
	private int currentBalance=0;
	
	/**
	 * increases balance of account by depositValue
	 * and stores the balance in the change history
	 * @param depositValue must be greater 0, otherwise it is ignored. 
	 * Maximum is Integer.MAX_VALUE
	 */
	public void deposit(int depositValue){
		if (depositValue <=0) return;
		
		// set key
		LocalDateTime currentTime = LocalDateTime.now();
		
		// set value
		int[] entry=new int[3];
		entry[0]=DEPOSIT;
		entry[1]=depositValue;
		entry[2]=currentBalance+depositValue;
		
		// add entry to balance change history
		changeHistory.put(currentTime, entry);
		
		// update balance
		currentBalance+=depositValue;
		
	}

	/**
	 * decreases balance of account by withdrawValue
	 * and stores the balance change in the change history
	 * @param withdrawValue must be greater 0, otherwise it is ignored.
	 * Maximum is Integer.MAX_VALUE
	 */
	public void withdraw(int withdrawValue){
               if (withdrawValue <=0) return;
		
		// set key
		LocalDateTime currentTime = LocalDateTime.now();
		
		// set value
		int[] entry=new int[3];
		entry[0]=WITHDRAW;
		entry[1]=withdrawValue;
		entry[2]=currentBalance-withdrawValue;
		
		// add entry to change history
		changeHistory.put(currentTime, entry);
		
		// update balance
		currentBalance-=withdrawValue;
	}
	
	
	/**
	 * fills the result with the contents of the changeHistory map,
	 * which is sorted by date of balance operation
	 * @return history table of balance changes
	 */
	public String printStatement(){
		
		// initialization
		String result="";	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String tableHeader="Date        Amount     Balance";		
		StringBuffer historyTable=new StringBuffer();
		
		// fill header line
		historyTable.append(tableHeader+"\n");
        
		// fill rows from changeHistory map ordered by balance change time stamp
		Iterator<Entry<LocalDateTime, int[]>> iterator = changeHistory.entrySet().iterator();
           while(iterator.hasNext()) {      	
        	Map.Entry<LocalDateTime, int[]> tableRow = iterator.next();
        	
        	// time stamp of deposit or withdrawel in date format
        	String formattedDate=((LocalDateTime)tableRow.getKey()).format(formatter);
        	historyTable.append(formattedDate+"  ");
        	
        	int[] balanceChangeValues = (int[])tableRow.getValue();
        	// '+' ( deposit ) or '-' (withdraw )
        	historyTable.append((char)balanceChangeValues[0]);
        	
        	// balance change value can have at most 10 positions due to int MAX_VALUE 
        	String balanceChange = String.format("%-10d", balanceChangeValues[1]);
        	historyTable.append(balanceChange);
        	
        	// balance value after change
        	historyTable.append(balanceChangeValues[2]+"\n");
           }
        
        result=historyTable.toString();
        return result;
	}
	
}
