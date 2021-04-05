package manager.home;

import java.util.Date;

import dao.home.FamilyHomeDAO;
import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberDAO;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewDAO;
import dao.home.HomeInfoViewVO;

public class HomeManager {

	private HomeInfoViewDAO homeInfoViewDAO;
	private FamilyHomeDAO familyHomeDAO;
	private FamilyMemberDAO familyMemberDAO;
	
	public HomeManager() {
		homeInfoViewDAO = new HomeInfoViewDAO();
		familyHomeDAO = new FamilyHomeDAO();
		familyMemberDAO = new FamilyMemberDAO();
	}
	
	
	public String[][] getMemberList(String homeCode, String member) {
		return this.homeInfoViewDAO.selectHomeMemberSimpleInfo(homeCode, member);
	}
	
	public FamilyMemberVO homeManagerInfo(String homeCode) {
		return this.familyMemberDAO.homeManagerInfo(homeCode);
	}
	
	public FamilyHomeVO getHomeInfo(String homeCode)
	{
		return familyHomeDAO.selectHome(homeCode);
	}
	
	public HomeInfoViewVO[] getMemberList(String homeCode)
	{
		return homeInfoViewDAO.getHomeMemberList(homeCode);
	}
	
	public FamilyMemberVO getMemberDetailInfo(String memberCode)
	{
		return familyMemberDAO.getMemberInfo(memberCode);
	}
	
	public int modifyHomeName(String homeCode, String modifyName)
	{
		return familyHomeDAO.updateHome(homeCode, modifyName);
	}
	
	public int deleteMember(String memberCode)
	{
		return familyMemberDAO.deleteMember(memberCode);
	}
	
	public int updateMember(String memberCode) // cutMember
	{
		return familyMemberDAO.updateCutMember(memberCode);
	}
	
	public int changeRole(String managerMemberCode, String memberCode)
	{
		return familyMemberDAO.changeRole(managerMemberCode, memberCode);
	}
	
	public int updateMemberProfile(String memberCode, 
			String memberName, String memberPhone, String memberEmail,
			String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth)
	{
		return familyMemberDAO.updateMember(memberCode, memberName, memberPhone, memberEmail, memberPw, memberPhoto, memberNickName, memberColor, memberBirth);
	}
	
	public HomeInfoViewVO[] searchNeighbor(String searchCategory, String searchWord)
	{
		switch(searchCategory)
		{
		case "1" : return homeInfoViewDAO.searchNeighborHomeName(searchWord); 
		case "2" : return homeInfoViewDAO.searchNeighborMemberName(searchWord); 
		case "3" : return homeInfoViewDAO.searchNeighborMemberPhoneNum(searchWord);
		default : return null;
		}
	}
}
