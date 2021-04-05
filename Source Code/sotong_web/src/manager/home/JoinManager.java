package manager.home;

import java.util.Date;

import dao.home.FamilyMemberDAO;

public class JoinManager {
	private FamilyMemberDAO familyMemberDAO;
	
	public JoinManager() {
		this.familyMemberDAO = new FamilyMemberDAO();
	}
	
	//id 확인 메소드
	public byte checkId(String id) {
		return familyMemberDAO.checkId(id);
	}
	
	//email 확인을 한다. 나중에 넣어야한다.
	
	//초대 받았을때 회원 등록 되는 메소드이다.
	public int joinMember(String id, String pw, String name, String email, String phoneNum, String homeCode) {
		byte role = 0;
		return familyMemberDAO.insertMember(homeCode, name, phoneNum, email, id, pw, role);
	}
	
	//초대 받지 않고 회원등록 하는 메소드
	public int joinMember(String id, String pw, String name, String email, String phoneNum) {
		byte role = 0;
		return this.familyMemberDAO.insertMember(name, phoneNum, email, id, pw, role);
	}
	
	//홈코드 여브 확인
	public int checkInviterHomeCode(String homeCode) {
		return familyMemberDAO.checkInviterHomeCode(homeCode);
	}
}
