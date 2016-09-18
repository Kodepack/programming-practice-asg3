/**
 * 
 */
package test.library.daos;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import library.daos.MemberMapDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IMember;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Kishantha
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MemberMapDAOTest {
	@Mock
	private IMemberHelper helper;

	@Mock
	private Map<Integer, IMember> memberMap;
	@InjectMocks
	private IMemberDAO memberMapDAO;
	
	
	
	@Test
	public void testAddMember(){
		
		String firstName = "";
		String lastName = "Last Name";
		String ContactPhone = "000222333";
		String emailAddress = "email@sample.com";
		
		
		memberMapDAO = new MemberMapDAO(helper);
		IMember iMember = memberMapDAO.addMember(firstName, lastName, ContactPhone, emailAddress);
		//assertEquals("");
	}

}
