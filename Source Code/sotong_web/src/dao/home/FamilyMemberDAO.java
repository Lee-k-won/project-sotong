package dao.home;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;


public class FamilyMemberDAO implements Serializable{

	private static final long serialVersionUID = -918484301002759652L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyMemberDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeMemberCode(){ //임시코드
		GregorianCalendar cal = new GregorianCalendar();
		
		String memberCode = "FM05" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return memberCode;
	}
	
	public int insertMember(String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, byte memberRole) {
		
		System.out.println("FamilyMemberDAO : Homcode : " + familyHomecode);
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_member_tb values (?, ?, ?, ?, ?, ?, ?, 'img/profile/default.JPG', ?, null, '00-00-00', ?)";
			pstmt = conn.prepareStatement(sql);
			System.out.println(memberPhone);
			
			pstmt.setString(1, makeMemberCode());
			pstmt.setString(2, familyHomecode);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setString(5, memberEmail);
			pstmt.setString(6, memberId);
			pstmt.setString(7, memberPw);
			pstmt.setString(8, memberName); // 별명 위치	
			pstmt.setByte(9, memberRole);   // 매니저에서 분기시 1 또는 0 , 1 : 매니저  0 : 가족
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public FamilyMemberVO checkLogin(String id, String pw)
	{		
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where member_id=? and MEMBER_PW=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberCode = rs.getString("member_code");
				String familyHomecode = rs.getString("family_home_code");
				String memberName = rs.getString("member_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
			
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				vo = new FamilyMemberVO(memberCode,familyHomecode,memberName,memberPhone,memberEmail,id,pw,memberPhoto,memberNickName,memberColor,memberBirth,memberRole );
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
	
	public int insertMemberAllInfo(String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth,
			byte memberRole) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_member_tb values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeMemberCode());
			pstmt.setString(2, familyHomecode);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setString(5, memberEmail);
			pstmt.setString(6, memberId);
			pstmt.setString(7, memberPw);
			pstmt.setString(8, memberPhoto);
			pstmt.setString(9, memberNickName);
			pstmt.setString(10, memberColor);
			pstmt.setString(11, memberBirth);
			pstmt.setByte(12, memberRole);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int updateMember(String memberCode, String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth,
			byte memberRole) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = ?, member_name=?, member_phone=?, member_email=?, member_id=?, member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?, member_role=? where member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, familyHomecode);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberPhone);
			pstmt.setString(4, memberEmail);
			pstmt.setString(5, memberId);
			pstmt.setString(6, memberPw);
			pstmt.setString(7, memberPhoto);
			pstmt.setString(8, memberNickName);
			pstmt.setString(9, memberColor);
			pstmt.setString(10, memberBirth);
			pstmt.setByte(11, memberRole);
			
			pstmt.setString(12, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int updateMember(String homeCode, String memberCode) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = ?, member_role=? where member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, homeCode);
			pstmt.setString(2, "1");
			pstmt.setString(3, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int updateMember(String memberCode, 
			String memberName, String memberPhone, String memberEmail,
			String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set member_name=?, member_phone=?, member_email=?,member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?where member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberEmail);
			pstmt.setString(4, memberPw);
			pstmt.setString(5, memberPhoto);
			pstmt.setString(6, memberNickName);
			pstmt.setString(7, memberColor);
			pstmt.setString(8, memberBirth);
			pstmt.setString(9, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int updateMemberById(String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth,
			byte memberRole)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = ?, member_name=?, member_phone=?, member_email=?, member_id=?, member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?, member_role=? where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, familyHomecode);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberPhone);
			pstmt.setString(4, memberEmail);
			pstmt.setString(5, memberId);
			pstmt.setString(6, memberPw);
			pstmt.setString(7, memberPhoto);
			pstmt.setString(8, memberNickName);
			pstmt.setString(9, memberColor);
			pstmt.setString(10, memberBirth);
			pstmt.setByte(11, memberRole);
			
