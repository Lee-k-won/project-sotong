package dao.schedule;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class FamilyEventResponseDAO implements Serializable{

	private static final long serialVersionUID = -2039265970611278330L;
	private DBConnectionModule connModule;
	private Connection conn;
	public FamilyEventResponseDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeCode(){
		GregorianCalendar cal = new GregorianCalendar();
		
		String familyEventResponseCode = "FR12" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return familyEventResponseCode;	
	}
	
	public int insertFamilyEventResponse(String family_event_response_code, String member_code, String family_event_code, String family_reponse_contents){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into family_event_response_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_response_code);
			pstmt.setString(2, member_code);
			pstmt.setString(3, family_event_code);
			pstmt.setString(4, family_reponse_contents);
			
			rowNum = pstmt.executeUpdate();
			
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
	public int insertFamilyEventResponse(String member_code, String family_event_code, String family_reponse_contents){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into family_event_response_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, makeCode());
			pstmt.setString(2, member_code);
			pstmt.setString(3, family_event_code);
			pstmt.setString(4, family_reponse_contents);
			
			rowNum = pstmt.executeUpdate();
			
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
	
	
	public int updateFamilyEventResponse(String family_event_response_code, String member_code, String family_event_code, String family_response_contents){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_event_response_tb set member_code=?, family_event_code=?, family_response_contents=? where family_event_response_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_code);
			pstmt.setString(2, family_event_code);
			pstmt.setString(3, family_response_contents);
			pstmt.setString(4, family_event_response_code);
			
			rowNum = pstmt.executeUpdate();
			
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
	
	public int deleteFamilyEventResponse(String family_event_response_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_event_response_tb where family_event_response_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_response_code);
			
			rowNum = pstmt.executeUpdate();
			
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
	
	public FamilyEventResponseVO selectFamilyEventResponse(String family_event_response_code){
		FamilyEventResponseVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_event_response_tb where family_event_response_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_response_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyEventResponseCode1 = rs.getString("family_event_response_code");
				String memberCode1 = rs.getString("member_code");
				String familyEventCode1 = rs.getString("family_event_code");
				String familyResponseContents1 = rs.getString("family_response_contents");
				
				vo = new FamilyEventResponseVO(familyEventResponseCode1, memberCode1, familyEventCode1, familyResponseContents1);
			
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
	
	public FamilyEventResponseVO[] selectFamilyEventResponseByEventCode(String family_event_code){
		FamilyEventResponseVO []vo = null;
		PreparedStatement pstmt = null;
		ArrayList<FamilyEventResponseVO> list = new ArrayList<FamilyEventResponseVO>();
		try{
			String sql = "select * from family_event_response_tb where family_event_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyEventResponseCode1 = rs.getString("family_event_response_code");
				String memberCode1 = rs.getString("member_code");
				String familyEventCode1 = rs.getString("family_event_code");
				String familyResponseContents1 = rs.getString("family_response_contents");
				
				list.add(new FamilyEventResponseVO(familyEventResponseCode1, memberCode1, familyEventCode1, familyResponseContents1));
			
			}
			
			vo = new FamilyEventResponseVO[list.size()];
			for(int cnt=0; cnt<list.size(); cnt++){
				vo[cnt] = list.get(cnt);
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
	
	public static void main(String[] args) {
		FamilyEventResponseDAO dao = new FamilyEventResponseDAO();
		//dao.insertFamilyEventResponse("er4", "m1", "e1", "테스트응답");
		//dao.deleteFamilyEventResponse("er4");
		//dao.updateFamilyEventResponse("er2", "m1", "e3", "수정응답");
		
		//System.out.println(dao.selectFamilyEventResponse("er2"));
		
		FamilyEventResponseVO []vo = dao.selectFamilyEventResponseByEventCode("e1");
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
		
	}
}
