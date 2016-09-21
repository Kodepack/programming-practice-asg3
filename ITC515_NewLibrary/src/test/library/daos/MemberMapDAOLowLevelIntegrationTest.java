package test.library.daos;

import static org.junit.Assert.assertEquals;

import java.util.List;

import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IMember;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Kishantha
 *
 */
public class MemberMapDAOLowLevelIntegrationTest {

	
	private static final int MEMBER_01_ID = 1;
	private static final String MEMBER_01_FIRST_NAME = "Kishantha";
	private static final String MEMBER_01_LAST_NAME = "Nanayakkara";
	private static final String MEMBER_01_CONTACTPHONE_NUMBER = "000222333";
	private static final String MEMBER_01_CONTACT_EMAIL = "email@sample.com";
	
	
	/**
	 * Test for add member
	 */
	@Test
	public void testAddMember(){
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		//Lets add member
		IMember member = memberDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//verify the returned member details
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}


}
