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

public class ScheduleViewDAO implements Serializable{

	private static final long serialVersionUID = -8210501748682327108L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public ScheduleViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd hh:mm");
	    String date = fmt.format(d);
	    return date;
	}
	
	public ScheduleViewVO selectScheduleView(String schedule_code){
		ScheduleViewVO vo = null;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from schedule_view where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				String scheduleCode1 = rs.getString("schedule_code");
				String familyHomeCode = rs.getString("family_home_code");
				String scheduleMemberCode = rs.getString("member_code");
				String scheduleMember1 = rs.getString("schedule_member");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				
				String scheduleStartDate1 = rs.getString("schedule_start_date"); 
				String scheduleEndDate1 = rs.getString("schedule_end_date");
				String scheduleAlarm1 = rs.getString("schedule_alarm");
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				String alarmMember1 = rs.getString("alarm_member");
				
				vo =new ScheduleViewVO(scheduleCode1, familyHomeCode,scheduleMemberCode,scheduleMember1, scheduleTitle1, 
						schedulePlace1, scheduleStartDate1, scheduleEndDate1, scheduleAlarm1, scheduleRepeat1, scheduleMemo1, alarmMember1);
				
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
	
	public ScheduleViewVO[] selectScheduleInfoListWeb(String memberCode, String year,String mon)
	{
		ScheduleViewVO vo = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleViewVO> voList = null;
		try{
			voList = new ArrayList<ScheduleViewVO>();
			String sql = "select * from schedule_view where substr(schedule_start_date,1,2)=? AND substr(schedule_start_date,4,2)=? AND member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			pstmt.setString(2, mon);
			pstmt.setString(3, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String familyHomeCode = rs.getString("family_home_code");
				String scheduleMemberCode = rs.getString("member_code");
				String scheduleMember1 = rs.getString("schedule_member");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				
				String scheduleStartDate1 = rs.getString("schedule_start_date"); 
				String scheduleEndDate1 = rs.getString("schedule_end_date");
				String scheduleAlarm1 = rs.getString("schedule_alarm");
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				String alarmMember1 = rs.getString("alarm_member");
				
				vo = new ScheduleViewVO(scheduleCode1, familyHomeCode,scheduleMemberCode,scheduleMember1, scheduleTitle1, 
						schedulePlace1, scheduleStartDate1, scheduleEndDate1, scheduleAlarm1, scheduleRepeat1, scheduleMemo1, alarmMember1);
				
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
		if(voList.size()<1 || voList==null)
		{
			return null;
		}
		else
		{
			return voList.toArray(new ScheduleViewVO[voList.size()]);
		}
	}
	
	public ScheduleViewVO[] selectAllFamilySchedule(String familyHomeCode, String year, String mon)
	{
		ScheduleViewVO vo = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleViewVO> voList = null;
		try{
			voList = new ArrayList<ScheduleViewVO>();
			String sql = "select * from schedule_view where substr(schedule_start_date,1,2)=? AND substr(schedule_start_date,4,2)=? AND family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			pstmt.setString(2, mon);
			pstmt.setString(3, familyHomeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
//				String familyHomeCode = rs.getString("family_home_code");
				String scheduleMemberCode = rs.getString("member_code");
				String scheduleMember1 = rs.getString("schedule_member");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				
				String scheduleStartDate1 = rs.getString("schedule_start_date"); 
				String scheduleEndDate1 = rs.getString("schedule_end_date");
				String scheduleAlarm1 = rs.getString("schedule_alarm");
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				String alarmMember1 = rs.getString("alarm_member");
				
				vo = new ScheduleViewVO(scheduleCode1, familyHomeCode,scheduleMemberCode,scheduleMember1, scheduleTitle1, 
						schedulePlace1, scheduleStartDate1, scheduleEndDate1, scheduleAlarm1, scheduleRepeat1, scheduleMemo1, alarmMember1);
				
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
		if(voList.size()<1 || voList==null)
		{
			return null;
		}
		else
		{
			return voList.toArray(new ScheduleViewVO[voList.size()]);
		}
	}
	
	public ScheduleViewVO[] selectAllFamilySchedule(String familyHomeCode)
	{
		ScheduleViewVO vo = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleViewVO> voList = null;
		try{
			voList = new ArrayList<ScheduleViewVO>();
			String sql = "select * from schedule_view where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
//				String familyHomeCode = rs.getString("family_home_code");
				String scheduleMemberCode = rs.getString("member_code");
				String scheduleMember1 = rs.getString("schedule_member");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				
				String scheduleStartDate1 = rs.getString("schedule_start_date"); 
				String scheduleEndDate1 = rs.getString("schedule_end_date");
				String scheduleAlarm1 = rs.getString("schedule_alarm");
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				String alarmMember1 = rs.getString("alarm_member");
				
				vo = new ScheduleViewVO(scheduleCode1, familyHomeCode,scheduleMemberCode,scheduleMember1, scheduleTitle1, 
						schedulePlace1, scheduleStartDate1, scheduleEndDate1, scheduleAlarm1, scheduleRepeat1, scheduleMemo1, alarmMember1);
				
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
		if(voList.size()<1 || voList==null)
		{
			return null;
		}
		else
		{
			return voList.toArray(new ScheduleViewVO[voList.size()]);
		}
	}
	

public String[][] selectAllFamilyScheduleByDate(String familyHomeCode, String year, String month){
		String [][]returnString = null;
		PreparedStatement pstmt = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		try{
			String sql = "select schedule_code, family_home_code, schedule_start_date, schedule_end_date from schedule_view where family_home_code=? AND ((substr(schedule_start_date,1,2)=? AND substr(schedule_start_date,4,2)=?) OR (substr(schedule_end_date,1,2)=? AND substr(schedule_end_date,4,2)=?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyHomeCode);
			pstmt.setString(2, year);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			pstmt.setString(5, month);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String familyHomeCode1 = rs.getString("family_home_code");
				String scheduleStartDate1 = rs.getString("schedule_start_date");
				String scheduleEndDate1 = rs.getString("schedule_end_date");
				list.add(new String[]{scheduleCode1, familyHomeCode1, scheduleStartDate1, scheduleEndDate1});
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
		ScheduleViewDAO dao = new ScheduleViewDAO();
		ScheduleViewVO vo = dao.selectScheduleView("s10");
		System.out.println(vo);
	}
	
}
