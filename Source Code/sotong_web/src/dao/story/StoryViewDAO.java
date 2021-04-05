package dao.story;

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

public class StoryViewDAO implements Serializable{

	private static final long serialVersionUID = 4399317021029198850L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public StoryViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public StoryViewDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public StoryViewVO selectCode(String code){
		StoryViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from story_view where story_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomeCode = rs.getString("family_home_code");
				String familyHomeName = rs.getString("family_home_name");
				
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberColor = rs.getString("member_color");
				
				
				String memberNickname = rs.getString("member_nickname");
				String contents = rs.getString("contents");
				String contentsCode = rs.getString("SOTONG_CONTENTS_CODE");
				String imageName = rs.getString("image_name");
				String imageWritenDate = rs.getString("image_writen_date");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String storyDate = rs.getString("story_date");
				int stroyHeart = rs.getInt("stroy_heart");
				String storyModifyDate = rs.getString("story_modify_date");
				String storyScope = rs.getString("story_scope");
				
				vo = new StoryViewVO(code,familyHomeCode,familyHomeName, memberCode, memberPhoto, memberColor,
						memberNickname,contents, contentsCode, imageName,imageWritenDate,emoticonName,emoticonRoute,
						storyDate, stroyHeart,storyModifyDate,storyScope);
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
	      
	      Date reDate = new Date(year,mon,date);
	      return reDate; 
	}
	
	public StoryViewVO[] getStoryList(String homeCode) {
		
		ArrayList<StoryViewVO> list = new ArrayList<StoryViewVO>();
		
		
		StoryViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from story_view where FAMILY_HOME_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String storyCode = rs.getString("story_code");
				String familyHomeName = rs.getString("family_home_name");
				
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberColor = rs.getString("member_color");
				
				String memberNickname = rs.getString("member_nickname");
				String contents = rs.getString("contents");
				String contentsCode = rs.getString("SOTONG_CONTENTS_CODE");
				String imageName = rs.getString("image_name");
				String imageWritenDate = rs.getString("IMAGE_WRITTEN_DATE");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String storyDate = rs.getString("story_date");
				int stroyHeart = rs.getInt("STORY_HEART");
				String storyModifyDate = rs.getString("story_modify_date");
				String storyScope = rs.getString("story_scope");
				
				
				vo = new StoryViewVO(storyCode,homeCode,familyHomeName, memberCode, memberPhoto, memberColor,
						memberNickname,contents, contentsCode, imageName,imageWritenDate,emoticonName,
						emoticonRoute,storyDate,stroyHeart,storyModifyDate,storyScope);
				list.add(vo);
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
		
		return list.toArray(new StoryViewVO[list.size()]);
	}
	
	public void getNeighborStoryList(String homeCode, ArrayList<StoryViewVO> neighborStoryList) {		
		
		StoryViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from story_view where FAMILY_HOME_CODE=? and STORY_SCOPE=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			pstmt.setString(2, "이웃");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String storyCode = rs.getString("story_code");
				String familyHomeName = rs.getString("family_home_name");
				
				String memberCode = rs.getString("member_code");
				String memberPhoto = rs.getString("member_photo");
				String memberColor = rs.getString("member_color");
				
				String memberNickname = rs.getString("member_nickname");
				String contents = rs.getString("contents");
				String contentsCode = rs.getString("SOTONG_CONTENTS_CODE");
				String imageName = rs.getString("image_name");
				String imageWritenDate = rs.getString("IMAGE_WRITTEN_DATE");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String storyDate = rs.getString("story_date");
				int stroyHeart = rs.getInt("STORY_HEART");
				String storyModifyDate = rs.getString("story_modify_date");
				String storyScope = rs.getString("story_scope");
				
				
				vo = new StoryViewVO(storyCode,homeCode,familyHomeName, memberCode, memberPhoto, memberColor,
						memberNickname,contents, contentsCode, imageName,imageWritenDate,emoticonName,
						emoticonRoute,storyDate,stroyHeart,storyModifyDate,storyScope);
				neighborStoryList.add(vo);
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
	}



	//매니저 구현 단계에서 추가 
	public String getSotongContentsCode(String storyCode) {
		PreparedStatement pstmt = null;
		String contents = null;
		try{
			String sql = "select SOTONG_CONTENTS_CODE from story_view where story_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storyCode);	
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()) {
				contents = rs.getString("SOTONG_CONTENTS_CODE");				
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
		return contents;
	}
	
	//매니저 구현 단계에서 추가 
	public String storyIndexCode(String homeCode, int storyIndex) {
		PreparedStatement pstmt = null;		
		String storyCode = null;
		try{
			String sql = "select story_code from story_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			int rowNum = 0;
			
			while(rs.next()){
				if (rowNum == storyIndex) {
					System.out.println("===============+==============");
					System.out.println("rowNum : " + rowNum + "\nstoryIndex : " + storyIndex);
					storyCode = rs.getString("story_code");
					System.out.println("storyCode : " + storyCode);
					break;
					
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
		return storyCode;
	}
	
	
	
	//배열 순서 뒤 바꿔주는 메소드 추가
	public StoryViewVO[] arraySort(ArrayList<StoryViewVO> list) {
		int size = list.size();
		
		ArrayList<StoryViewVO> sortList = new ArrayList<StoryViewVO>();
		
		for (StoryViewVO vo : list) {
			size--;
			sortList.add(list.get(size));
		}
		return sortList.toArray(new StoryViewVO[sortList.size()]);
	}
	
	public static void main(String[] args) {
		StoryViewDAO dao = new StoryViewDAO();
		//System.out.println(dao.selectCode("cheal"));
		
		for (StoryViewVO vo : dao.getStoryList("h1")) {
			System.out.println(vo);
		}
	}
}
