package manager.wish;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manager.SotongManager;
import dao.wish.WishDAO;
import dao.wish.WishListViewVO;
import dao.wish.WishVO;
import dao.wish.WishViewDAO;
import dao.wish.WishViewVO;
public class WishManager {
	private WishViewDAO wishViewDAO;
	private WishDAO wishDAO;
	
	public WishManager()
	{
		wishViewDAO=new WishViewDAO();
		wishDAO=new WishDAO();
	}
	public WishViewVO[] getWishList(String homeCode)
	{
		List<WishViewVO> list=wishViewDAO.selectByFamily(homeCode);
		WishViewVO[] vo=list.toArray(new WishViewVO[list.size()]);
		if(vo.length == 0)
		{
			return null;
		}
		else
		{
			return vo;
		}
		
	}
	public int addWish(String homeCode,String memberCode,String wishTitle, String contents,String emoticonCode,String imageName,Date wishWrittenDate,Date wishEndDate)
	{
		SotongManager manager=new SotongManager();
		String sotongContentsCode=manager.addSotongContents(homeCode, "sc4", contents, imageName, changeDateToString(wishWrittenDate), emoticonCode);
		int res=wishDAO.insertWish(sotongContentsCode, memberCode, wishTitle, wishWrittenDate, wishEndDate, (byte)0);
		return res;
	}
	public int deleteWish(String wishCode)
	{
		String sotongContentsCode=wishDAO.selectWish(wishCode).getSotongContentsCode();
		int res=wishDAO.deleteWish(wishCode);
		if(res!=0)
		{
			SotongManager manager=new SotongManager();
			int res2=manager.deleteSotongContents(sotongContentsCode);
			if(res2!=0)
			{
				return 1;
			}
			else 
				return 0;
		}
		else 
			return 0;
	}
	public int updateWish(String wishCode,String homeCode,String memberCode,String wishTitle, String contents,String emoticonCode,String imageName,Date wishWrittenDate,Date wishEndDate)
	{
		int res=0;
		WishVO vo=wishDAO.selectWish(wishCode);
		SotongManager manager=new SotongManager();
		String sotongContentsCode=vo.getSotongContentsCode();
		manager.updateSotongContents(sotongContentsCode, contents, imageName, changeDateToString(wishWrittenDate), emoticonCode);
		res=wishDAO.updateWish(wishCode, sotongContentsCode, memberCode, wishTitle, wishWrittenDate, wishEndDate, vo.getWishFinish());
		
		return res;
	}
	public int finishWish(String wishCode, Date finishDate)
	{	
		WishVO vo=wishDAO.selectWish(wishCode);
		
		if(vo.getWishFinish()==(byte)1)
		{
			return -1;
		}
		Date wishEndDate=vo.getWishEndDate();
		if(wishEndDate.compareTo(finishDate)>0)
		{
			return wishDAO.finishWish(wishCode,finishDate);
		}
		else
		{
			return wishDAO.finishWish(wishCode,wishEndDate);
		}
		
	}
	public Date changeStringToDate(String date) {
		Date dt = null;
		try {
			dt = new SimpleDateFormat("yy-MM-dd").parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;

	}
	//추가
	public WishListViewVO[] getWishListWeb(String homeCode, ArrayList<String[]> wishTitle)
	{
		List<WishListViewVO> list = wishViewDAO.selectByFamilyWeb(homeCode, wishTitle);
		WishListViewVO[] vo = list.toArray(new WishListViewVO[list.size()]);
		if(vo.length == 0)
		{
			return null;
		}
		else
		{
			return vo;
		}
		
	}
	
	public WishListViewVO[] getWishing(String homeCode, ArrayList<String[]> wishTitle)
	   {
	      List<WishListViewVO> list = wishViewDAO.selectWishing(homeCode, wishTitle);
	      WishListViewVO[] vo = list.toArray(new WishListViewVO[list.size()]);
	      if(vo.length == 0)
	      {
	         return null;
	      }
	      else
	      {
	         return vo;
	      }
	      
	   }
	   
	   public WishListViewVO[] getFinishWish(String homeCode, ArrayList<String[]> wishTitle)
	   {
	      List<WishListViewVO> list = wishViewDAO.selectFinishWish(homeCode, wishTitle);
	      WishListViewVO[] vo = list.toArray(new WishListViewVO[list.size()]);
	      if(vo.length == 0)
	      {
	         return null;
	      }
	      else
	      {
	         return vo;
	      }
	      
	   }
	
		
	public String changeDateToString(Date date) {
		return new SimpleDateFormat("yy-MM-dd").format(date);
	}
	public static void main(String args[])
	{
		WishManager manager=new WishManager();
		/*WishViewVO[] vo=manager.getWishList("h3");*/
		/*for(int i=0;i<vo.length;i++)
		{
			System.out.println(vo[i]);
		}*/
		//manager.addWish("h1", "m1", "위시위시하게해줌", "내 소망은 이 프로그램이 끝나는거임", "em1", "images/wish/pic7", manager.changeStringToDate("15-07-06"), manager.changeStringToDate("15-08-31"));
		/*manager.deleteWish("WD236158161429");*/
		//manager.updateWish("WD236158163430", "h1", "m1", "위시시르다", "좀 끝나라 시간은 없고", "em2", "images/wish/pic8", manager.changeStringToDate("15-08-06"), manager.changeStringToDate("15-09-26"));
		//manager.finishWish("WD236158163430",manager.changeStringToDate("15-08-24"));
	}
}
