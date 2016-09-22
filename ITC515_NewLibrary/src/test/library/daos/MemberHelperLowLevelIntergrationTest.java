package test.library.daos;

import org.junit.Test;

import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IMember;

public class MemberHelperLowLevelIntergrationTest {
	
	private IMemberDAO memberDAO;
	
	@Test
	
	//creates members and print all members on a loop
	
	public void makeMemeberTest(){
		
		
		memberDAO = new MemberMapDAO(new MemberHelper());	
		
		IMember[] member = new IMember[6];
		IMember eachMember;
		member[0] = memberDAO.addMember("fName0", "lName0", "0001", "email0");
		member[1] = memberDAO.addMember("fName1", "lName1", "0002", "email1");
		member[2] = memberDAO.addMember("fName2", "lName2", "0003", "email2");
		member[3] = memberDAO.addMember("fName3", "lName3", "0004", "email3");
		member[4] = memberDAO.addMember("fName4", "lName4", "0005", "email4");
		member[5] = memberDAO.addMember("fName5", "lName5", "0006", "email5");
		
		
		for (int i=0;i<5;i++){
			
			eachMember=member[i];
			System.out.println(eachMember.getID());
		
		}
		
		
		
	}
	
	
	
	

}
