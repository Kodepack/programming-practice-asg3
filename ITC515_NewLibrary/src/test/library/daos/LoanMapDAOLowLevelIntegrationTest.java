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
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

public class LoanMapDAOLowLevelIntegrationTest {

	/**
	 * Test create loan
	 */
	@Test
	public void testCreateLoan(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);

		assertEquals(book,loan.getBook());
		assertEquals(member,loan.getBorrower());

		
	}
	

	/**
	 * Test get loan by id
	 */
	@Test
	public void testGetLoanByID(){
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		ILoan iLoan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(iLoan);
		
		ILoan loan = loanDAO.getLoanByID(1);
		//Check if the two loans are equal
		assertEquals(iLoan,loan);
		
	}
	


	
}
