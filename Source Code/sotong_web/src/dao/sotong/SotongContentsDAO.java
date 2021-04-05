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

public class SotongContentsDAO implements Serializable{

	private static final long serialVersionUID = 3393120461170380746L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public SotongContentsDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeSotongCode(){ //임시코드
		GregorianCalendar cal = new GregorianCalendar();
		
		String sotongCode = "SC21" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return sotongCode;
	}
	
	public int insertSotongContents(String familyHomeCode, String sotongCategoryCode, String contents, String imageCode, String emoticonCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into sotong_contents_tb values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeSotongCode());
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, sotongCategoryCode);
			pstmt.setString(4, contents);
			pstmt.setString(5, imageCode);
			pstmt.setString(6, emoticonCode);
								
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
		return rowNum;		//생성된 소통코드 반환. 안에서 rowNum으로 처리하는것 추가해야한다.
	}
	
	public String insertSotongContentsCode(String familyHomeCode, String sotongCategoryCode, String contents, String imageCode, String emoticonCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		String sotongCode = makeSotongCode();
		
		try{
			String sql = "insert into sotong_contents_tb values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongCode);
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, sotongCategoryCode);
			pstmt.setString(4, contents);
			pstmt.setString(5, imageCode);
			pstmt.setString(6, emoticonCode);
								
			System.out.println("sotongCode : " + sotongCode);
			System.out.println("familyHomeCode : " + familyHomeCode);
			System.out.println("sotongCategoryCode : " + sotongCategoryCode);
			System.out.println("contents : " + contents);
			System.out.println("imageCode : " + imageCode);
			System.out.println("emoticonCode" + emoticonCode);
			 
			
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
		return sotongCode;		//생성된 소통코드 반환. 안에서 rowNum으로 처리하는것 추가해야한다.
	}
	
	public int updateSotongContents(String sotongContentsCode, String contents, String imageCode, String emoticonCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			//String sql = "update sotong_contents_tb set family_home_code = ?, sotong_category_code=?, contents=?, image_code=?, emoticon_code=? where sotong_contents_code=?";
			String sql = "update sotong_contents_tb set contents=?, image_code=?, emoticon_code=? where sotong_contents_code=?";
			pstmt = conn.prepareStatement(sql);
			
			
			//pstmt.setString(1, familyHomeCode);
			//pstmt.setString(2, sotongCategoryCode);
			pstmt.setString(1, contents);
			pstmt.setString(2, imageCode);
			pstmt.setString(3, emoticonCode);
			pstmt.setString(4, sotongContentsCode);
					
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
	
	public int deleteSotongContents(String sotongContentsCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from sotong_contents_tb where sotong_contents_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sotongContentsCode);
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
	
	public SotongContentsVO selectSotongContents(String sotongContentsCode)
	{
		SotongContentsVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from sotong_contents_tb where sotong_contents_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sotongContentsCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyHomeCode = rs.getString("family_home_code");
				String sotongCategoryCode = rs.getString("sotong_category_code");
				String contents = rs.getString("contents");
				String imageCode = rs.getString("image_code");
				String emotionCode = rs.getString("emoticon_code");
							
				vo = new SotongContentsVO(sotongContentsCode,familyHomeCode,sotongCategoryCode,contents,imageCode,emotionCode);
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
	

	public SotongContentsVO[] selectSotongContentsByCategory(String sotongCategoryCode)
	{
		ArrayList<SotongContentsVO> voList = null;
		SotongContentsVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<SotongContentsVO>();
			String sql = "select * from sotong_contents_tb where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sotongCategoryCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String familyHomeCode = rs.getString("family_home_code");
				String contents = rs.getString("contents");
				String imageCode = rs.getString("image_code");
				String emotionCode = rs.getString("emoticon_code");
							
				vo = new SotongContentsVO(sotongContentsCode,familyHomeCode,sotongCategoryCode,contents,imageCode,emotionCode);
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
			return voList.toArray(new SotongContentsVO[voList.size()]);
		}
		else
		{
			return null;
		}
	}
	
	//추가 이야기 수정 하는 부분에서 추가
	public int updateContents(String sotongContentsCode, String contents) {
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			//String sql = "update sotong_contents_tb set family_home_code = ?, sotong_category_code=?, contents=?, image_code=?, emoticon_code=? where sotong_contents_code=?";
			String sql = "update sotong_contents_tb set contents=? where sotong_contents_code=?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, contents);
			pstmt.setString(2, sotongContentsCode);
					
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
	//태영 수정.앨범 관련. 홈 코드 이용해서 소통 내용 목록 가져오기.
	public SotongContentsVO[] selectSotongContentsByHomeCode(String homeCode)
	{
		ArrayList<SotongContentsVO> voList = null;
		SotongContentsVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<SotongContentsVO>();
			String sql = "select * from sotong_contents_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String familyHomeCode = rs.getString("family_home_code");
				String sotongCategoryCode=rs.getString("SOTONG_CATEGORY_CODE");	
				String contents = rs.getString("contents");
				String imageCode = rs.getString("image_code");
				String emotionCode = rs.getString("emoticon_code");
						
				vo = new SotongContentsVO(sotongContentsCode,familyHomeCode,sotongCategoryCode,contents,imageCode,emotionCode);
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
			return voList.toArray(new SotongContentsVO[voList.size()]);
		}
		else
		{
			return null;
		}
	}
	public static void main(String[] args)
	{
		SotongContentsDAO dao = new SotongContentsDAO();
		System.out.println("삽입 : " + dao.insertSotongContents("h1", "sc2", "내요오오오옹", "i1", "em1"));
		System.out.println("조회 : " + dao.selectSotongContents("scontents1"));
	//	System.out.println("수정 : " + dao.updateSotongContents("scontents8", "h2", "sc4", "수정내요오옹", "i2", "em2"));
		System.out.println("삭제 : " + dao.deleteSotongContents("scontents4"));
		SotongContentsVO[] volist = dao.selectSotongContentsByCategory("sc4");		
		System.out.println("카테고리 조회 : ");
		for(int i=0;i<volist.length;i++)
		{
			System.out.println(volist[i]);
		}
	}
}
