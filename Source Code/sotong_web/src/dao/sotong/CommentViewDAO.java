package dao.sotong;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DBConnectionModule;

public class CommentViewDAO implements Serializable{

	private static final long serialVersionUID = -3274720077295461461L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public CommentViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public CommentViewDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	// 여기서 부터 Manager 클래스 추출 후 추가
	
	public CommentViewVO[] getStoryCommentList(String storyCode) {
		
		ArrayList<CommentViewVO> list = new ArrayList<CommentViewVO>();
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from comment_view where STORY_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, storyCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String commentCode = rs.getString("COMMENT_CODE");
				String commentContents = rs.getString("COMMENT_CONTENTS");
				
				String memberNickname = rs.getString("member_nickname");
				String memberPhoto = rs.getString("member_photo");
				String memberColor = rs.getString("member_color");
				
				String commentDate = rs.getString("comment_date");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				
				String nowDate = commentDate;
				
				
				
				CommentViewVO vo = new CommentViewVO(commentCode, commentContents,
						memberNickname, memberPhoto, memberColor,
						nowDate, emoticonName, null, storyCode, emoticonRoute);				
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
		
		return list.toArray(new CommentViewVO[list.size()]);
	}
	
	public CommentViewVO[] getFamilyDiaryCommentList(String familyDiaryCode) {
		
		ArrayList<CommentViewVO> list = new ArrayList<CommentViewVO>();
		PreparedStatement pstmt = null;
		
		try{
			String sql = "select * from comment_view where FAMILY_DIARY_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyDiaryCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String commentCode = rs.getString("COMMENT_CODE");
				String commentContents = rs.getString("COMMENT_CONTENTS");
				
				String memberNickname = rs.getString("member_nickname");
				String memberPhoto = rs.getString("member_photo");
				String memberColor = rs.getString("member_color");
				
				String commentDate = rs.getString("comment_date");
				String emoticonName = rs.getString("emoticon_name");
				
				String emoticonRoute = rs.getString("emoticon_route");
				
				String nowDate = commentDate;
				
				CommentViewVO vo = new CommentViewVO(commentCode, commentContents,
						memberNickname, memberPhoto, memberColor,
						nowDate, emoticonName, familyDiaryCode, null, emoticonRoute);				
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
		
		return list.toArray(new CommentViewVO[list.size()]);
	}
	
	public static void main(String[] args) {
		CommentViewDAO dao = new CommentViewDAO();
		for (CommentViewVO vo : dao.getFamilyDiaryCommentList("fdc1")) {
			System.out.println(vo);
			System.out.println("===2===");
		}
		
		for (CommentViewVO vo : dao.getStoryCommentList("story1")) {
			System.out.println(vo);
			System.out.println("===1===");
		}
	}	
}
