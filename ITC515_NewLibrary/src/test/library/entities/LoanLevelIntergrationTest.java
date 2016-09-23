package test.library.entities;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.entities.Loan;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.ELoanState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class LoanLevelIntergrationTest {
	
	
	
	@Test
	public void testCommitLoanWithBorrow(){
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("authorX", "titleX", "callNoX");
		IMember member = memberDAO.addMember("fName0", "lName0", "000X", "email0");
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		
		
		System.out.println(loan.toString());
		
		
	}
	
	@Test
	
	//Testing whether Loan will overdue to book loaned at the same instance
	public void checkOverDueForCurrentDate(){
	
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("authorX", "titleX", "callNoX");
		IMember member = memberDAO.addMember("fName0", "lName0", "000X", "email0");
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		assertFalse(loan.checkOverDue(new Date()));
	
		
		
	}
	
	@Test
	//TestMemeber with OverdueLoan
	
	public void checkOverDue(){
		
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("authorX", "titleX", "callNoX");
		IMember member = memberDAO.addMember("fName0", "lName0", "000X", "email0");
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		setOverDueDate(1, loanDAO);
		assertTrue(loan.checkOverDue(new Date()));
		
	}
	
	// Make a Member with an overdue loan
	
	public void setOverDueDate(int timeNum, ILoanDAO loanDAO){
		
		
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		
		cal.setTime(now);
		cal.add(Calendar.DATE, ILoan.LOAN_PERIOD + timeNum);
		Date checkDate = cal.getTime();		
		loanDAO.updateOverDueStatus(checkDate);
		
		
		
		
	}
	
	
	public void checkNotOverDue(){
		
		
		IBookDAO bookDAO = new BookMapDAO(new BookHelper());
		ILoanDAO loanDAO = new LoanMapDAO(new LoanHelper());
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		IBook book  = bookDAO.addBook("authorX", "titleX", "callNoX");
		IMember member = memberDAO.addMember("fName0", "lName0", "000X", "email0");
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);

		setOverDueDate(-1, loanDAO);
		assertFalse(loan.checkOverDue(new Date()));
		
	}
	
	
	

	
	
	
	
	


}
