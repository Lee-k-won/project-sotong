package dao.story;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.DBConnectionModule;


public class StoryDAO implements Serializable{

	private static final long serialVersionUID = 5199019678495014462L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public StoryDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public StoryDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode() {
		GregorianCalendar cal = new GregorianCalendar();
		
		String storyCode = "SD22" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return storyCode;
	}
	
	public int insertStory(String storyCode, String memberCode, String homeCode,
		String sotongCotentsCode, Date storyDate, int storyHeart,
		Date modifyDate, Boolean storyScope) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = sendChangeDate(storyDate);
		
		try{
			String sql = "insert into story_tb values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,storyCode);
			pstmt.setString(2, memberCode);
			pstmt.setString(3, homeCode);
			pstmt.setString(4,sotongCotentsCode);
			pstmt.setString(5, nowDate);
			pstmt.setInt(6, storyHeart);
			pstmt.setString(7,nowDate);
			if (storyScope) {
				pstmt.setString(8, "이웃");
			} else {
				pstmt.setString(8, "가족");
			}
			
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
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
	
	//구현 단계에서 추가
		public boolean insertStory(String homeCode, String memberCode, String sotongContentsCode,
			String storyDate, String scope) {
			
			int rowNum = 0;
			PreparedStatement pstmt = null;
			
			try{
				String sql = "insert into story_tb values(?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,makeMenuCode());
				pstmt.setString(2, memberCode);
				pstmt.setString(3, homeCode);
				pstmt.setString(4,sotongContentsCode);
				pstmt.setString(5, storyDate);
				pstmt.setInt(6, 0);
				pstmt.setString(7,storyDate);
				pstmt.setString(8, scope);
				
				
				rowNum = pstmt.executeUpdate();
				
				if (rowNum != 0) {
					conn.commit();
				} else {
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
			if (rowNum != 0) {
				return true;
			} else {
				return false;
			}
		}
		
		
	public String sendChangeDate(Date dateTime) {
		int year= dateTime.getYear() - 100;
		String mon = (dateTime.getMonth()+1)>9 ? "" + (dateTime.getMonth()+1) : "0" + (dateTime.getMonth()+1);
		String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
	    String hour = dateTime.getHours()>9 ? "" + dateTime.getHours() : "0" + dateTime.getHours();
	    
	    String min = dateTime.getMinutes()>9 ? "" + dateTime.getMinutes() : "0" + dateTime.getMinutes();
	    System.out.println(min);
		return  year + "-" + mon +"-" + day + "-" + hour + ":" + min; 
	}
	
	public int updateStory(String storyCode, String memberCode, String homeCode,
			String sotongCotentsCode, int storyHeart, Date modifyDate, Boolean storyScope) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = sendChangeDate(modifyDate);
		
		
		try{
			String sql = "update story_tb set member_code=?, FAMILY_HOME_CODE=?, sotong_contents_code =?, "
					+ "story_heart = ?, story_modify_date=?,story_scope = ? where story_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, memberCode);
			pstmt.setString(2, homeCode);
			pstmt.setString(3, sotongCotentsCode);
			pstmt.setInt(4, storyHeart);
			pstmt.setString(5, nowDate);
			if (storyScope) {
				pstmt.setString(6, "이웃");
			} else {
				pstmt.setString(6, "가족");
			}
			pstmt.setString(7, storyCode);
			
			
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
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
	
	public boolean updateStoryDate(String storyCode, Date storyDate) {
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = sendChangeDate(storyDate);
		
		try{
			String sql = "update story_tb set story_modify_date=? where story_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, nowDate);
			pstmt.setString(2, storyCode);
						
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
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
		if (rowNum != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//매니저 구현 단계에서 추가 
	public boolean deleteStory(String homeCode, int storyIndex) {
		int checkDelete = 0;
		PreparedStatement pstmt = null;		
		System.out.println("삭제하려왔어요");
		try{
			String sql = "select story_code from story_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			int rowNum = 0;
			
			while(rs.next()){
				if (rowNum == storyIndex) {
					System.out.println(rowNum + "번을 삭제할 거예요");
					System.out.println(storyIndex + "번을 삭제해야 됨!!!");
					String storyCode = rs.getString("story_code");
					checkDelete = deleteCode(storyCode);
				}
				rowNum++;
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
		if (checkDelete != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int deleteCode(String storyCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from story_tb where story_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storyCode);
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
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
	
	public StoryVO selectCode(String code){
		StoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from story_tb where story_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String storyCode = rs.getString("story_code");
				String memberCode = rs.getString("member_code");
				String homeCode = rs.getString("FAMILY_HOME_CODE");
				String sotongCotentsCode = rs.getString("SOTONG_CONTENTS_CODE");
				String storyDate = rs.getString("story_date");
				int storyHeart = rs.getInt("story_heart");
				String modifyDate = rs.getString("STORY_MODIFY_DATE");
				String storyScope = rs.getString("story_scope");
				
				
				Date writenDate = changeDate(storyDate);
				Date updateDate = changeDate(modifyDate);
				Boolean scope = false;
				if (storyScope.equals("이웃공개")) {
					scope = true;
				}
				vo = new StoryVO(storyCode,memberCode,homeCode,
						sotongCotentsCode,writenDate,storyHeart,updateDate,scope);
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
	
	public Date changeDate(String dateTime) {
		int year = Integer.parseInt(dateTime.substring(0,2));
		int mon = Integer.parseInt(dateTime.substring(3,5));
		int date = Integer.parseInt(dateTime.substring(6,8));
		int hour = Integer.parseInt(dateTime.substring(9,11));
		int min = Integer.parseInt(dateTime.substring(12,14));
		
		Date reDate = new Date(year,mon,date);
		reDate.setHours(hour);
		reDate.setMinutes(min);
		return reDate; 
	}
	
	public int incrementStoryHeart(String storyCode, int storyHeart) {
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "update story_tb set story_heart=? where story_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, storyHeart + 1);
			pstmt.setString(2, storyCode);
					
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
				storyHeart += 1;
			} else {
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
		return storyHeart;
	}
	
	public int decrementStoryHeart(String storyCode, int storyHeart) {
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "update story_tb set story_heart=? where story_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, storyHeart - 1);
			pstmt.setString(2, storyCode);
					
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
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
		return storyHeart - 1;
	}
	
	public static void main(String[] args) {
		StoryDAO dao = new StoryDAO();
		System.out.println(dao.updateStoryDate("story1", new Date()));
		//System.out.println(dao.insertMenu("cheal", "m2", "h2", "scontents3", new Date(), 50, new Date(), false));
		//System.out.println(dao.updateMenu("cheal", "m1", "h1", "scontents1", 10, new Date(), true));
		//System.out.println(dao.selectCode("cheal"));
		//System.out.println(dao.insertMenu("asd", "m1", "h1", "scontents3", new Date(), 30, new Date(), true));
		
	}
	public StoryViewVO[] getStoryList(String homeCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//이야기 공개 범위 수정 추가
	public int storyPublicScope(String storyCode, String scope ,String modifyDate) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "update story_tb set story_scope = ?, STORY_MODIFY_DATE=? where story_code = ?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, scope);
			pstmt.setString(2, modifyDate);
			pstmt.setString(3, storyCode);
			
			rowNum = pstmt.executeUpdate();
			if (rowNum != 0) {
				conn.commit();
			} else {
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
}
