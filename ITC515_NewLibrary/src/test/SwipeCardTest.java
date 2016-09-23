package test;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Before;
import org.junit.Test;

public class SwipeCardTest {

	private IBookDAO bookDAO;
	private ILoanDAO loanDAO;
	private IMemberDAO memberDAO;
	//Add the test data
    IBook[] book = new IBook[15];
	IMember[] member = new IMember[6];
	
	@Before
	public void init(){

			
			//Initialize the concrete classes
			bookDAO = new BookMapDAO(new BookHelper());
			loanDAO = new LoanMapDAO(new LoanHelper());
			memberDAO = new MemberMapDAO(new MemberHelper());


			
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
			
			member[0] = memberDAO.addMember("fName0", "lName0", "0001", "email0");
			member[1] = memberDAO.addMember("fName1", "lName1", "0002", "email1");
			member[2] = memberDAO.addMember("fName2", "lName2", "0003", "email2");
			member[3] = memberDAO.addMember("fName3", "lName3", "0004", "email3");
			member[4] = memberDAO.addMember("fName4", "lName4", "0005", "email4");
			member[5] = memberDAO.addMember("fName5", "lName5", "0006", "email5");
			
			Calendar cal = Calendar.getInstance();
			Date now = cal.getTime();
					
			//create a member with overdue loans		
			for (int i=0; i<2; i++) {
				ILoan loan = loanDAO.createLoan(member[1], book[i]);
				loanDAO.commitLoan(loan);
			}
			cal.setTime(now);
			cal.add(Calendar.DATE, ILoan.LOAN_PERIOD + 1);
			Date checkDate = cal.getTime();		
			loanDAO.updateOverDueStatus(checkDate);
			
			//create a member with maxed out unpaid fines
			member[2].addFine(10.0f);
			
			//create a member with maxed out loans
			for (int i=2; i<7; i++) {
				ILoan loan = loanDAO.createLoan(member[3], book[i]);
				loanDAO.commitLoan(loan);
			}
			
			//a member with a fine, but not over the limit
			member[4].addFine(5.0f);
			
			//a member with a couple of loans but not over the limit
			for (int i=7; i<9; i++) {
				ILoan loan = loanDAO.createLoan(member[5], book[i]);
				loanDAO.commitLoan(loan);
			}

	}
	
	@Test
	public void testSwipeCard(){
		for(int i = 0; i < member.length; i++){
			cardSwiped(i);
		}
	}

	
	@Test
	public void testSwipeCardMemberWithOverdueLoan(){
		//Checking member id 2 - with overdue
		IMember borrower = cardSwiped(2);
		
		assertTrue("This member should have overdue loans",borrower.hasOverDueLoans());
		assertTrue("Member should not have reached loan limits",!borrower.hasReachedLoanLimit());
		assertTrue("Member should not have fines payable",!borrower.hasFinesPayable());
		assertTrue("Member should not have fines limit",!borrower.hasReachedFineLimit());
	}

	
	@Test
	public void testSwipeCardMemberReachedLoanLimit(){
		//Check member 4 who has maxed out loan limits
		IMember borrower = cardSwiped(4);
		
		assertTrue("This member should have overdue loans",!borrower.hasOverDueLoans());
		assertTrue("Member should not have reached loan limits",borrower.hasReachedLoanLimit());
		assertTrue("Member should not have fines payable",!borrower.hasFinesPayable());
		assertTrue("Member should not have fines limit",!borrower.hasReachedFineLimit());
	}

	@Test
	public void testSwipeCardMemberFinesPayableAndReachedLimit(){
		//Check member 3 who has maxed out fines
		IMember borrower = cardSwiped(3);
		
		assertTrue("This member should have overdue loans",!borrower.hasOverDueLoans());
		assertTrue("Member should not have reached loan limits",!borrower.hasReachedLoanLimit());
		assertTrue("Member should have fines payable",borrower.hasFinesPayable());
		assertTrue("Member should not have fines limit",borrower.hasReachedFineLimit());
	}
	

	@Test
	public void testSwipeCardMemberFinesPayable(){
		//Check member 5 who has fines payable but not over limit
		IMember borrower = cardSwiped(5);
		
		assertTrue("This member should not have overdue loans",!borrower.hasOverDueLoans());
		assertTrue("Member should not have reached loan limits",!borrower.hasReachedLoanLimit());
		assertTrue("Member should have fines payable",borrower.hasFinesPayable());
		assertTrue("Member should not have fines limit",!borrower.hasReachedFineLimit());
	}
	
	public IMember cardSwiped(int memberID) {

		IMember borrower = memberDAO.getMemberByID(memberID);
		if (borrower == null) {
			System.out.println(String.format("Member ID %d not found", memberID));
			return null;
		}
		boolean overdue = borrower.hasOverDueLoans();
		boolean atLoanLimit = borrower.hasReachedLoanLimit();
		boolean hasFines = borrower.hasFinesPayable();
		boolean overFineLimit = borrower.hasReachedFineLimit();
		boolean borrowing_restricted = (overdue || atLoanLimit || overFineLimit);
		
		if (borrowing_restricted) {
			System.out.println("Borrowing restricted for this member");
		}
		else {
			System.out.println("Borrowing allowed for this member");
		}

		//display member details
		int mID = borrower.getID();
		String mName = borrower.getFirstName() + " " + borrower.getLastName();
		String mContact = borrower.getContactPhone();
		System.out.println(String.format("Member Details: %s, %s, %s ",mID, mName, mContact));

		
		if (overdue) {
			System.out.println("Overdue");
		}
		if (atLoanLimit) {
			System.out.println("Loan limit reached");
		}
		if (hasFines) {
			float amountOwing = borrower.getFineAmount();
			System.out.println(String.format("Amount owing %f", amountOwing));

		}
		
		if (overFineLimit) {
			float amountOwing = borrower.getFineAmount();
			System.out.println(String.format("Amount owing %f", amountOwing));
		}
		
		//display existing loans
		for (ILoan ln : borrower.getLoans()) {
			System.out.println(String.format("Existing loans %s", ln.toString()));

		}
		
		
		return borrower;

	}
	
	


}
