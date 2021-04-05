package dao.diary;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import dao.DBConnectionModule;

public class DiaryViewDAO implements Serializable{
	
	private static final long serialVersionUID = -9122893129583102936L;
	private Connection conn;
	
	public DiaryViewDAO() {
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	
	public DiaryViewVO selectDiaryInfo(String diaryCode)
	{
		DiaryViewVO res = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from diary_view where diary_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, diaryCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String memberNickname = rs.getString("member_nickname");
				String diaryTitle = rs.getString("diary_title");
				String diaryDate = rs.getString("diary_date");
				String sotongContentsCode = rs.getString("sotong_contents_code");
				String contents = rs.getString("contents");
				String imageName = rs.getString("image_name");
				String imageWrittenDate = rs.getString("image_written_date");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
								
				res = new DiaryViewVO(diaryCode,memberNickname,diaryTitle,diaryDate,sotongContentsCode,contents,imageName,imageWrittenDate,emoticonName,emoticonRoute);
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
		
		return res;
	}

	public static void main(String[] args)
	{
		DiaryViewDAO dao= new DiaryViewDAO();
		
		DiaryViewVO res = dao.selectDiaryInfo("d1");
		
		System.out.println(res);
		
	}
}
