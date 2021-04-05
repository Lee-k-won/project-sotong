package dao.schedule;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;

public class FamilyEventDAO implements Serializable{

	private static final long serialVersionUID = 7480399317473720435L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyEventDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public String makeCode(){
		GregorianCalendar cal = new GregorianCalendar();
		
		String familyEventCode = "FE11" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return familyEventCode;	
	}
	
	public int insertFamilyEvent(String family_event_code, String family_schedule_code, String family_event_request){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_event_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_code);
			pstmt.setString(2, family_schedule_code);
			pstmt.setString(3, family_event_request);
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
	
	public int insertFamilyEvent(String family_schedule_code, String family_event_request){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_event_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, makeCode());
			pstmt.setString(2, family_schedule_code);
			pstmt.setString(3, family_event_request);
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
	
	public int updateFamilyEvent(String family_event_code, String family_schedule_code, String family_event_request){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_event_tb set family_schedule_code=?, family_event_request=? where family_event_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_code);
			pstmt.setString(2, family_event_request);
			pstmt.setString(3, family_event_code);
			
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
	public int deleteFamilyEvent(String family_event_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_event_tb where family_event_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_code);
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
	public FamilyEventVO selectFamilyEvent(String family_event_code){
		FamilyEventVO vo = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from family_event_tb where family_event_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_event_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyEventCode1 = rs.getString("family_event_code");
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyEventRequest1 = rs.getString("family_event_request");
				
				vo = new FamilyEventVO(familyEventCode1, familyScheduleCode1, familyEventRequest1);
				
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
	
	public FamilyEventVO selectFamilyEventByFamilyScheduleCode(String family_schedule_code){
		FamilyEventVO vo = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from family_event_tb where family_schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyEventCode1 = rs.getString("family_event_code");
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyEventRequest1 = rs.getString("family_event_request");
				
				vo = new FamilyEventVO(familyEventCode1, familyScheduleCode1, familyEventRequest1);
				
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
		FamilyEventDAO dao = new FamilyEventDAO();
		
		//dao.insertFamilyEvent("e1", "fs2", "테스트요청");
		//dao.deleteFamilyEvent("e1");
		//dao.updateFamilyEvent("e3", "fs1", "수정요청!");
		
		System.out.println(dao.selectFamilyEventByFamilyScheduleCode("fs2"));
	}
	
}
