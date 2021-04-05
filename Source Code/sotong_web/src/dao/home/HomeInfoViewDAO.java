package dao.home;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnectionModule;

public class HomeInfoViewDAO implements Serializable{

	private static final long serialVersionUID = -7310027524662413669L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public HomeInfoViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	public HomeInfoViewVO[] selectHomeAllMember(String familyHomeCode)
	{
		HomeInfoViewVO vo = null;
		ArrayList<HomeInfoViewVO> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<HomeInfoViewVO>();
			String sql = "select * from home_info_view where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyHomeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String familyHomeName = rs.getString("family_home_name");
				String memberCode = rs.getString("member_code");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				vo = new HomeInfoViewVO(familyHomeCode,familyHomeName,memberCode,memberPhone,memberEmail,memberPhoto,memberNickName,memberColor,memberBirth,memberRole);
				voList.add(vo);
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		if(voList != null && voList.size()>=1)
		{
			return voList.toArray(new HomeInfoViewVO[voList.size()]);
		}
		else
		{
			return null;
		}
	}
	
	
	
	
	public HomeInfoViewVO selectHomeMember(String memberCode)
	{
		HomeInfoViewVO vo = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from home_info_view where member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){				
				
				String familyHomeCode = rs.getString("family_home_code");
				String familyHomeName = rs.getString("family_home_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				vo = new HomeInfoViewVO(familyHomeCode,familyHomeName,memberCode,memberPhone,memberEmail,memberPhoto,memberNickName,memberColor,memberBirth,memberRole);
				
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return vo;
	}

	public HomeInfoViewVO[] getHomeMemberList(String familyHomeCode)
	{
		ArrayList<HomeInfoViewVO> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<HomeInfoViewVO>();
			String sql = "select * from home_info_view where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyHomeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomeName = rs.getString("family_home_name");
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				voList.add(new HomeInfoViewVO(familyHomeCode,familyHomeName,memberCode,memberPhone,memberEmail,memberPhoto,memberNickName,memberColor,memberBirth,memberRole));
				
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if(voList == null || voList.size() <1)
		{
			return null;
		}
		else
		{
			return voList.toArray(new HomeInfoViewVO[voList.size()]);
		}
		
	}
	
	public HomeInfoViewVO[] searchNeighborMemberPhoneNum(String phoneNum)
	{
		ArrayList<HomeInfoViewVO> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<HomeInfoViewVO>();
			String sql = "select * from home_info_view where member_phone like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+phoneNum+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomeCode = rs.getString("family_home_code");
				String familyHomeName = rs.getString("family_home_name");
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				voList.add(new HomeInfoViewVO(familyHomeCode,familyHomeName,memberCode,memberPhone,memberEmail,memberPhoto,memberNickName,memberColor,memberBirth,memberRole));
				
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if(voList == null || voList.size() <1)
		{
			return null;
		}
		else
		{
			return voList.toArray(new HomeInfoViewVO[voList.size()]);
		}		
		
	}
	
	public HomeInfoViewVO[] searchNeighborHomeName(String homeName)
	{
		ArrayList<HomeInfoViewVO> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<HomeInfoViewVO>();
			String sql = "select * from home_info_view where member_role='1' AND family_home_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+homeName+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomeCode = rs.getString("family_home_code");
				String familyHomeName = rs.getString("family_home_name");
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				voList.add(new HomeInfoViewVO(familyHomeCode,familyHomeName,memberCode,memberPhone,memberEmail,memberPhoto,memberNickName,memberColor,memberBirth,memberRole));
				
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if(voList == null || voList.size() <1)
		{
			return null;
		}
		else
		{
			return voList.toArray(new HomeInfoViewVO[voList.size()]);
		}		
		
	}
	
	public HomeInfoViewVO[] searchNeighborMemberName(String memberName)
	{
		ArrayList<HomeInfoViewVO> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<HomeInfoViewVO>();
			String sql = "select * from home_info_view where member_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+memberName+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomeCode = rs.getString("family_home_code");
				String familyHomeName = rs.getString("family_home_name");
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				voList.add(new HomeInfoViewVO(familyHomeCode,familyHomeName,memberCode,memberPhone,memberEmail,memberPhoto,memberNickName,memberColor,memberBirth,memberRole));
				
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		if(voList == null || voList.size() <1)
		{
			return null;
		}
		else
		{
			return voList.toArray(new HomeInfoViewVO[voList.size()]);
		}		
		
	}
	
	public String[][] selectHomeMemberSimpleInfo(String homeCode, String member)
	{
		ArrayList<String[]> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<String[]>();
			String sql = "select member_code, member_nickName, MEMBER_BIRTH, MEMBER_PHOTO, MEMBER_COLOR from home_info_view where family_home_code=? and MEMBER_ROLE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			pstmt.setString(2, "0");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberCode = rs.getString("member_code");
				
				if (memberCode.equals(member)){
					// 자신이 들어오면 추가를 안한다.
				} else {
					String memberNickName = rs.getString("member_nickname");
					String memberBirth = rs.getString("MEMBER_BIRTH");
					String memberColor = rs.getString("Member_color");
					String memberPhoto = rs.getString("member_photo");
					
					if (memberColor == null) {
						memberColor = "#fff";
					}
					if (memberPhoto == null) {
						memberPhoto = "img/profile/default.JPG"; 
					}

					voList.add(new String[]{memberCode, memberNickName, memberBirth, memberColor, memberPhoto });
				}
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return voList.toArray(new String[voList.size()][]);
	}
	
	//추가 app 사용 메소드
	public String[][] selectHomeMemberSimpleInfoApp(String homeCode)
	{
		ArrayList<String[]> voList = null;
		PreparedStatement pstmt = null;
		
		try{
			voList = new ArrayList<String[]>();
			String sql = "select member_code, member_nickName, MEMBER_BIRTH, MEMBER_PHOTO, MEMBER_COLOR from home_info_view where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberCode = rs.getString("member_code");
				String memberNickName = rs.getString("member_nickname");
				String memberBirth = rs.getString("MEMBER_BIRTH");
				String memberColor = rs.getString("Member_color");
				String memberPhoto = rs.getString("member_photo");

				
				if (memberColor == null) {
					memberColor = "#fff";
				}
				if (memberPhoto == null) {
					memberPhoto = "img/profile/default.JPG"; 
				}

				voList.add(new String[]{memberCode, memberNickName, memberBirth, memberColor, memberPhoto });
			
			}	
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
		return voList.toArray(new String[voList.size()][]);
	}
	
	public static void main(String[] args)
	{
		HomeInfoViewDAO dao = new HomeInfoViewDAO();
//		HomeInfoViewVO[] voList = dao.selectHomeAllMember("h2");
//		for(int i=0; i<voList.length;i++)
//		{
//			System.out.println("리스트"+voList[i]);
//		}
//		
//		System.out.println("가족 정보보기 : " + dao.selectHomeMember("m7"));
//		System.out.println("멤버목록 보기");
//		String[][] memberList = dao.getHomeMemberList("h1");
//		for(int i=0; i<memberList.length;i++)
//		{
//			for(int j=0; j<memberList[i].length;j++)
//			System.out.println(memberList[i][j]);
//		}
		
//		HomeInfoViewVO[] list = dao.searchNeighborHomeName("철연");
//		HomeInfoViewVO[] list = dao.searchNeighborMemberName("별님");
		HomeInfoViewVO[] list = dao.searchNeighborMemberPhoneNum("5678");
		for(int i=0; i<list.length;i++)
			{
				System.out.println("리스트"+list[i]);
			}
//		dao.searchNeighborMemberName("한별");
//		dao.searchNeighborMemberPhoneNum("5678");
	}
		
	
	
	//홈 구현 단계 에서 추가 homeCode를 통해 homeName을 구해온다.
	public String selectHomeName(String homeCode)
	{
		String familyHomeName = null;
		PreparedStatement pstmt = null;

		try{
			String sql = "select FAMILY_HOME_NAME from home_info_view where FAMILY_HOME_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
				familyHomeName = rs.getString("FAMILY_HOME_NAME");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return familyHomeName;
	}
}

