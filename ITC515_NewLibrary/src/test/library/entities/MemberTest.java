 


package test.library.entities;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
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
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);
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
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);
       boolean expResult = false;
       boolean result = instance.hasReachedLoanLimit();
       assertEquals(expResult, result);
       
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
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;
       instance.addFine(fine);
       
   }

   /**
    * Test of payFine method, of class Member.
    */
   @Test
   public void testPayFine() {
       System.out.println("payFine");
       float payment = 0.0F;
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;
       instance.payFine(payment);
       // TODO review the generated test code and remove the default call to fail.
       
   }

   /**
    * Test of addLoan method, of class Member.
    */
   @Test
   public void testAddLoan() {
       System.out.println("addLoan");
       List<ILoan> loan = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210).getLoans();
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;;
       instance.addLoan((ILoan) loan);
       
   }

   /**
    * Test of getLoans method, of class Member.
    */
   @Test
   public void testGetLoans() {
       System.out.println("getLoans");
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;
       List<ILoan> expResult = instance.getLoans();
       List<ILoan> result = instance.getLoans();
       assertEquals(expResult, result);
        
   }

   /**
    * Test of removeLoan method, of class Member.
    */
   @Test
   public void testRemoveLoan() {
       System.out.println("removeLoan");
       ILoan loan = null;
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;;
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
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);
       EMemberState expResult = instance.getState();
       EMemberState result = instance.getState();
       assertEquals(expResult, result);
       
   }

   /**
    * Test of getFirstName method, of class Member.
    */
   @Test
   public void testGetFirstName() {
       System.out.println("getFirstName");
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;;
       String expResult = "Gayan";
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
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;;
       String expResult = "tennakon";
       String result = instance.getLastName();
       assertEquals(expResult, result);
        
   }

   /**
    * Test of getContactPhone method, of class Member.
    */
   @Test
   public void testGetContactPhone() {
       System.out.println("getContactPhone");
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;
       String expResult = instance.getContactPhone();
       String result = instance.getContactPhone();
       assertEquals(expResult, result);
        
   }

   /**
    * Test of getEmailAddress method, of class Member.
    */
   @Test
   public void testGetEmailAddress() {
       System.out.println("getEmailAddress");
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);
       String expResult = instance.getEmailAddress();
       String result = instance.getEmailAddress();
       assertEquals(expResult, result);
       
   }

   /**
    * Test of getID method, of class Member.
    */
   @Test
   public void testGetID() {
       System.out.println("getID");
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;
       int expResult = 11594210;
       int result = instance.getID();
       assertEquals(expResult, result);
        
   }

   /**
    * Test of toString method, of class Member.
    */
   @Test
   public void testToString() {
       System.out.println("toString");
       Member instance = new Member("Gayan", "tennakon", "0469932148", "anushka@123.com", 11594210);;
       String expResult = "";
       String result = instance.toString();
       assertEquals(expResult, result);
       // TODO review the generated test code and remove the default call to fail.
       fail("The test case is a prototype.");
   }
   
}
