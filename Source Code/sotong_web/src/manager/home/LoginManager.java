package manager.home;

import dao.home.FamilyMemberDAO;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewDAO;

public class LoginManager {
	private FamilyMemberDAO familyMemberDAO;
	private HomeInfoViewDAO homeInfoViewDAO;
	
	public LoginManager() {
		this.familyMemberDAO = new FamilyMemberDAO();
		this.homeInfoViewDAO = new HomeInfoViewDAO();
	}
	
	//아이디 비밀번호를 가지고 로그인 확인을 한다.
	public FamilyMemberVO checkLogin(String id, String pw) {
		return this.familyMemberDAO.checkLogin(id, pw);
	}
	
	//home 매니저 정보를 가져오는 메소드 추가됐다.
	public FamilyMemberVO homeManagerInfo(String homeCode) {
		return this.familyMemberDAO.homeManagerInfo(homeCode);
	}
	
	//아이디를 통해 해당 윶저의 상세 정보를 가져온다.
	public FamilyMemberVO getMemberInfo(String id) {
		return this.familyMemberDAO.getMemberInfoById(id);
	}
	
	//homeCode를 통해 속한 가족 구성원의 정보들을 가져온다.Web
	public String[][] getMemberListWeb(String homeCode, String member) {
		return this.homeInfoViewDAO.selectHomeMemberSimpleInfo(homeCode, member);
	}
	
	//homeCode를 통해 속한 가족 구성원의 정보들을 가져온다. App
	public String[][] getMemberListApp(String homeCode) {
		return this.homeInfoViewDAO.selectHomeMemberSimpleInfoApp(homeCode);
	}
	
	//homeCode를 통해 홈 이름을 가져온다.
	public String getHomeName(String homeCode) {
		return homeInfoViewDAO.selectHomeName(homeCode);
	}
}
