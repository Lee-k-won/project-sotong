package manager.home;

import dao.home.FamilyHomeDAO;
import dao.home.FamilyMemberDAO;

public class MakeHomeManager {
	private FamilyHomeDAO familyHomeDAO;
	private FamilyMemberDAO familyMemberDAO;
	
	public MakeHomeManager() {
		this.familyHomeDAO = new FamilyHomeDAO();
		this.familyMemberDAO = new FamilyMemberDAO();
	}
	
	public String addHome(String homeName) {
		return this.familyHomeDAO.insertHome(homeName);
	}
	
	public int updateMember(String homeCode, String memberCode) {
		return this.familyMemberDAO.updateMember(homeCode, memberCode);
	}
	
}
