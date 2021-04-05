package control.wish;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.wish.WishManager;
import dao.home.FamilyMemberVO;
import dao.wish.WishListViewVO;
import dao.wish.WishViewVO;

/**
 * Servlet implementation class WishServlet
 */
@WebServlet({ "/WishServlet", "/wish_add.do", "/wish_delete.do",
		"/wish_update.do", "/wish_finish.do","/wish.do","/move-wish-page","/wishing.do", "/wish-finishWish.do"})
public class WishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WishServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	      response.setCharacterEncoding("utf-8");   
	   
	      String uri = request.getRequestURI();
	      
	      int lastIndex = uri.lastIndexOf("/");
	      String action = uri.substring(lastIndex+1);
	      
	      RequestDispatcher rd = null;
	      
	      switch (action) {
	         case "move-wish-page" :
	            rd = request.getRequestDispatcher("JSP/wishList/WishListWrite.jsp");
	            rd.forward(request,  response);   
	            break;
	         case "wishing.do" :
	            requestWishing(request, response);
	            break;
	         case "wish-finishWish.do" :
	            requestFinishWishWeb(request, response);
	            break;
	         default :
	            doPost(request, response);
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		switch (action) {
			case "wish_add.do" :
				requestAddWish(request, response);
				break;
			case "wish_delete.do" :
				requestDeleteWish(request, response);
				break;
			case "wish_update.do" :
				requestUpdateWish(request, response);
				break;
			case "wish_finish.do" :
				requestFinishWish(request, response);
				break;
			case "wish.do" :
				requestWishList(request, response);
				break;
	}
		
	}

	public void requestWishList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		String homeCode=request.getParameter("homeCode");
		
		WishManager manager= new WishManager();
		WishViewVO[] wishList = manager.getWishList(homeCode);
		
		if (homeCode == null) {
			HttpSession session = request.getSession();
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			homeCode = userInfo.getFamilyHomecode();
		}
		
		
		String devide = request.getParameter("serviceRoute");
		if (devide == null) {
			devide = "2000";
		}
		
		System.out.println("devide : " + devide);
		
		
		
		int checkWish = 0; //
		
		WishListViewVO[] wishListView = null; //
		ArrayList<String[]> wishTitle = new ArrayList<String[]>();
		
		if (wishList == null) {	//
			wishListView = manager.getWishListWeb(homeCode, wishTitle);	//
		}
		
		System.out.println("权 内靛 : " + homeCode);
		for (String[] arr : wishTitle) {
			for (String str : arr)
				System.out.println(str);
		}
		
		System.out.println("=-=-=-==-=--=-=-=-=-=-=-==-=--=");
		
		if (wishList == null && wishListView == null) {	//
			checkWish = -1;	//	
		}
		
		if (checkWish != -1) {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				String str = "200|";
				String data = "";
				for (int i = 0; i < wishList.length; i++) {
						data = 	wishList[i].getWishCode()+"|"			
								+wishList[i].getMemberNickName()+"|"	
								+wishList[i].getSotongContentsCode()+"|"
								+wishList[i].getWishTitle()+"|"
								+wishList[i].getContents()+"|"
								+wishList[i].getEmoticonName()+"|"
								+wishList[i].getEmoticonRoute()+"|"
								+changeDateToString(wishList[i].getWishDate())+"|"
								+changeDateToString(wishList[i].getWishEndDate())+"|"
								+wishList[i].getWishFinish()+"|".trim();
								out.println(str + data + "".trim());
							//System.out.println("wishInfo" + str + data + "".trim());
				}
			} else {
				System.out.println("Web 立加");
				System.out.println("甸客乐促");
				request.setAttribute("wishList", wishListView);
				request.setAttribute("wishTitleList", wishTitle);
				RequestDispatcher rd = request.getRequestDispatcher("JSP/wishList/WishList.jsp");
				rd.forward(request,  response);			
			}
		} else {
			
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				out.println("500|");	
			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				request.setAttribute("wishList", wishListView);
				request.setAttribute("wishTitleList", wishTitle);
				RequestDispatcher rd = request.getRequestDispatcher("JSP/wishList/WishList.jsp");
				rd.forward(request,  response);	
			}
		}
		
		
		
		
		
		
		/*
		PrintWriter out = response.getWriter();
		String homeCode=request.getParameter("homeCode");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		WishViewVO[] wishList = manager.getWishList(homeCode);
		if (wishList != null) {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				String str = "200|";
				String data = "";
				for (int i = 0; i < wishList.length; i++) {
						data = 	wishList[i].getWishCode()+"|"			
								+wishList[i].getMemberNickName()+"|"	
								+wishList[i].getSotongContentsCode()+"|"
								+wishList[i].getWishTitle()+"|"
								+wishList[i].getContents()+"|"
								+wishList[i].getEmoticonName()+"|"
								+wishList[i].getEmoticonRoute()+"|"
								+changeDateToString(wishList[i].getWishDate())+"|"
								+changeDateToString(wishList[i].getWishEndDate())+"|"
								+wishList[i].getWishFinish()+"|".trim();
								out.println(str + data + "".trim());
							//System.out.println("wishInfo" + str + data + "".trim());
				}
				
				

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				out.println("500|");	
			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
		*/
	}

	public void requestAddWish(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String homeCode=request.getParameter("homeCode");
		String memberCode=request.getParameter("memberCode");
		String wishTitle=request.getParameter("wishTitle");
		String contents=request.getParameter("contents");
		String emoticonCode=request.getParameter("emoticonCode");
		String imageName=request.getParameter("imageName");
		String wishWrittenDate=request.getParameter("wishWrittenDate");
		String wishEndDate=request.getParameter("wishEndDate");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.addWish(homeCode,memberCode,wishTitle,contents,emoticonCode,imageName,changeStringToDate(wishWrittenDate),changeStringToDate(wishEndDate));
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
				RequestDispatcher rd = request.getRequestDispatcher("JSP/wishList/WishListWrite.jsp");
	            rd.forward(request,  response);    
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				out.println("500|");
			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}
	

	public void requestDeleteWish(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String wishCode=request.getParameter("wishCode");
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.deleteWish(wishCode);
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				out.println("500|");
			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}

	public void requestUpdateWish(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		String homeCode=request.getParameter("homeCode");
		String wishCode=request.getParameter("wishCode");
		String memberCode=request.getParameter("memberCode");
		String wishTitle=request.getParameter("wishTitle");
		String contents=request.getParameter("contents");
		String emoticonCode=request.getParameter("emoticonCode");
		String imageName=request.getParameter("imageName");
		String wishWrittenDate=request.getParameter("wishWrittenDate");
		String wishEndDate=request.getParameter("wishEndDate");
		
		String devide=request.getParameter("serviceRoute");
		
		
		
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.updateWish(wishCode,homeCode,memberCode,wishTitle,contents,emoticonCode,imageName,changeStringToDate(wishWrittenDate),changeStringToDate(wishEndDate));
		
		
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
				System.out.println("tt/"+homeCode+"/"+wishCode+"/"+memberCode+"/"+wishTitle+"/"+contents+"/"+emoticonCode+"/"+imageName+"/"+wishWrittenDate+"/"+wishEndDate);
				
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				String str = "500|";
				out.println(str);

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}

	public void requestFinishWish(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String wishCode=request.getParameter("wishCode");
		String finishDate=request.getParameter("finishDate");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.finishWish(wishCode,changeStringToDate(finishDate));
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				out.println("500|");
			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}
	
	public void requestWishing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	   {
	      WishManager manager= new WishManager();

	      HttpSession session = request.getSession();
	      FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
	      String homeCode = userInfo.getFamilyHomecode();
	      
	      
	      WishListViewVO[] wishListView = null; //
	      ArrayList<String[]> wishTitle = new ArrayList<String[]>();
	      
	      wishListView = manager.getWishing(homeCode, wishTitle);   //
	      

	      System.out.println("Web 立加");
	      System.out.println("甸客乐促");
	      request.setAttribute("wishList", wishListView);
	      request.setAttribute("wishTitleList", wishTitle);
	      RequestDispatcher rd = request.getRequestDispatcher("JSP/wishList/WishList.jsp");
	      rd.forward(request,  response);         
	   }
	   
	   public void requestFinishWishWeb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	   {
	      WishManager manager= new WishManager();

	      HttpSession session = request.getSession();
	      FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
	      String homeCode = userInfo.getFamilyHomecode();
	      
	      
	      WishListViewVO[] wishListView = null; //
	      ArrayList<String[]> wishTitle = new ArrayList<String[]>();
	      
	      wishListView = manager.getFinishWish(homeCode, wishTitle);   //
	      

	      System.out.println("Web 立加");
	      System.out.println("甸客乐促");
	      request.setAttribute("wishList", wishListView);
	      request.setAttribute("wishTitleList", wishTitle);
	      RequestDispatcher rd = request.getRequestDispatcher("JSP/wishList/WishList.jsp");
	      rd.forward(request,  response);         
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

	public String changeDateToString(Date date) {
		return new SimpleDateFormat("yy-MM-dd").format(date);
	}

}
