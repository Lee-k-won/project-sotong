package dao.diary;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.DBConnectionModule;


public class FamilyDiaryViewDAO implements Serializable{

	private static final long serialVersionUID = -572942236513831549L;
	private Connection conn;
	
	public FamilyDiaryViewDAO() {
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	
	public FamilyDiaryViewVO[] selectDiaryInfo(String familyDiaryCode)
	{
		ArrayList<FamilyDiaryViewVO> list = new ArrayList<FamilyDiaryViewVO>();
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_view where family_diary_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyHomeCode = rs.getString("family_home_code");
				String familyDiaryDate = rs.getString("family_diary_date");
				String familyDiaryPartCode = rs.getString("family_diary_part_code");
				String memberNickname = rs.getString("member_nickname");
				String memberPhoto = rs.getString("member_photo");
				String familyDiaryPartDate = rs.getString("family_diary_part_date");
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String contents = rs.getString("contents");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String imageName = rs.getString("image_name");
				String imageWrittenDate = rs.getString("image_written_date");
								
				list.add(new FamilyDiaryViewVO(familyDiaryCode,familyHomeCode,familyDiaryDate,familyDiaryPartCode,memberNickname,memberPhoto,familyDiaryPartDate,sotongContentsCode,contents,emoticonName,emoticonRoute,imageName,imageWrittenDate));
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return list.toArray(new FamilyDiaryViewVO[list.size()]);
	}
	
	public FamilyDiaryViewVO selectDiaryPartInfo(String familyDiaryPartCode)
	{
		FamilyDiaryViewVO vo = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_view where family_diary_part_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryPartCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				String familyHomeCode = rs.getString("family_home_code");
				String familyDiaryDate = rs.getString("family_diary_date");
				String memberNickname = rs.getString("member_nickname");
				String memberPhoto = rs.getString("member_photo");
				String familyDiaryPartDate = rs.getString("family_diary_part_date");
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String contents = rs.getString("contents");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String imageName = rs.getString("image_name");
				String imageWrittenDate = rs.getString("image_written_date");
								
				vo = new FamilyDiaryViewVO(familyDiaryCode,familyHomeCode,familyDiaryDate,familyDiaryPartCode,memberNickname,memberPhoto,familyDiaryPartDate,sotongContentsCode,contents,emoticonName,emoticonRoute,imageName,imageWrittenDate);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public Date dateCut(String date)
	{
		 Integer year = new Integer(date.substring(0, 2));
		 Integer month = new Integer(date.substring(3, 5));
		 Integer day = new Integer(date.substring(6, 8));
		 
		 return new Date(year+100,month-1,day);
	}
	
	public static void main(String[] args)
	{
		FamilyDiaryViewDAO dao= new FamilyDiaryViewDAO();
		
		FamilyDiaryViewVO[] res = dao.selectDiaryInfo("fdc1");
		
		for(FamilyDiaryViewVO temp : res)
		{
			System.out.println(temp);
		}
		
	}
}
