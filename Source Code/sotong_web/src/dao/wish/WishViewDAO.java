package dao.wish;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DBConnectionModule;

public class WishViewDAO implements Serializable{

private static final long serialVersionUID = 1388877351313512271L;
private DBConnectionModule connModule;
private Connection conn;

public WishViewDAO()
{
	connModule=DBConnectionModule.getInstance();
	conn=connModule.getConn();
}
public WishViewVO select(String wishCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	WishViewVO viewVO=null;
	try
	{
		
		String sql="SELECT * FROM wish_view where wish_CODE=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, wishCode);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		viewVO=new WishViewVO();
		
		viewVO.setWishCode(rs.getString("wish_code"));
		viewVO.setMemberNickName(rs.getString("member_nickname"));
		viewVO.setFamilyHomeCode(rs.getString("family_home_code"));
		viewVO.setSotongContentsCode(rs.getString("sotong_contents_code"));
		viewVO.setContents(rs.getString("contents"));
		viewVO.setEmoticonName(rs.getString("emoticon_name"));
		viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
		viewVO.setImageName(rs.getString("image_name"));	
		viewVO.setImageWrittenDate(changeDate(rs.getString("image_written_date")));		
		viewVO.setWishTitle(rs.getString("wish_title"));	
		viewVO.setWishDate(changeDate(rs.getString("wish_date")));
		viewVO.setWishEndDate(changeDate(rs.getString("wish_end_date")));

		String isFinished=rs.getString("wish_finish");
		if(isFinished.equals("완료"))
		{
			viewVO.setWishFinish((byte)1);
		}
		else
		{
			viewVO.setWishFinish((byte)0);
		}
		
		//System.out.println(year+"/"+month+"/"+day);
		
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return viewVO;
			
}
public String[][] getOnGoingSimpleWishList(String homeCode)
{
	String[][] simpleListes=null;
	String[] list=null;;
	String title=null, date=null;
	PreparedStatement pstmt=null;
	int cnt=0;
	try
	{
		
		String sql="SELECT wish_title,wish_date FROM wish_view where FAMILY_HOME_CODE=? AND wish_finish=\'진행중\'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, homeCode);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			cnt++;
		}
		rs=pstmt.executeQuery();
		simpleListes=new String[cnt][];
		cnt=0;
		while(rs.next())
		{
			title=rs.getString("wish_title");
			date=rs.getString("wish_date");
			list=new String[]{title,date};
			simpleListes[cnt]=list;
			cnt++;
		}
		
		
			
		
		
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return simpleListes;
}


public List<WishListViewVO> selectByFamilyWeb(String familyCode, ArrayList<String[]> wishTitle)
{
	
	
	PreparedStatement pstmt = null;
	List<WishListViewVO> list = new ArrayList<WishListViewVO>();
	WishListViewVO viewVO = null;
	
	System.out.println("=-=-=--==-=--=---=-=-=--=-=-=-=-=-=-=-=-=-=-");
	System.out.println("WishViewDAO FamilyHomCode : " + familyCode);
	System.out.println("=-=-=--==-=--=---=-=-=--=-=-=-=-=-=-=-=-=-=-");
	try
	{
		
		String sql = "SELECT * FROM wish_view where FAMILY_HOME_CODE = ?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, familyCode);
		
		ResultSet rs = pstmt.executeQuery();
		
		String wishCode = null;
		String wishTitleContents = null;
		String wishDate = null;
		while(rs.next())
		{
			wishCode = rs.getString("wish_code");
			wishTitleContents = rs.getString("wish_title");
			wishDate = rs.getString("wish_date");
			
			viewVO=new WishListViewVO();
			viewVO.setWishCode(wishCode);
			viewVO.setMemberNickName(rs.getString("member_nickname"));
			viewVO.setFamilyHomeCode(rs.getString("family_home_code"));
			viewVO.setSotongContentsCode(rs.getString("sotong_contents_code"));
			viewVO.setContents(rs.getString("contents"));
			viewVO.setEmoticonName(rs.getString("emoticon_name"));
			viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
			viewVO.setImageName(rs.getString("image_name"));		
			viewVO.setImageWrittenDate(rs.getString("image_written_date"));
			
			viewVO.setWishTitle(wishTitleContents);
			viewVO.setWishDate(wishDate);
		
			viewVO.setWishEndDate(rs.getString("wish_end_date"));
			
			viewVO.setWishFinish(rs.getString("wish_finish"));
			System.out.println("위시 코드 : " + wishCode);
			System.out.println("위시 날짜 : " + wishDate);
			System.out.println("위시 제목 : " + wishTitleContents);
			list.add(viewVO);
			String[] arr = new String[]{ wishCode, wishDate, wishTitleContents };
			wishTitle.add(arr);
			
		}
		
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return list;
			
}


public String[][] getFinishedSimpleWishList(String homeCode)
{
	String[][] simpleListes=null;
	String[] list=null;;
	String title=null, date=null;
	PreparedStatement pstmt=null;
	int cnt=0;
	try
	{
		
		String sql="SELECT wish_title,wish_date FROM wish_view where FAMILY_HOME_CODE=? AND wish_finish=\'완료\'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, homeCode);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
			cnt++;
		}
		rs=pstmt.executeQuery();
		simpleListes=new String[cnt][];
		cnt=0;
		while(rs.next())
		{
			title=rs.getString("wish_title");
			date=rs.getString("wish_date");
			list=new String[]{title,date};
			simpleListes[cnt]=list;
			cnt++;
		}
		
		
			
		
		
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return simpleListes;
}
public List<WishViewVO> selectByFamily(String familyCode)
{
	
	
	PreparedStatement pstmt=null;
	List<WishViewVO> list=new ArrayList<WishViewVO>();
	WishViewVO viewVO=null;
	try
	{
		
		String sql="SELECT * FROM wish_view where FAMILY_HOME_CODE=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, familyCode);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next())
		{
		viewVO=new WishViewVO();
		viewVO.setWishCode(rs.getString("wish_code"));
		viewVO.setMemberNickName(rs.getString("member_nickname"));
		viewVO.setFamilyHomeCode(rs.getString("family_home_code"));
		viewVO.setSotongContentsCode(rs.getString("sotong_contents_code"));
		viewVO.setContents(rs.getString("contents"));
		viewVO.setEmoticonName(rs.getString("emoticon_name"));
		viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
		viewVO.setImageName(rs.getString("image_name"));		
		viewVO.setImageWrittenDate(changeDate(rs.getString("image_written_date")));
		
		viewVO.setWishTitle(rs.getString("wish_title"));
		viewVO.setWishDate(changeDate(rs.getString("wish_date")));
	
		viewVO.setWishEndDate(changeDate(rs.getString("wish_end_date")));
		
		String isFinished=rs.getString("wish_finish");
		if(isFinished.equals("완료"))
		{
			viewVO.setWishFinish((byte)1);
		}
		else
		{
			viewVO.setWishFinish((byte)0);
		}
		
		//System.out.println(year+"/"+month+"/"+day);
		list.add(viewVO);
		
		}
		
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return list;
			
}
public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
    String date = fmt.format(d);
    return date;
}
	

public Date changeDate(String date) {
	Date dt = null;
	try {
		dt = new SimpleDateFormat("yy-MM-dd").parse(date);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dt;

}

public List<WishListViewVO> selectWishing(String familyCode, ArrayList<String[]> wishTitle)
{
   
   
   PreparedStatement pstmt = null;
   List<WishListViewVO> list = new ArrayList<WishListViewVO>();
   WishListViewVO viewVO = null;
   
   try
   {
      
      String sql = "SELECT * FROM wish_view where FAMILY_HOME_CODE = ? and WISH_FINISH='진행중'";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, familyCode);
      
      ResultSet rs = pstmt.executeQuery();
      
      String wishCode = null;
      String wishTitleContents = null;
      String wishDate = null;
      while(rs.next())
      {
         wishCode = rs.getString("wish_code");
         wishTitleContents = rs.getString("wish_title");
         wishDate = rs.getString("wish_date");
         
         viewVO=new WishListViewVO();
         viewVO.setWishCode(wishCode);
         viewVO.setMemberNickName(rs.getString("member_nickname"));
         viewVO.setFamilyHomeCode(rs.getString("family_home_code"));
         viewVO.setSotongContentsCode(rs.getString("sotong_contents_code"));
         viewVO.setContents(rs.getString("contents"));
         viewVO.setEmoticonName(rs.getString("emoticon_name"));
         viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
         viewVO.setImageName(rs.getString("image_name"));      
         viewVO.setImageWrittenDate(rs.getString("image_written_date"));
         
         viewVO.setWishTitle(wishTitleContents);
         viewVO.setWishDate(wishDate);
      
         viewVO.setWishEndDate(rs.getString("wish_end_date"));
         
         viewVO.setWishFinish(rs.getString("wish_finish"));
         System.out.println("위시 코드 : " + wishCode);
         System.out.println("위시 날짜 : " + wishDate);
         System.out.println("위시 제목 : " + wishTitleContents);
         list.add(viewVO);
         String[] arr = new String[]{ wishCode, wishDate, wishTitleContents };
         wishTitle.add(arr);
         
      }
      
   }
   catch(SQLException se)
   {
      se.printStackTrace();
   }
   catch(Exception e)
   {
      e.printStackTrace();
   }
   finally
   {
      try
      {
         if(pstmt!=null)
         {
            pstmt.close();
         }
      }
      catch(SQLException se)
      {
         se.printStackTrace();
      }
   }
   
   return list;
         
}

public List<WishListViewVO> selectFinishWish(String familyCode, ArrayList<String[]> wishTitle)
{
   
   
   PreparedStatement pstmt = null;
   List<WishListViewVO> list = new ArrayList<WishListViewVO>();
   WishListViewVO viewVO = null;
   
   try
   {
      
      String sql = "SELECT * FROM wish_view where FAMILY_HOME_CODE = ? and WISH_FINISH='완료'";
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, familyCode);
      
      ResultSet rs = pstmt.executeQuery();
      
      String wishCode = null;
      String wishTitleContents = null;
      String wishDate = null;
      while(rs.next())
      {
         wishCode = rs.getString("wish_code");
         wishTitleContents = rs.getString("wish_title");
         wishDate = rs.getString("wish_date");
         
         viewVO=new WishListViewVO();
         viewVO.setWishCode(wishCode);
         viewVO.setMemberNickName(rs.getString("member_nickname"));
         viewVO.setFamilyHomeCode(rs.getString("family_home_code"));
         viewVO.setSotongContentsCode(rs.getString("sotong_contents_code"));
         viewVO.setContents(rs.getString("contents"));
         viewVO.setEmoticonName(rs.getString("emoticon_name"));
         viewVO.setEmoticonRoute(rs.getString("emoticon_route"));
         viewVO.setImageName(rs.getString("image_name"));      
         viewVO.setImageWrittenDate(rs.getString("image_written_date"));
         
         viewVO.setWishTitle(wishTitleContents);
         viewVO.setWishDate(wishDate);
      
         viewVO.setWishEndDate(rs.getString("wish_end_date"));
         
         viewVO.setWishFinish(rs.getString("wish_finish"));
         System.out.println("위시 코드 : " + wishCode);
         System.out.println("위시 날짜 : " + wishDate);
         System.out.println("위시 제목 : " + wishTitleContents);
         list.add(viewVO);
         String[] arr = new String[]{ wishCode, wishDate, wishTitleContents };
         wishTitle.add(arr);
         
      }
      
   }
   catch(SQLException se)
   {
      se.printStackTrace();
   }
   catch(Exception e)
   {
      e.printStackTrace();
   }
   finally
   {
      try
      {
         if(pstmt!=null)
         {
            pstmt.close();
         }
      }
      catch(SQLException se)
      {
         se.printStackTrace();
      }
   }
   
   return list;
         
}



public static void main(String args[])
{
	WishViewDAO dao=new WishViewDAO();
	//dao.delete("201563202230");
	WishViewVO view=dao.select("w2");
	//dao.update("15113193819", dao.select("l2"));
	/*System.out.println(view);
	System.out.println(view.getImageWrittenDate().getYear()+"/"+view.getImageWrittenDate().getMonth()+"/"+view.getImageWrittenDate().getDate()+"/"+view.getImageWrittenDate().getDay());
	System.out.println(view.getWishDate().getYear()+"/"+view.getWishDate().getMonth()+"/"+view.getWishDate().getDate()+"/"+view.getWishDate().getDay());
	System.out.println(view.getWishEndDate().getYear()+"/"+view.getWishEndDate().getMonth()+"/"+view.getWishEndDate().getDate()+"/"+view.getWishEndDate().getDay());
	*//*List list=dao.selectByFamily("h3");
	for(int i=0;i<list.size();i++)
	{
		System.out.println(list.get(i));
	}*/
	/*for(int i=0;i<dao.getOnGoingSimpleWishList("h2").length;i++)
	{
		System.out.println((dao.getOnGoingSimpleWishList("h2"))[i][0]);
		System.out.println((dao.getOnGoingSimpleWishList("h2"))[i][1]);
	}*/
	/*for(int i=0;i<dao.getFinishedSimpleWishList("h1").length;i++)
	{
		System.out.println((dao.getFinishedSimpleWishList("h1"))[i][0]);
		System.out.println((dao.getFinishedSimpleWishList("h1"))[i][1]);
	}*/
	/*List<WishViewVO> familyWish=dao.selectByFamily("h3");
	for(int i=0;i<familyWish.size();i++)
	{
		System.out.println(familyWish.get(i));
	}
*/
	System.out.println(dao.select("w1"));
}
}

