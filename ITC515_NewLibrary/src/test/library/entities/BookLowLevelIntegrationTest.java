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

	
	/**
	 * Test case for borrow book
	 */
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
	
	/**
	 * Test case for borrow more than once
	 */
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
	

	/**
	 * Test case for getLoan method of book
	 */
	
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
	
	/**
	 * Test case for returning a new book
	 */

	@Test
	public void testReturnNewBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		final boolean DAMAGED = true;
		try {
			//Should not be possible to return a new book
			book.returnBook(!DAMAGED);
			fail("cannot return a new book");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.AVAILABLE,book.getState());
	}


	/**
	 * Test case for return borrowed but not damaged book and re borrow
	 */
	
	@Test
	public void testReturnBorrowedNotDamagedBookAndReborrow(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		final boolean DAMAGED = true;
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member1 = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		IMember member2 = memberDAO.addMember("fName0", "lName0", "0001", "email0");		
		
		//borrow the book by member1
		ILoan loan1 = loanDAO.createLoan(member1, book);
		loanDAO.commitLoan(loan1);
		
		//return the book not damaged
		book.returnBook(!DAMAGED);
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book again by member2
		ILoan loan2 = loanDAO.createLoan(member2, book);
		loanDAO.commitLoan(loan2);
		//successfully borrowed again
		assertEquals(EBookState.ON_LOAN,book.getState());
		

	}
	

	/**
	 * Test case for return book damaged and re borrow
	 */
	@Test
	public void testReturnBorrowedDamagedBookAndReborrow(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());

		IMember member1 = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		IMember member2 = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		final boolean DAMAGED = true;

		//borrow the book by member1
		ILoan loan1 = loanDAO.createLoan(member1, book);
		loanDAO.commitLoan(loan1);
		
		//return the book damaged
		book.returnBook(DAMAGED);
		
		try {
			//borrow the book again by member2
			ILoan loan2 = loanDAO.createLoan(member2, book);
			loanDAO.commitLoan(loan2);
			//this should fail
			fail("cannot borrow a damaged book");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.DAMAGED,book.getState());
	}
	
	
	
	@Test
	public void testLoseNewBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");

		try {
			//lose a new book
			book.lose();
			//this should fail
			fail("cannot loose a new book");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.AVAILABLE,book.getState());
	}
	
	@Test
	public void testLose(){
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		//borrow the book
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		//lose the book
		book.lose();
		//this should be successful
		assertEquals(EBookState.LOST,book.getState());

	}
	
	@Test
	public void testRepairNewBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		try {
			//lose a new book
			book.repair();
			//this should fail
			fail("cannot repair a new book");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.AVAILABLE,book.getState());
	}
	
	@Test
	public void testRepairDamagedBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		final boolean DAMAGED = true;
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		assertEquals(EBookState.ON_LOAN,book.getState());

		book.returnBook(DAMAGED);
		assertEquals(EBookState.DAMAGED,book.getState());
		book.repair();
		assertEquals(EBookState.AVAILABLE,book.getState());
		

	}

	@Test
	public void testDisposeBorrowedBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		try {
			ILoan loan = loanDAO.createLoan(member, book);
			loanDAO.commitLoan(loan);
			//lose a new book
			book.dispose();
			//this should fail
			fail("cannot dispose a borrowed");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.ON_LOAN,book.getState());
	}
	
	
	@Test
	public void testDisposeNewBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		book.dispose();
		assertEquals(EBookState.DISPOSED,book.getState());

	}
	
	
	@Test
	public void testDisposeLostBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		assertEquals(EBookState.ON_LOAN,book.getState());
		book.lose();
		assertEquals(EBookState.LOST,book.getState());
		book.dispose();
		assertEquals(EBookState.DISPOSED,book.getState());
	}
	
	
	@Test
	public void testDisposeDamagedBook(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		
		final boolean DAMAGED = true;

		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		assertEquals(EBookState.ON_LOAN,book.getState());
		book.returnBook(DAMAGED);
		assertEquals(EBookState.DAMAGED,book.getState());
		book.dispose();
		assertEquals(EBookState.DISPOSED,book.getState());
	}	
	
	
}
