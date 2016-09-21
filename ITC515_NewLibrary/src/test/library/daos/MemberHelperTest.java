package test.library.daos;

import junit.framework.TestCase;
import library.daos.MemberHelper;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

import org.junit.Test;


public class MemberHelperTest extends TestCase{

	private static final String FIRSTNAME="FIRSTNAME";
	private static final String LASTNAME="LASTNAME";
	private static final String CONTACTPHONE="0452535065";
	private static final String EMAIL="abc@gmail.com"; 
	private static final int MEMBERID=54;
	
	@Test
	
	public void testmakeMember(){
		
		
		IMemberHelper MemberHelper =new MemberHelper();
		IMember member = MemberHelper.makeMember(FIRSTNAME, LASTNAME, CONTACTPHONE, EMAIL, MEMBERID);
		assertEquals(member.getContactPhone(), CONTACTPHONE);
		assertEquals(member.getFirstName(), FIRSTNAME);
		assertEquals(member.getLastName(), LASTNAME);
		assertEquals(member.getEmailAddress(), EMAIL);
		assertEquals(member.getID(), MEMBERID);
		

	}
	
}
