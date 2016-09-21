package test.library.daos;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import library.daos.BookHelper;
import library.daos.LoanHelper;
import library.interfaces.daos.IBookHelper;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Prabath
 *
 */
public class BookHelperTest {

	private final static String AUTHOR = "Jane Austen";
	private final static String TITLE = "Pride and Prejudice";
	private final static String CALL_NUMBER = "003232323"; 
	private final static int ID = 1;

	@Test
	public void testMakeLoan() {
		IBookHelper bookHealper = new BookHelper();
		IBook book = bookHealper.makeBook(AUTHOR, TITLE, CALL_NUMBER, ID);
		
		assertEquals(AUTHOR,book.getAuthor());
		assertEquals(CALL_NUMBER,book.getCallNumber());
		assertEquals(ID,book.getID());
		assertEquals(TITLE,book.getTitle());
		assertEquals(EBookState.AVAILABLE,book.getState());
		assertEquals(null,book.getLoan());
		

	}
	
}