			pstmt.setString(12, memberId);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int updateMemberById(String memberId, 
			String memberName, String memberPhone, String memberEmail,
			String memberPw, String memberPhoto,
			String memberNickName, String memberColor, String memberBirth,
			byte memberRole)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set member_name=?, member_phone=?, member_email=?,member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?, member_role=? where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberEmail);
			pstmt.setString(4, memberPw);
			pstmt.setString(5, memberPhoto);
			pstmt.setString(6, memberNickName);
			pstmt.setString(7, memberColor);
			pstmt.setString(8, memberBirth);
			pstmt.setByte(9, memberRole);
			
			pstmt.setString(10, memberId);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int deleteMember(String memberCode)
	{
		int rowNum=0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_member_tb where member_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberCode);
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int deleteMemberById(String memberId)
	{
		int rowNum=0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_member_tb where member_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
		
	public FamilyMemberVO getMemberInfo(String memberCode)
	{
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomecode = rs.getString("family_home_code");
				String memberName = rs.getString("member_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberId = rs.getString("member_id");
				String memberPw = rs.getString("member_pw");
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				
				vo = new FamilyMemberVO(memberCode,familyHomecode,memberName,memberPhone,memberEmail,memberId,memberPw,memberPhoto,memberNickName,memberColor,memberBirth,memberRole );
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
	
	public FamilyMemberVO getMemberInfoById(String memberId)
	{
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberCode = rs.getString("member_code");
				String familyHomecode = rs.getString("family_home_code");
				String memberName = rs.getString("member_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				
				String memberPw = rs.getString("member_pw");
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				vo = new FamilyMemberVO(memberCode,familyHomecode,memberName,memberPhone,memberEmail,memberId,memberPw,memberPhoto,memberNickName,memberColor,memberBirth,memberRole );
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
	
	public byte checkId(String memberId)
	{
		PreparedStatement pstmt = null;
		try{
			String sql = "select member_id from family_member_tb where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
		
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()==false) {return -1;}
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
		return 1;
	}

		   	   
	/**
	 * 매니저의 권한을 변경한다.
	 * @param managerMemberCode 현재 매니저인 멤버의 식별코드
	 * @param memberCode 매니저 권한을 받을 멤버의 식별코드
	 * @return 성공여부
	 */
	public byte changeRole(String managerMemberCode, String memberCode)
	{
		PreparedStatement pstmt = null;
		int check1 = -1; // 매니저 권한 내리기 체크
		int check2 = -1; // 매니저 권한 올리기 체크
		try{
			
			//매니저 권한인 사람을 가족구성원으로 내리기
			String sql = "update family_member_tb set member_role = '0' where member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, managerMemberCode);
			
			check1 = pstmt.executeUpdate();
			if(check1==-1) // 내리기 실패시 -1 리턴
			{
				conn.rollback(); 
				return -1;
			}
			else
			{
				String sql2 = "update family_member_tb set member_role = '1' where member_code=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, memberCode);
				
				check2 = pstmt.executeUpdate();
				if(check2==-1)
				{
					conn.rollback();
					return -1;
				}
				else
				{
					conn.commit();
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
		return 1;
	}
	
	public int updateCutMember(String memberCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = null, member_photo=null, member_nickname=member_name, member_color=null, member_birth=null, member_role='-1' where member_code = ?";
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	
	public int checkInviterHomeCode(String homeCode)
	{
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select family_home_code from family_member_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()==false)
			{
				return -1;
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
		return 1;
	}
	public static void main(String[] args)
	{
		FamilyMemberDAO dao = new FamilyMemberDAO();
		
//		System.out.println("추가"+dao.insertMemberAllInfo("h1", "멤버땨응d", "1235454", "aa+s", "id42", "pw1", "멤버사진이란웅", "멤버땨땨응", "#ff88ff", new Date(), (byte)1));
//		System.out.println("추가"+dao.insertMember("h1", "멤버땨응s", "1235454", "aa+s", "id46", "pw1", "멤버사진이란웅", "멤버땨땨응", "#ff88ff", new Date(), (byte)1));
//		System.out.println("추가"+dao.insertMember("h1", "멤버땨응a", "1235454", "aa+s", "id47", "pw1", "멤버사진이란웅", "멤버땨땨응", "#ff88ff", new Date(), (byte)1));
//		System.out.println("수정"+dao.updateMember("m1", "h1", "장재영", "재영씨폰~", "재영씨이멜", "jaeyoung", "123", "재영씨사진~", "장재영", "#6688ff", new Date(), (byte)2));
//		System.out.println("조회"+dao.selectMember("m1"));
//		System.out.println("수정"+dao.updateMemberById("h3", "장재영", "재영씨폰~바뀜~", "재영씨이멜두바뀜~", "jaeyoung", "123", "재영씨사진두바뀜~", "영재", "#6688ff", new Date(), (byte)2));
//		System.out.println("아디로조회"+dao.selectMemberById("jaeyoung"));
//		System.out.println("삭제"+dao.deleteMember("300029157202637"));
//		System.out.println("아디로삭제"+dao.deleteMemberById("id44"));
		
//		System.out.println("매니저 변경" + dao.changeRole("m1", "m4"));
//		System.out.println("아이디 체크(X) : " + dao.checkId("Fe3"));
//		System.out.println("아이디 체크(O) : " + dao.checkId("Fe22"));
//		System.out.println("로그인 체크(X) : " + dao.checkLogin("lee2", "123234"));
//		System.out.println("로그인 체크(O) : " + dao.checkLogin("lee2", "1234"));
//		System.out.println("정보 변경 : " + dao.updateMember("m2", "테슽으", "012-3457-8888", "test@email.com", "5678", "image/profile/photo", "테스틏", "#fffff", new Date(), (byte)0));
//	
//		System.out.println(dao.updateCutMember("m1"));
//		System.out.println(dao.updateCutMember("m5"));
//		System.out.println(dao.checkInviterHomeCode("h1"));
//		System.out.println(dao.checkInviterHomeCode("h5"));
		
		
	}
	
	//구현 단계에서 추가
	public int insertMember(String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, byte memberRole) {
		
		System.out.println("FamilyMemberDAO : memberName : " + memberName);
		
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_member_tb values (?, ?, ?, ?, ?, ?, ?, 'img/profile/default.JPG', ?, null, '00-00-00', ?)";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, makeMemberCode());
			pstmt.setString(2, null);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setString(5, memberEmail);
			pstmt.setString(6, memberId);
			pstmt.setString(7, memberPw);
			pstmt.setString(8, memberName); // 별명 위치	
			pstmt.setByte(9, memberRole);   // 매니저에서 분기시 1 또는 0 , 1 : 매니저  0 : 가족
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
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
		return rowNum;
	}
	

	//home manager 정보를 가져오는 메소드
	public FamilyMemberVO homeManagerInfo(String homeCode)
	{		
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where FAMILY_HOME_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()){
				
				String memberRole = rs.getString("member_role");
				
				if (memberRole.equals("1")) {
					
					System.out.println("매니저를 가질러 왔다.");
					
					String memberCode = rs.getString("member_code");
					String memberName = rs.getString("member_name");
					String memberPhone = rs.getString("member_phone");
					String memberEmail = rs.getString("member_email");
				
					String memberPhoto = rs.getString("member_photo");
					String memberNickName = rs.getString("member_nickname");
					String memberColor = rs.getString("member_color");
					String memberBirth = rs.getString("member_birth");
					String memberId = rs.getString("MEMBER_ID");
					String memberPw = rs.getString("MEMBER_PW");
					
					if (memberColor == null) {
						memberColor = "#fff";
					}
					vo = new FamilyMemberVO(memberCode,homeCode,memberName,memberPhone,memberEmail,
							memberId,memberPw,memberPhoto,memberNickName,memberColor,memberBirth,memberRole);	
					break;
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
		return vo;
	}
}
