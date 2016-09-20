/**
 * 
 */
package test.library.daos;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import library.daos.MemberMapDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Kishantha
 *
 */

public class MemberMapDAOTest {
	
	private static final int MEMBER_01_ID = 1;
	private static final String MEMBER_01_FIRST_NAME = "";
	private static final String MEMBER_01_LAST_NAME = "Last Name";
	private static final String MEMBER_01_CONTACTPHONE_NUMBER = "000222333";
	private static final String MEMBER_01_CONTACT_EMAIL = "email@sample.com";
	

	
	@Before
	public void init() {
	    //MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddMember(){

		
		IMemberHelper helper = Mockito.mock(IMemberHelper.class);
		IMember iMember = Mockito.mock(IMember.class);
		MemberMapDAO memberMapDAO = new MemberMapDAO(helper);
		

		
		Mockito.when(helper.makeMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL, MEMBER_01_ID)).thenReturn(iMember);
		Mockito.when(iMember.getID()).thenReturn(MEMBER_01_ID);
		Mockito.when(iMember.getFirstName()).thenReturn(MEMBER_01_FIRST_NAME);
		Mockito.when(iMember.getLastName()).thenReturn(MEMBER_01_LAST_NAME);
		Mockito.when(iMember.getContactPhone()).thenReturn(MEMBER_01_CONTACTPHONE_NUMBER);
		Mockito.when(iMember.getEmailAddress()).thenReturn(MEMBER_01_CONTACT_EMAIL);

		//Lets add member
		IMember member = memberMapDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//verify the returned member details
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}

	@Test
	public void testGetMemberById(){

		
		IMemberHelper helper = Mockito.mock(IMemberHelper.class);
		IMember iMember = Mockito.mock(IMember.class);
		MemberMapDAO memberMapDAO = new MemberMapDAO(helper);
		
		Mockito.when(helper.makeMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL, MEMBER_01_ID)).thenReturn(iMember);
		Mockito.when(iMember.getID()).thenReturn(MEMBER_01_ID);
		Mockito.when(iMember.getFirstName()).thenReturn(MEMBER_01_FIRST_NAME);
		Mockito.when(iMember.getLastName()).thenReturn(MEMBER_01_LAST_NAME);
		Mockito.when(iMember.getContactPhone()).thenReturn(MEMBER_01_CONTACTPHONE_NUMBER);
		Mockito.when(iMember.getEmailAddress()).thenReturn(MEMBER_01_CONTACT_EMAIL);
		
		//Add one member fist
		memberMapDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//Lets try to get that member
		IMember member = memberMapDAO.getMemberByID(MEMBER_01_ID);
		
		//Lets check the members properties
		assertEquals(MEMBER_01_ID, member.getID());
		assertEquals(MEMBER_01_FIRST_NAME, member.getFirstName());
		assertEquals(MEMBER_01_LAST_NAME, member.getLastName());
		assertEquals(MEMBER_01_CONTACTPHONE_NUMBER, member.getContactPhone());
		assertEquals(MEMBER_01_CONTACT_EMAIL, member.getEmailAddress());
		
	}

	
	@Test
	public void testListMembers(){

		
		IMemberHelper helper = Mockito.mock(IMemberHelper.class);
		IMember iMember = Mockito.mock(IMember.class);
		MemberMapDAO memberMapDAO = new MemberMapDAO(helper);
		
		Mockito.when(helper.makeMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL, MEMBER_01_ID)).thenReturn(iMember);
		Mockito.when(iMember.getID()).thenReturn(MEMBER_01_ID);
		Mockito.when(iMember.getFirstName()).thenReturn(MEMBER_01_FIRST_NAME);
		Mockito.when(iMember.getLastName()).thenReturn(MEMBER_01_LAST_NAME);
		Mockito.when(iMember.getContactPhone()).thenReturn(MEMBER_01_CONTACTPHONE_NUMBER);
		Mockito.when(iMember.getEmailAddress()).thenReturn(MEMBER_01_CONTACT_EMAIL);
		
		//Add one member fist
		memberMapDAO.addMember(MEMBER_01_FIRST_NAME, MEMBER_01_LAST_NAME, MEMBER_01_CONTACTPHONE_NUMBER, MEMBER_01_CONTACT_EMAIL);
		
		//Lets try to list members
		List<IMember> memberList = memberMapDAO.listMembers();
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
