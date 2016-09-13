package test.library.daos;

import static org.junit.Assert.*;

import java.util.Date;

import library.daos.LoanHelper;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Kishantha
 * This class will implement test cases for LoanHelper 
 *
 */
public class LoanHelperTest {



	@Test
	public void testMakeLoan() {
		ILoanHelper loanHelper = new LoanHelper();
		IBook book = Mockito.mock(IBook.class);
		IMember borrower = Mockito.mock(IMember.class);
		Date borrowDate = new Date();
		Date dueDate = new Date();
		ILoan loan = loanHelper.makeLoan(book, borrower,borrowDate , dueDate);
		
		assertEquals(book,loan.getBook());
		assertEquals(borrower,loan.getBorrower());


	}


}
