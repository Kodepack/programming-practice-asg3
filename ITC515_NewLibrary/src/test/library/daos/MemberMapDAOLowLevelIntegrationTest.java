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

	/**
	 * Test for get member by id
	 */
	@Test
	public void testGetMemberById(){
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		//Lets add member
		memberDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		//Lets try to get that member
		IMember member = memberDAO.getMemberByID(MEMBER_01_ID);
		//Lets check the members properties
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}

	/**
	 * Test for list members
	 */
	@Test
	public void testListMembers(){

		
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		//Add one member fist
		memberDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//Lets try to list members
		List<IMember> memberList = memberDAO.listMembers();
		IMember member = memberList.get(0);

		//Lets check the size of the member list
		assertEquals(1,memberList.size());
		//lets check the member's properties
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}
	
	/**
	 * Test for find members by last name
	 */
	@Test
	public void testFindMembersByLastName(){
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		
		//Add one member fist
		memberDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//Lets try to find member by last name
		List<IMember> memberList = memberDAO.findMembersByLastName(MEMBER_01_LAST_NAME);
		IMember member = memberList.get(0);

		//Lets check the size of the member list
		assertEquals(1,memberList.size());
		//lets check the member's properties
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}
	
	/**
	 * Test for find members by email address
	 */
	@Test
	public void testFindMembersByEmailAddress(){
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		//Add one member fist
		memberDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//Lets try to find member by last name
		List<IMember> memberList = memberDAO.findMembersByEmailAddress(MEMBER_01_CONTACT_EMAIL);
		IMember member = memberList.get(0);

		//Lets check the size of the member list
		assertEquals(1,memberList.size());
		//lets check the member's properties
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}	
	
	/**
	 * Test for find members by names
	 */
	@Test
	public void testFindMembersByNames(){
		IMemberDAO memberDAO = new MemberMapDAO(new MemberHelper());
		//Add one member fist
		memberDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//Lets try to find member by last name
		List<IMember> memberList = memberDAO.findMembersByNames(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME);
		IMember member = memberList.get(0);

		//Lets check the size of the member list
		assertEquals(1,memberList.size());
		//lets check the member's properties
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}	
	

}
