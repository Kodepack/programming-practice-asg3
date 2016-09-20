package test.library.entities;

import junit.framework.TestCase;


import library.interfaces.entities.ELoanState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.entities.Loan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Test;

import org.mockito.Mockito;


public class LoanTest extends TestCase{

	private static final int ID=25;
	private static final Date BORROWDATE= dateString("21-01-2016");
	private static final Date DUEDATE=dateString("22-01-2016");
	private ELoanState state;
	
	
	
	
	private static Date dateString(String dateInString){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		Date date=null;
		try {
			
			date = sdf.parse(dateInString);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}
	
	
}
