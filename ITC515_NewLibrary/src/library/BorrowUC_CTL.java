package library;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.IBorrowUIListener;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.ICardReaderListener;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;
import library.interfaces.hardware.IScannerListener;

public class BorrowUC_CTL implements ICardReaderListener, 
									 IScannerListener, 
									 IBorrowUIListener {
	
	private ICardReader reader;
	private IScanner scanner; 
	private IPrinter printer; 
	private IDisplay display;
	//private String state;
	private int scanCount = 0;
	private IBorrowUI ui;
	private EBorrowState state; 
	private IBookDAO bookDAO;
	private IMemberDAO memberDAO;
	private ILoanDAO loanDAO;
	
	private List<IBook> bookList;
	private List<ILoan> loanList;
	private IMember borrower;
	
	private JPanel previous;


	public BorrowUC_CTL(ICardReader reader, IScanner scanner, 
			IPrinter printer, IDisplay display,
			IBookDAO bookDAO, ILoanDAO loanDAO, IMemberDAO memberDAO ) {
		//Kishantha
		//assign the constructor variables 
		this.bookDAO = bookDAO;
		this.memberDAO= memberDAO;
		this.loanDAO = loanDAO;
		this.reader = reader;
		this.scanner = scanner;
		this.printer = printer;

		//initialize the listeners
		reader.addListener(this);
		scanner.addListener(this);
		reader.addListener(this);
		this.display = display;
		this.ui = new BorrowUC_UI(this);
		
		//replaced the variable assignement with
		//the setter
		//state = EBorrowState.CREATED;
		setState(EBorrowState.INITIALIZED);
		//No need to display here as it will be done at
		//initialise() which is called from the Main class borrowBooks() method
		//this.display.setDisplay((JPanel) ui,"Library");
		
	}
	
	public void initialise() {
		previous = display.getDisplay();
		display.setDisplay((JPanel) ui, "Borrow UI");		
	}
	
	public void close() {
		display.setDisplay(previous, "Main Menu");
	}

	@Override
	public void cardSwiped(int memberID) {
		throw new RuntimeException("Not implemented yet");
	}
	
	
	
	@Override
	public void bookScanned(int barcode) {
		throw new RuntimeException("Not implemented yet");
	}

	
	private void setState(EBorrowState state) {
		//Kishantha
		//throw new RuntimeException("Not implemented yet");
		//Added the state change implementation
		this.state = state;
		ui.setState(state);

		switch (state) {
		
		case INITIALIZED:
			reader.setEnabled(true);
			scanner.setEnabled(false);
			break;
			
		case SCANNING_BOOKS:
			reader.setEnabled(false);
			scanner.setEnabled(true);
			this.bookList = new ArrayList<IBook>();
			this.loanList = new ArrayList<ILoan>();
			break;
			
		case CONFIRMING_LOANS:
			reader.setEnabled(false);
			scanner.setEnabled(false);
			for (ILoan loan : loanList) {
				ui.displayConfirmingLoan(loan.toString());
			}
			break;
			
		case COMPLETED:
			reader.setEnabled(false);
			scanner.setEnabled(false);
			for (ILoan loan : loanList) {
				loanDAO.commitLoan(loan);
				printer.print(loan.toString()+"\n\n");
			}
			break;
			
		case CANCELLED:
			reader.setEnabled(false);
			scanner.setEnabled(false);
			break;
			
		case BORROWING_RESTRICTED:
			reader.setEnabled(false);
			scanner.setEnabled(false);
			break;
			
		default:
			throw new RuntimeException("Unknown state");
		}
	}

	@Override
	public void cancelled() {
		close();
	}
	
	@Override
	public void scansCompleted() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansConfirmed() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansRejected() {
		throw new RuntimeException("Not implemented yet");
	}

	private String buildLoanListDisplay(List<ILoan> loans) {
		StringBuilder bld = new StringBuilder();
		for (ILoan ln : loans) {
			if (bld.length() > 0) bld.append("\n\n");
			bld.append(ln.toString());
		}
		return bld.toString();		
	}

}
