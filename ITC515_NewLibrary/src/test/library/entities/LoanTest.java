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
	
	@Test
	// tested for null values
	public void testNullValues(){
		
		
		ILoan loan =null;
		try {

			loan = new Loan(null, null, null, null);
			fail("null values are not checked in constructor");

		} catch (IllegalArgumentException e) {
			//Exception ignored
		}
		
	}
	
	//Checks the Valid Values
	
	@Test
	public void testValidValues() {

		ILoan loan = new Loan(getBook(),getMember(),BORROWDATE, DUEDATE);
		IBook book = (IBook) loan.getBook();
		assertEquals(getBook().getID(), book.getID());
		assertEquals(getBook().getLoan(), book.getLoan());
		
	}
	
	//Test the Commit function Loaning a book
	
	public Loan testCommit(){
		
		Loan loan = new Loan(getBook(),getMember(),BORROWDATE, DUEDATE);
		setState(ELoanState.CURRENT);
		loan.commit(ID);
		assertEquals(getState(),loan.getState());
		
		return loan;
	}
	
	//Testing the Complete Function for a Loan
	public void testComplete(){
		
		
		Loan loan = new Loan(getBook(),getMember(),BORROWDATE, DUEDATE);
		setState(ELoanState.CURRENT);
		loan.commit(ID);
		assertEquals(getState(),loan.getState());
		
	} 
	
	//Testing Overdue Return
	@Test
	public void testIsOverDue(){
		
		Loan loan = new Loan(getBook(),getMember(),BORROWDATE, DUEDATE);
		assertFalse(loan.isOverDue());
	
	}
	
	@Test
	//Checking whether there's an overdue inserting a date
	public void testcheckOverDue(){
		
		Loan loan = new Loan(getBook(),getMember(),BORROWDATE, DUEDATE);
		loan.commit(ID);
		loan.checkOverDue(dateString("12-05-2017"));
		setState(ELoanState.OVERDUE);
		assertEquals(getState(),loan.getState());
	}
	
	//Check scenario where a return is on time
	public void testcheckOverDueforNotOverDue(){
		
		Loan loan = new Loan(getBook(),getMember(),BORROWDATE, DUEDATE);
		loan.commit(ID);
		loan.checkOverDue(dateString("12-05-2016"));
		setState(ELoanState.OVERDUE);
		assertNotSame(getState(),loan.getState());
		
	}
	
	//Same Details returned after Loan commit in Book
	public void testValidValuesCommitBook(){
		
		Loan loan=testCommit();
		IBook book = (IBook) loan.getBook();
		assertEquals(getBook().getID(), book.getID());
		
		
	}
	//Same Details returned after Loan commit in Member
	public void testValidValuesCommitMember(){
		
		Loan loan=testCommit();
		IMember memeber= (IMember) loan.getBorrower();
		assertEquals(getMember().getID(), memeber.getID());
		
		
	}
	
	
	
	private static IBook getBook() {

		return Mockito.mock(IBook.class);
	}

	private static IMember getMember() {

		return Mockito.mock(IMember.class);
	}
	
	public ELoanState getState() {
		return state;
	}

	public void setState(ELoanState state) {
		this.state = state;
	}

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
