package test.library.daos;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 * @author Kishantha
 *
 */
public class LoanHelperLowLevelIntegrationTest {


	/**
	 * Test for make loan
	 */
	@Test
	public void testMakeLoan() {
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		ILoanHelper loanHelper = new LoanHelper();
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember borrower = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		Date borrowDate = new Date();
		Date dueDate = new Date();
		ILoan loan = loanHelper.makeLoan(book, borrower,borrowDate , dueDate);
		
		assertEquals(book,loan.getBook());
		assertEquals(borrower,loan.getBorrower());


	}

	
}
