 


package test.library.entities;

import java.util.List;

import library.entities.Member;
import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anushka
 */


public class MemberTest {
    
    public MemberTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasOverDueLoans method, of class Member.
     */
    @Test
    public void testHasOverDueLoans() {
        System.out.println("hasOverDueLoans");
        Member instance = null;
        boolean expResult = false;
        boolean result = instance.hasOverDueLoans();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasReachedLoanLimit method, of class Member.
     */
    @Test
    public void testHasReachedLoanLimit() {
        System.out.println("hasReachedLoanLimit");
        Member instance = null;
        boolean expResult = false;
        boolean result = instance.hasReachedLoanLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasFinesPayable method, of class Member.
     */
    @Test
    public void testHasFinesPayable() {
        System.out.println("hasFinesPayable");
        Member instance = null;
        boolean expResult = false;
        boolean result = instance.hasFinesPayable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasReachedFineLimit method, of class Member.
     */
    @Test
    public void testHasReachedFineLimit() {
        System.out.println("hasReachedFineLimit");
        Member instance = null;
        boolean expResult = false;
        boolean result = instance.hasReachedFineLimit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFineAmount method, of class Member.
     */
    @Test
    public void testGetFineAmount() {
        System.out.println("getFineAmount");
        Member instance = null;
        float expResult = 0.0F;
        float result = instance.getFineAmount();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addFine method, of class Member.
     */
    @Test
    public void testAddFine() {
        System.out.println("addFine");
        float fine = 0.0F;
        Member instance = null;
        instance.addFine(fine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of payFine method, of class Member.
     */
    @Test
    public void testPayFine() {
        System.out.println("payFine");
        float payment = 0.0F;
        Member instance = null;
        instance.payFine(payment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLoan method, of class Member.
     */
    @Test
    public void testAddLoan() {
        System.out.println("addLoan");
        ILoan loan = null;
        Member instance = null;
        instance.addLoan(loan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoans method, of class Member.
     */
    @Test
    public void testGetLoans() {
        System.out.println("getLoans");
        Member instance = null;
        List<ILoan> expResult = null;
        List<ILoan> result = instance.getLoans();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLoan method, of class Member.
     */
    @Test
    public void testRemoveLoan() {
        System.out.println("removeLoan");
        ILoan loan = null;
        Member instance = null;
        instance.removeLoan(loan);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Member.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Member instance = null;
        EMemberState expResult = null;
        EMemberState result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Member.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Member instance = null;
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Member.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Member instance = null;
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContactPhone method, of class Member.
     */
    @Test
    public void testGetContactPhone() {
        System.out.println("getContactPhone");
        Member instance = null;
        String expResult = "";
        String result = instance.getContactPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailAddress method, of class Member.
     */
    @Test
    public void testGetEmailAddress() {
        System.out.println("getEmailAddress");
        Member instance = null;
        String expResult = "";
        String result = instance.getEmailAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Member.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Member instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Member.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Member instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
