/**
 * 
 */
package test.library.entities;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.TestCase;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


/**
 * @author Kishantha
 *
 */

public class BookTest  {

	private static final String AUTHOR = "Dan Brown";
	private static final String TITLE = "Angels and Deamons";
	private static final String CALL_NUMBER = "333232";
	private static final int BOOK_ID = 1;


	@Test
	public void testNullValues() {

		IBook book = null;

		try {
			book = new Book(null, null, null, 0);
			fail("null values are not checked in constructor");

		} catch (IllegalArgumentException e) {
			//Exception ignored
		}

	}

	@Test
	public void testEmptyValues() {

		IBook book = null;

		try {
			book = new Book("", "", "", 0);
			fail("emplty values are not checked in constructor");
		} catch (IllegalArgumentException e) {
			//Exception ignored
		}

	}

	@Test
	public void testValidValues() {

		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);

		assertEquals(book.getAuthor(), AUTHOR);
		assertEquals(book.getTitle(), TITLE);
		assertEquals(book.getCallNumber(), CALL_NUMBER);
		assertEquals(book.getID(), BOOK_ID);
	}


	
	@Test
	public void testBorrow(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		book.borrow(getLoan());
		assertEquals(EBookState.ON_LOAN,book.getState());
	}
	
	@Test
	public void testBorrowWithNullLoan(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		try {
			book.borrow(null);
			fail("Cannot borrow for null loan");
		} catch (IllegalArgumentException e) {
			//ignore
		}
	}

	@Test
	public void testBorrowTwice(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		
		try {
			//Borrow a new book
			book.borrow(getLoan());
			//Borrow twice the same book
			book.borrow(getLoan());
			//this should fail
			fail("Cannot borrow already borrowed book");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.ON_LOAN,book.getState());

	}
	
	@Test
	public void testGetLoan(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		
		//There should be no loans for a new book.
		assertNull(book.getLoan());
		//Borrow the book
		book.borrow(getLoan());
		//getLoan should be not null for a borrowed book
		assertNotNull(book.getLoan());

	}
	

	@Test
	public void testReturnNewBook(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
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

	
	@Test
	public void testReturnBorrowedNotDamagedBookAndReborrow(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		final boolean DAMAGED = true;
		//borrow the book
		book.borrow(getLoan());
		//return the book not damaged
		book.returnBook(!DAMAGED);
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book again
		book.borrow(getLoan());
		//successfully borrowed again
		assertEquals(EBookState.ON_LOAN,book.getState());
		

	}
	

	
	@Test
	public void testReturnBorrowedDamagedBookAndReborrow(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		final boolean DAMAGED = true;

		//borrow the book
		book.borrow(getLoan());
		//return the book damaged
		book.returnBook(DAMAGED);
		
		try {
			//borrow the book again
			book.borrow(getLoan());
			//this should fail
			fail("cannot borrow a damaged book");
		} catch (RuntimeException e) {
			//ignore
		}
		assertEquals(EBookState.DAMAGED,book.getState());
	}
	
	
	
	@Test
	public void testLoseNewBook(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		final boolean DAMAGED = true;
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
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		//borrow the book
		book.borrow(getLoan());
		//lose the book
		book.lose();
		//this should be successful
		assertEquals(EBookState.LOST,book.getState());

	}
	
	@Test
	public void testRepairNewBook(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		final boolean DAMAGED = true;
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
		final boolean DAMAGED = true;
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		book.borrow(getLoan());
		assertEquals(EBookState.ON_LOAN,book.getState());

		book.returnBook(DAMAGED);
		assertEquals(EBookState.DAMAGED,book.getState());
		book.repair();
		assertEquals(EBookState.AVAILABLE,book.getState());
		

	}

	@Test
	public void testDisposeBorrowedBook(){
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		final boolean DAMAGED = true;
		try {
			book.borrow(getLoan());
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
		final boolean DAMAGED = true;
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		book.dispose();
		assertEquals(EBookState.DISPOSED,book.getState());

	}
	
	
	@Test
	public void testDisposeLostBook(){
		final boolean DAMAGED = true;
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		book.borrow(getLoan());
		assertEquals(EBookState.ON_LOAN,book.getState());
		book.lose();
		assertEquals(EBookState.LOST,book.getState());
		book.dispose();
		assertEquals(EBookState.DISPOSED,book.getState());
	}
	
	
	@Test
	public void testDisposeDamagedBook(){
		final boolean DAMAGED = true;
		IBook book = new Book(AUTHOR, TITLE, CALL_NUMBER, BOOK_ID);
		assertEquals(EBookState.AVAILABLE,book.getState());
		//borrow the book
		book.borrow(getLoan());
		assertEquals(EBookState.ON_LOAN,book.getState());
		book.returnBook(DAMAGED);
		assertEquals(EBookState.DAMAGED,book.getState());
		book.dispose();
		assertEquals(EBookState.DISPOSED,book.getState());
	}	
	
	
	private ILoan getLoan() {

		return Mockito.mock(ILoan.class);
	}
}
