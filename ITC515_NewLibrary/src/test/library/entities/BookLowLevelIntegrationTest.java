package test.library.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.EMemberState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Test;


/**
 * 
 * @author Kishantha
 *
 */

public class BookLowLevelIntegrationTest {

	
	//@Test
	public void testBorrowBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		
		assertEquals(member, loan.getBorrower());
		assertEquals(book, loan.getBook());
		assertEquals(1,member.getLoans().size());
		assertEquals(loan,member.getLoans().get(0));
		assertEquals(loan,book.getLoan());
		assertEquals(EMemberState.BORROWING_ALLOWED,member.getState());
		assertEquals(EBookState.ON_LOAN,book.getState());
		
	}
	

	
	
}
