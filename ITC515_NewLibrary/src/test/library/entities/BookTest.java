/**
 * 
 */
package test.library.entities;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Kishantha
 *
 */
public class BookTest extends TestCase{
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

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
	public void testValidValues(){
		
		final String AUTHOR = "Dan Brown"; 
		final String TITLE = "Angels and Deamons"; 
		final String CALL_NUMBER = "333232"; 
		final int BOOK_ID = 1;
		
		IBook book = new Book(AUTHOR,TITLE,CALL_NUMBER,BOOK_ID);
		
		assertEquals(book.getAuthor(), AUTHOR);
		assertEquals(book.getTitle(), TITLE);
		assertEquals(book.getCallNumber(), CALL_NUMBER);
		assertEquals(book.getID(), BOOK_ID);
	}
	

}
