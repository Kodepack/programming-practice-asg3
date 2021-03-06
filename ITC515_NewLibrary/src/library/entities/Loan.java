package library.entities;

import java.text.DateFormat;
import java.util.Date;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.entities.ELoanState;

public class Loan implements ILoan {

	private int id_;
	private final IMember borrower_;
	private final IBook book_;
	private Date borrowDate_;
	private Date dueDate_;
	private ELoanState state_;
	
	public Loan(IBook book, IMember borrower, Date borrowDate, Date returnDate) {
		if (!sane(book, borrower, borrowDate, returnDate)) {
			throw new IllegalArgumentException("Loan: constructor : invalid or bad parameters");
		}
		this.book_ = book;
		this.borrower_ = borrower;
		this.borrowDate_ = borrowDate;
		this.dueDate_ = returnDate;	
		this.state_ = ELoanState.PENDING;
	}
	
	private boolean sane(IBook book, IMember borrower, Date borrowDate, Date returnDate) {
		return  ( book != null && 
				  borrower != null && 
				  borrowDate != null && 
				  returnDate != null && 
				  borrowDate.compareTo(returnDate) <= 0);
	}


	@Override
	public void commit(int loanId) {
		if (!(state_ == ELoanState.PENDING)) {

			exRuntimeException(ELoanState.CURRENT, "commit");
		}
		if (loanId <= 0) {
			throw new RuntimeException(
					String.format("Loan : commit : id a valid integer  - (Postive Value)  : %d\n", 
							loanId));
		}
		
		this.id_ = loanId;
		state_ = ELoanState.CURRENT;
		book_.borrow(this);
		borrower_.addLoan(this);
	}
	
	@Override
	public void complete() {
		if (!(state_ == ELoanState.CURRENT || state_ == ELoanState.OVERDUE)) {

			exRuntimeException(ELoanState.COMPLETE, "complete");
		}
		state_ = ELoanState.COMPLETE;		
	}

	@Override
	public boolean isOverDue() {
		return (state_ == ELoanState.OVERDUE);
	}

	@Override
	public boolean checkOverDue(Date currentDate) {
		if (!(state_ == ELoanState.CURRENT || state_ == ELoanState.OVERDUE )) {

			
			exRuntimeException(ELoanState.OVERDUE, "checkOverDue");
			
		}
		if (currentDate.compareTo(dueDate_) > 0) {
			state_ = ELoanState.OVERDUE;
		}
		return isOverDue();
	}
	
	private void exRuntimeException(ELoanState state, String stateText){
		
		throw new RuntimeException(
				String.format("Loan : "+ stateText+" : incorrect state value  : %s -> %s\n",
						state_, state));
	}



	@Override
	public String toString() {
		return (String.format("Loan ID:  %d\nAuthor:   %s\nTitle:    %s\nBorrower: %s %s\nBorrowed: %s\nDue Date: %s", 
				id_, book_.getAuthor(), book_.getTitle(), borrower_.getFirstName(), borrower_.getLastName(),
				DateFormat.getDateInstance().format(borrowDate_),
				DateFormat.getDateInstance().format(dueDate_)));
	}
	
	@Override
	public IMember getBorrower() {
		return borrower_;
	}

	@Override
	public IBook getBook() {
		return book_;
	}

	@Override
	public int getID() {
		return id_;
	}
	
	public ELoanState getState() {
		return state_;
	}


}
