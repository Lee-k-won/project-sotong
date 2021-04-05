package dao.sotong;

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

public class EmoticonDAO implements Serializable{

	private static final long serialVersionUID = 4900314399848899713L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public EmoticonDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeEmoticonCode(){ //임시코드
		GregorianCalendar cal = new GregorianCalendar();
		
		String emoticonCode = "ED17" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return emoticonCode;
	}
	
	public int insertEmoticon(String emoticonName, String emoticonRoute, String emoticonCategoryCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into Emoticon_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeEmoticonCode());
			pstmt.setString(2, emoticonName);
			pstmt.setString(3, emoticonRoute);
			pstmt.setString(4, emoticonCategoryCode);
						
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
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
	
	/**
	 * 이모티콘 식별코드로 이모티콘 검색해서 수정
	 * @param emoticonCode 이모티콘 식별코드
	 * @param emoticonName 이모티콘 이름
	 * @param emoticonRoute 이모티콘 경로
	 * @param emoticonCategoryCode 이모티콘 카테고리 코드
	 * @return 정수형, 성공여부
	 */
	public int updateEmoticon(String emoticonCode, String emoticonName, String emoticonRoute, String emoticonCategoryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update emoticon_tb set emoticon_name=?, emoticon_route=?, emoticon_category_code=? where emoticon_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emoticonName);
			pstmt.setString(2,emoticonRoute);
			pstmt.setString(3,emoticonCategoryCode);
			pstmt.setString(4,emoticonCode);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
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
	
	
	/**
	 * 이모티콘 이름으로 검색해서 이모티콘 정보 수정
	 * @param emoticonName 이모티콘 이름
	 * @param newEmoticonName 수정될 이모티콘 이름
	 * @param emoticonRoute 수정될 이모티콘 경로
	 * @param emoticonCategoryCode 수정될 이모티콘 카테고리 
	 * @return 정수형, 성공여부
	 */
	public int updateEmoticonByEmoticonName(String emoticonName, String newEmoticonName, String emoticonRoute, String emoticonCategoryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update emoticon_tb set emoticon_name=?, emoticon_route=?, emoticon_category_code=? where emoticon_name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newEmoticonName);
			pstmt.setString(2,emoticonRoute);
			pstmt.setString(3,emoticonCategoryCode);
			pstmt.setString(4,emoticonName);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
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
	
	/**
	 * 이모티콘 코드로 검색해서 삭제
	 * @param emoticonCode 삭제할 이모티콘 코드
	 * @return 정수형, 성공여부
	 */
	public int deleteEmoticon(String emoticonCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from emoticon_tb where emoticon_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emoticonCode);
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
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
	
	
	/**
	 * 이모티콘 이름으로 검색하여 삭제
	 * @param emoticonName 삭제할 이모티콘 이름
	 * @return 정수형, 성공여부
	 */
	public int deleteEmoticonByEmoticonName(String emoticonName){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from emoticon_tb where emoticon_name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emoticonName);
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
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
	
	/**
	 * 이모티콘 코드로 이모티콘VO 얻어오기
	 * @param emoticonCode 조회할 이모티콘 코드
	 * @return 조회 결과 얻어온 VO객체
	 */
	public EmoticonVO selectEmoticon(String emoticonCode)
	{
		EmoticonVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from emoticon_tb where emoticon_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emoticonCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String emoticonCategoryCode = rs.getString("emoticon_category_code");
		
				vo = new EmoticonVO(emoticonCode,emoticonName,emoticonRoute,emoticonCategoryCode);
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
	
	/**
	 * @param emoticonName 조회할 이모티콘 이름
	 * @return 조회 검색 결과 VO 객체
	 */
	public EmoticonVO selectEmoticonByEmoticonName(String emoticonName)
	{
		EmoticonVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from emoticon_tb where emoticon_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emoticonName);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String emoticonCode = rs.getString("emoticon_code");
				String emoticonRoute = rs.getString("emoticon_route");
				String emoticonCategoryCode = rs.getString("emoticon_category_code");
		
				vo = new EmoticonVO(emoticonCode,emoticonName,emoticonRoute,emoticonCategoryCode);
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
	public EmoticonVO[] selectEmoticonByCategory(String emoticonCategoryCode)
	{
		ArrayList<EmoticonVO> voList = null;
		EmoticonVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<EmoticonVO>();
			String sql = "select * from emoticon_tb where emoticon_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emoticonCategoryCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
					
				String emoticonName = rs.getString("emoticon_name");
				String emoticonCode = rs.getString("emoticon_code");
				String emoticonRoute = rs.getString("emoticon_route");
								
				vo = new EmoticonVO(emoticonCode,emoticonName,emoticonRoute,emoticonCategoryCode);
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
			return voList.toArray(new EmoticonVO[voList.size()]);
		}
		else
		{
			return null;
		}
	}
	
	
	public static void main(String[] args)
	{
		EmoticonDAO dao = new EmoticonDAO();
//		System.out.println("삽입 : " +dao.insertEmoticon("안녕", "emoticon/anbu/123.jpg", "ec1"));
//		System.out.println("수정 : " +dao.updateEmoticon("em2", "삐짐", "emoticon/sad/TOT.png", "ec2"));
//		System.out.println("조회 : " + dao.selectEmoticonByEmoticonName("삐짐"));
//		System.out.println("수정 - 이모티콘 이름 : " + dao.updateEmoticonByEmoticonName("축하해", "축하", "emoticon/celebration/1.png", "ec4"));
//		System.out.println("조회 - 이모티콘 이름 : " + dao.selectEmoticonByEmoticonName("안녕"));
//		System.out.println("삭제 : " + dao.deleteEmoticon("6000301571044"));
//		System.out.println("삭제 - 이모티콘 이름 : "+dao.deleteEmoticonByEmoticonName("하트"));
		
//		EmoticonVO[] volist = dao.selectEmoticonByCategory("ec4");
//		System.out.println("카테고리 조회 : ");
//		for(int i=0;i<volist.length;i++)
//		{
//			System.out.println(volist[i]);
//		}
//		
	}
	
}
