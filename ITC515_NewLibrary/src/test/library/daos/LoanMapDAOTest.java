package test.library.daos;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import library.daos.LoanMapDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * 
 * @author Prabath
 *
 */
public class LoanMapDAOTest {

	
	@Test
	public void testCreateLoan(){
		
		int ID = 1;
		ILoanHelper helper = Mockito.mock(ILoanHelper.class);
		IMember borrower = Mockito.mock(IMember.class);
		IBook book = Mockito.mock(IBook.class);
		ILoan iLoan = Mockito.mock(ILoan.class);
		Date borrowDate = new Date();
		Date dueDate = new Date();
		
		ArgumentCaptor<Date> borrowDateArg = ArgumentCaptor.forClass(Date.class);
		ArgumentCaptor<Date> dueDateArg = ArgumentCaptor.forClass(Date.class);
		
		Mockito.when(helper.makeLoan(Matchers.any(IBook.class),Matchers.any(IMember.class),Matchers.any(Date.class),Matchers.any(Date.class) )).thenReturn(iLoan);
		Mockito.when(iLoan.getBook()).thenReturn(book);
		Mockito.when(iLoan.getBorrower()).thenReturn(borrower);
		Mockito.when(iLoan.getID()).thenReturn(ID);
		
		ILoanDAO loanMapDAO = new LoanMapDAO(helper);
		ILoan loan = loanMapDAO.createLoan(borrower,book);
		Mockito.verify(helper).makeLoan(Matchers.any(IBook.class),Matchers.any(IMember.class), borrowDateArg.capture(), dueDateArg.capture());
		
		assertEquals(iLoan, loan);
		assertEquals(book,loan.getBook());
		assertEquals(borrower,loan.getBorrower());
		assertEquals(ID,loan.getID());
		
	}
	
	
	@Test
	public void testCommitLoan(){
		
		ILoanHelper helper = Mockito.mock(ILoanHelper.class);
		ILoan iLoan = Mockito.mock(ILoan.class);
		ILoanDAO loanMapDAO = new LoanMapDAO(helper);
		loanMapDAO.commitLoan(iLoan);
		Mockito.verify(iLoan).commit(Matchers.any(Integer.class));
		
	}

	@Test
	public void testGetLoanByID(){
		int id = 1;
		ILoanHelper helper = Mockito.mock(ILoanHelper.class);
		ILoan iLoan = Mockito.mock(ILoan.class);
		ILoanDAO loanMapDAO = new LoanMapDAO(helper);
		loanMapDAO.commitLoan(iLoan);
		ILoan loan = loanMapDAO.getLoanByID(id);
		//Check if the two loans are equal
		assertEquals(iLoan,loan);
		
	}
	

	@Test
	public void testGetLoanByBook(){
		int id = 1;
		ILoanHelper helper = Mockito.mock(ILoanHelper.class);
		ILoan iLoan = Mockito.mock(ILoan.class);
		ILoanDAO loanMapDAO = new LoanMapDAO(helper);
		IBook book = Mockito.mock(IBook.class);
		Mockito.when(iLoan.getBook()).thenReturn(book);

		loanMapDAO.commitLoan(iLoan);
		ILoan loan = loanMapDAO.getLoanByBook(book);
		//Check if the two loans are equal
		assertEquals(iLoan,loan);
		
	}
	
	

	
}
