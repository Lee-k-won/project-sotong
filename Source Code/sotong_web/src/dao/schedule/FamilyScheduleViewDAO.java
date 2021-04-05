package dao.schedule;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class FamilyScheduleViewDAO implements Serializable{

	private static final long serialVersionUID = 6552634636191891884L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyScheduleViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}

	public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
	
	public FamilyScheduleViewVO[] selectFamilyScheduleView(String family_schedule_code){
		FamilyScheduleViewVO []vo = null;
		PreparedStatement pstmt = null;
		ArrayList<FamilyScheduleViewVO> list = new ArrayList<FamilyScheduleViewVO>();
		try{
			String sql = "select * from family_schedule_view where family_schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyHomeCode = rs.getString("FAMILY_HOME_CODE");
				String memberCode = rs.getString("member_code");
				String memberNickname = rs.getString("member_nickName");
				String familyScheduleTitle1 = rs.getString("family_schedule_title");
				String familySchedulePlace1 = rs.getString("family_schedule_place");
				String familyScheduleStartDate1 = rs.getString("family_schedule_start_date"); 
				String familyScheduleEndDate1 = rs.getString("family_schedule_end_date");
				String familyScheduleAlarm1 = rs.getString("family_schedule_alarm");
				int familyScheduleRepeat1 = rs.getInt("family_schedule_repeat");
				String familyScheduleMemo1 = rs.getString("family_schedule_memo");
				String familyEventRequest1 = rs.getString("family_event_request");
				String memberName1 = rs.getString("member_name");
				String familyResponseContents1 = rs.getString("family_response_contents");
				
				list.add(new FamilyScheduleViewVO(familyScheduleCode1,familyHomeCode,memberCode,memberNickname, familyScheduleTitle1, familySchedulePlace1, familyScheduleStartDate1, familyScheduleEndDate1, familyScheduleAlarm1, familyScheduleRepeat1, familyScheduleMemo1, familyEventRequest1, memberName1, familyResponseContents1));
				
			}
			
			vo = new FamilyScheduleViewVO[list.size()];
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
	
	public FamilyScheduleViewVO[] selectFamilyScheduleViewByHomeCode(String familyHomeCode){
		FamilyScheduleViewVO []vo = null;
		PreparedStatement pstmt = null;
		ArrayList<FamilyScheduleViewVO> list = new ArrayList<FamilyScheduleViewVO>();
		try{
			String sql = "select * from family_schedule_view where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyHomeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyHomeCode1 = rs.getString("FAMILY_HOME_CODE");
				String memberCode = rs.getString("member_code");
				String memberNickname = rs.getString("member_nickName");
				String familyScheduleTitle1 = rs.getString("family_schedule_title");
				String familySchedulePlace1 = rs.getString("family_schedule_place");
				String familyScheduleStartDate1 = rs.getString("family_schedule_start_date"); 
				String familyScheduleEndDate1 = rs.getString("family_schedule_end_date");
				String familyScheduleAlarm1 = rs.getString("family_schedule_alarm");
				int familyScheduleRepeat1 = rs.getInt("family_schedule_repeat");
				String familyScheduleMemo1 = rs.getString("family_schedule_memo");
				String familyEventRequest1 = rs.getString("family_event_request");
				String memberName1 = rs.getString("member_name");
				String familyResponseContents1 = rs.getString("family_response_contents");
				
				list.add(new FamilyScheduleViewVO(familyScheduleCode1,familyHomeCode1,memberCode,memberNickname, familyScheduleTitle1, familySchedulePlace1, familyScheduleStartDate1, familyScheduleEndDate1, familyScheduleAlarm1, familyScheduleRepeat1, familyScheduleMemo1, familyEventRequest1, memberName1, familyResponseContents1));
				
			}
			
			vo = new FamilyScheduleViewVO[list.size()];
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
	

public String[][] selectFamilyScheduleByDate(String familyHomeCode, String year, String month){
		String [][]returnString = null;
		PreparedStatement pstmt = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		try{
			String sql = "select family_schedule_code, family_home_code, family_schedule_start_date, family_schedule_end_date from family_schedule_view where family_home_code=? AND ((substr(family_schedule_start_date,1,2)=? AND substr(family_schedule_start_date,4,2)=?) OR (substr(family_schedule_end_date,1,2)=? AND substr(family_schedule_end_date,4,2)=?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyHomeCode);
			pstmt.setString(2, year);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, month);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyHomeCode1 = rs.getString("family_home_code");
				String familyScheduleStartDate1 = rs.getString("family_schedule_start_date");
				String familyScheduleEndDate1 = rs.getString("family_schedule_end_date");
				
				list.add(new String[]{familyScheduleCode1, familyHomeCode1, familyScheduleStartDate1, familyScheduleEndDate1});
			}
			
			returnString = new String[list.size()][4];
			
			for(int cnt=0; cnt<list.size(); cnt++){
				returnString[cnt][0] = list.get(cnt)[0];
				returnString[cnt][1] = list.get(cnt)[1];
				returnString[cnt][2] = list.get(cnt)[2];
				returnString[cnt][3] = list.get(cnt)[3];
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
		
		return returnString;
		
	}
	/*
	public int yearCut(String date){
		Integer yearInt = new Integer(date.substring(0, 2));
		return yearInt.intValue()+100;
		
	}
	public int monthCut(String date){
		Integer yearInt = new Integer(date.substring(3, 5));
		return yearInt.intValue();
	}
	public int dateCut(String date){
		Integer yearInt = new Integer(date.substring(6, 8));
		return yearInt.intValue();
	}
	*/
	public static void main(String[] args) {
		FamilyScheduleViewDAO dao = new FamilyScheduleViewDAO();
		FamilyScheduleViewVO []vo = dao.selectFamilyScheduleView("fs1");
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
		
	}
	
}
