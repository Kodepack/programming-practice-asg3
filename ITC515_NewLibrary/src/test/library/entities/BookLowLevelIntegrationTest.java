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
	
	@Test
	public void testBorrowBookMoreThanOnce(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		IMember[] member = new IMember[2];
		
		member[0] = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		member[1] = memberDAO.addMember("fName1", "lName1", "0002", "email1");
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");

		
		try{
			for (int i=0; i<2; i++) {
				ILoan loan = loanDAO.createLoan(member[i], book);
				loanDAO.commitLoan(loan);
			}
		
			fail("Cannot borrow same book more than once");
		}catch(RuntimeException ignoredException){
			//ignore as this is the default behaviour
		}
		
		assertEquals(EBookState.ON_LOAN,book.getState());

		
	}
	

	
	@Test
	public void testGetLoan(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		//Add a book
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		//Add a member
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		//There should be no loans for a new book.
		assertNull(book.getLoan());
		//Borrow the book
		ILoan loan = loanDAO.createLoan(member, book);
		
		loanDAO.commitLoan(loan);
		
		//getLoan should be not null for a borrowed book
		assertNotNull(book.getLoan());

	}
	

}
