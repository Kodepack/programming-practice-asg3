package test.library.entities;

import static org.junit.Assert.assertEquals;

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
		
		IBook book  = bookDAO.addBook("author1", "title1", "callNo1");
		IMember member = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		ILoan loan = loanDAO.createLoan(member, book);
		loanDAO.commitLoan(loan);
		
		
		System.out.println(loan.toString());
		
		
	}
	

	
	
	
	
	


}
