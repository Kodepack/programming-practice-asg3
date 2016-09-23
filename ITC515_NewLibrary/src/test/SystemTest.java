package test;



import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;
import library.*;
import library.daos.*;
import library.interfaces.EBorrowState;



@RunWith(MockitoJUnitRunner.class)
public class SystemTest {
    
    @Spy
    private IBookDAO bookDAO_ = new BookMapDAO(new BookHelper());
    @Spy
    private IMemberDAO memberDAO_ = new MemberMapDAO(new MemberHelper());
    @Spy
    private ILoanDAO loanDAO_ = new LoanMapDAO(new LoanHelper());
    
    @Mock
    private ICardReader reader_;
    @Mock
    private IScanner scanner_; 
    @Mock
    private IPrinter printer_; 
    @Mock
    private IDisplay display_;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private BorrowUC_UI ui_;
    
    @InjectMocks
    private BorrowUC_CTL sut_;
    

    @Before
    public void setUp() throws Exception {
        setUpTestData();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test initializations
     */
    @Test
    public void testInit() {
        sut_.initialise();
        
        verify(reader_).setEnabled(true);
        verify(scanner_).setEnabled(false);
        verify(ui_).setState(any(EBorrowState.class));
    }

    /**
     * Test card swipe and book scan for borrower with no restrictions
     */
    @Test
    public void testCardSwipedAndBookScanForNoRestrictions() {
        //arrange
        sut_.initialise();
        reset(reader_);
        reset(scanner_);
        reset(ui_);
        
        //select a member with no restrictions, no existing loans
        int memberId = 1;
        
        //execute
        sut_.cardSwiped(memberId);

        //asserts and verifies
        assertEquals(EBorrowState.SCANNING_BOOKS,sut_.getState());
        verify(reader_).setEnabled(false);
        verify(scanner_).setEnabled(true);
        verify(ui_).setState(EBorrowState.SCANNING_BOOKS);
        verify(ui_, atLeastOnce()).displayScannedBookDetails(any(String.class));           
        verify(ui_).displayPendingLoan(""); 
        verify(ui_).displayMemberDetails(eq(memberId), anyString(), anyString());  
        verify(ui_).displayExistingLoan(anyString());
        assertTrue(sut_.getScanCount() == 0);
 
        sut_.bookScanned(12);
        
        verify(ui_, atLeastOnce()).displayScannedBookDetails(anyString());
        verify(ui_, atLeastOnce()).displayPendingLoan(anyString());
        assertTrue(sut_.getScanCount() == 1);
    }
    
    
    /**
     * Test swipe card and book scan for borrower with fines payable but not over limit
     */
    
    @Test
    public void testCardSwipedAndBookScanForFinesPayableNotOverLimit() {
        //arrange
        sut_.initialise();
        reset(reader_);
        reset(scanner_);
        reset(ui_);
        
		//Check member 5 who has fines payable but not over limit
        int memberId = 5;
        
        //execute
        sut_.cardSwiped(memberId);

        //asserts and verifies
        assertEquals(EBorrowState.SCANNING_BOOKS,sut_.getState());
        verify(reader_).setEnabled(false);
        verify(scanner_).setEnabled(true);
        verify(ui_).setState(EBorrowState.SCANNING_BOOKS);
        verify(ui_, atLeastOnce()).displayScannedBookDetails(any(String.class));           
        verify(ui_).displayPendingLoan(""); 
        verify(ui_).displayMemberDetails(eq(memberId), anyString(), anyString());  
        verify(ui_).displayExistingLoan(anyString());
        assertTrue(sut_.getScanCount() == 0);
 
        sut_.bookScanned(12);
        
        verify(ui_, atLeastOnce()).displayScannedBookDetails(anyString());
        verify(ui_, atLeastOnce()).displayPendingLoan(anyString());
        assertTrue(sut_.getScanCount() == 1);
    }

    
    /**
     * Test swipe card and book scan for borrower with fines reached limit
     */
    @Test
    public void testCardSwipedAndBookScanForFinesPayableAndReachedLimit() {
        //arrange
        sut_.initialise();
        reset(reader_);
        reset(scanner_);
        reset(ui_);
        
		//Check member 3 who has maxed out fines
        int memberId = 3;
        
        //execute
        sut_.cardSwiped(memberId);

        //asserts and verifies
        assertEquals(EBorrowState.BORROWING_RESTRICTED,sut_.getState());
        verify(reader_).setEnabled(false);
        verify(scanner_).setEnabled(false);
        verify(ui_).setState(EBorrowState.BORROWING_RESTRICTED);
        verify(ui_, atLeastOnce()).displayScannedBookDetails(any(String.class));           
        verify(ui_).displayPendingLoan(""); 
        verify(ui_).displayMemberDetails(eq(memberId), anyString(), anyString());  
        verify(ui_).displayExistingLoan(anyString());
        assertTrue(sut_.getScanCount() == 0);
 
        try{
        	sut_.bookScanned(12);
        	fail("Cannot scan books for restricted borrower");
        }catch(RuntimeException e){
        	//Ignore the invalid operation error
        }

    }
    
   private void setUpTestData() {
        IBook[] book = new IBook[15];
        IMember[] member = new IMember[6];
        
        book[0]  = bookDAO_.addBook("author1", "title1", "callNo1");
        book[1]  = bookDAO_.addBook("author1", "title2", "callNo2");
        book[2]  = bookDAO_.addBook("author1", "title3", "callNo3");
        book[3]  = bookDAO_.addBook("author1", "title4", "callNo4");
        book[4]  = bookDAO_.addBook("author2", "title5", "callNo5");
        book[5]  = bookDAO_.addBook("author2", "title6", "callNo6");
        book[6]  = bookDAO_.addBook("author2", "title7", "callNo7");
        book[7]  = bookDAO_.addBook("author2", "title8", "callNo8");
        book[8]  = bookDAO_.addBook("author3", "title9", "callNo9");
        book[9]  = bookDAO_.addBook("author3", "title10", "callNo10");
        book[10] = bookDAO_.addBook("author4", "title11", "callNo11");
        book[11] = bookDAO_.addBook("author4", "title12", "callNo12");
        book[12] = bookDAO_.addBook("author5", "title13", "callNo13");
        book[13] = bookDAO_.addBook("author5", "title14", "callNo14");
        book[14] = bookDAO_.addBook("author5", "title15", "callNo15");
        
        member[0] = memberDAO_.addMember("fName0", "lName0", "0001", "email0");
        member[1] = memberDAO_.addMember("fName1", "lName1", "0002", "email1");
        member[2] = memberDAO_.addMember("fName2", "lName2", "0003", "email2");
        member[3] = memberDAO_.addMember("fName3", "lName3", "0004", "email3");
        member[4] = memberDAO_.addMember("fName4", "lName4", "0005", "email4");
        member[5] = memberDAO_.addMember("fName5", "lName5", "0006", "email5");
        
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
                
        //create a member with overdue loans        
        for (int i=0; i<2; i++) {
            ILoan loan = loanDAO_.createLoan(member[1], book[i]);
            loanDAO_.commitLoan(loan);
        }
        cal.setTime(now);
        cal.add(Calendar.DATE, ILoan.LOAN_PERIOD + 1);
        Date checkDate = cal.getTime();     
        loanDAO_.updateOverDueStatus(checkDate);
        
        //create a member with maxed out unpaid fines
        member[2].addFine(10.0f);
        
        //create a member with maxed out loans
        for (int i=2; i<7; i++) {
            ILoan loan = loanDAO_.createLoan(member[3], book[i]);
            loanDAO_.commitLoan(loan);
        }
        
        //a member with a fine, but not over the limit
        member[4].addFine(5.0f);
        
        //a member with a couple of loans but not over the limit
        for (int i=7; i<9; i++) {
            ILoan loan = loanDAO_.createLoan(member[5], book[i]);
            loanDAO_.commitLoan(loan);
        }
    }

}