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
	public void testBorrowerHasReachedLoanLimit(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book[] = new IBook[15];
		
		book[0]  = bookDAO.addBook("author1", "title1", "callNo1");
		book[1]  = bookDAO.addBook("author1", "title2", "callNo2");
		book[2]  = bookDAO.addBook("author1", "title3", "callNo3");
		book[3]  = bookDAO.addBook("author1", "title4", "callNo4");
		book[4]  = bookDAO.addBook("author2", "title5", "callNo5");
		book[5]  = bookDAO.addBook("author2", "title6", "callNo6");
		book[6]  = bookDAO.addBook("author2", "title7", "callNo7");
		book[7]  = bookDAO.addBook("author2", "title8", "callNo8");
		book[8]  = bookDAO.addBook("author3", "title9", "callNo9");
		book[9]  = bookDAO.addBook("author3", "title10", "callNo10");
		book[10] = bookDAO.addBook("author4", "title11", "callNo11");
		book[11] = bookDAO.addBook("author4", "title12", "callNo12");
		book[12] = bookDAO.addBook("author5", "title13", "callNo13");
		book[13] = bookDAO.addBook("author5", "title14", "callNo14");
		book[14] = bookDAO.addBook("author5", "title15", "callNo15");

		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		try{
			for (int i=0; i<6; i++) {
				ILoan loan = loanDAO.createLoan(member, book[i]);
				loanDAO.commitLoan(loan);
			}
		
			fail("Cannot borrow more than 6");
		}catch(RuntimeException ignoredException){
			
		}
		
	}


}
