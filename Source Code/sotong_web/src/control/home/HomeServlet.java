package control.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import manager.home.HomeManager;
import manager.home.LoginManager;
import manager.home.NeighborHomeManager;
import model.bean.FamilyHomeBean;

import com.google.gson.Gson;

import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewVO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet({"/okManagerRole.do", "/home.do","/renameHome.do", "/deleteMember.do","/detailMember.do", "/modifyMember.do","/modifyMemberOk.do", "/neighborHome.do", "/neighborProfile.do", "/searchNeighbor.do"})
@MultipartConfig
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		process(request, response);
	}

	public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = null;
	    if (d != null) {
	    	date = fmt.format(d);
	    } else {
	    	date = "00월 00일";
	    }
	    return date;
	}
	
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		switch (action) {
			case "home.do" :
				requestHome(request,response);
				break;
			case "detailMember.do" :
				requestMemberInfo(request,response);
				break;
			case "renameHome.do" :
				requestRename(request,response);
				break;
			case "modifyMember.do" :
				requestUpdateMemberProfile(request,response);
				break;
			case "modifyMemberOk.do" :
				requestUpdateMemberProfileOk(request,response);
				break;
			case "neighborHome.do" :
				requestNeighborHome(request,response);
				break;
			case "deleteMember.do" :
				requestDeleteMember(request,response);
				break;
			case "neighborProfile.do" :
				requestNeighborProfile(request,response);
				break;
			case "searchNeighbor.do" :
				requestSearchNeighbor(request,response);
				break;
			case "okManagerRole.do" :
				requestOkManagerRole(request,response);
				break;
		}
		
		/*
		if(action.equals("home.do"))
		{
			requestHome(request,response);			
		}
		else if(action.equals("detailMember.do"))
		{
			requestMemberInfo(request,response);
		}
		else if(action.equals("renameHome.do"))
		{
			requestRename(request,response);
		}
		else if(action.equals("modifyMember.do"))
		{
			requestUpdateMemberProfile(request,response);
		}
		else if(action.equals("modifyMemberOk.do"))
		{
			requestUpdateMemberProfileOk(request,response);
		}
		else if(action.equals("neighborHome.do"))
		{
			requestNeighborHome(request,response);
		}
		else if(action.equals("deleteMember.do"))
		{
			requestDeleteMember(request,response);
		}
		else if(action.equals("neighborProfile.do"))
		{
			requestNeighborProfile(request,response);
		}
		else if(action.equals("searchNeighbor.do"))
		{
			requestSearchNeighbor(request,response);
		}
		*/
	}
	
	// 추가 메소드이다.
	private void requestOkManagerRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HomeManager manager = new HomeManager();
		LoginManager loginManager = new LoginManager();
		
		String homeCode = request.getParameter("homeCode");
		String managerCode = request.getParameter("managerCode");
		String memberCode = request.getParameter("memberCode");
		String id = request.getParameter("managerId");
		
		System.out.println("homeCode : " + homeCode);
		System.out.println("managerCode : " + managerCode);
		System.out.println("memberCode : " + memberCode);
		System.out.println("id : " + id);
		
		int checkRole = manager.changeRole(managerCode, memberCode);
		
		if (checkRole == 1) {
			FamilyMemberVO vo = loginManager.getMemberInfo(id);
			request.getSession().setAttribute("userInfo", vo);
			System.out.println(" 매니저 변경 성공 ");
		} else {
			System.out.println("매니저 변경 실패");
		}
	}
	
	
	
	private void requestSearchNeighbor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		String searchCategory = request.getParameter("search-category");
		String searchWord = request.getParameter("searchWord");
		
		System.out.println("검색분류 : " + searchCategory);
		System.out.println("검색어 : " + searchWord);
		HomeInfoViewVO[] homeList = manager.searchNeighbor(searchCategory, searchWord);

		if(homeList == null)
		{
			System.out.println("검색결과가 없음");
			return;
		}
		else
		{
			String result = new Gson().toJson(homeList);
			System.out.println(result);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		}
		
	}
	
	private void requestNeighborProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String serviceRouteApp = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		//선택한 멤버구성원의 코드를 받아옴
		String memberCode = request.getParameter("memberCode"); 
		
		if(memberCode == null) // 멤버 구성원 코드가 없으면
		{
			System.out.println("이웃 코드가 없음");
			out.println("500|");
		}
		else // 멤버 구성원 코드가 있으면
		{
			System.out.println("이웃 가족구성원 코드 : "+memberCode);			
			FamilyMemberVO memberInfo = manager.getMemberDetailInfo(memberCode);
			System.out.println("가족구성원 정보 : " + memberInfo);
			
			if(serviceRouteWeb == null) // 웹 접속 X이면
			{
				serviceRouteApp = request.getParameter("servieRoute");
				if(serviceRouteApp == null)
				{
					System.out.println("이웃 가족구성원 멤버보기 접속 에러");
					out.println("500|");
				}
				else // 앱 접속O
				{
					System.out.println("App 접속 - 이웃 가족구성원 보기");
				}
			}
			else // 웹 접속O
			{
				System.out.println("Web 접속 - 이웃 가족구성원 보기");
					
				System.out.println("멤버정보 null 체크 : " + memberInfo);
				System.out.println("멤버코드 : " + memberInfo.getFamilyHomecode());
				String homeName = manager.getHomeInfo(memberInfo.getFamilyHomecode()).getFamilyHomeName();
				String birth = memberInfo.getMemberBirth();

				request.setAttribute("homeName", homeName);
				request.setAttribute("birth",birth);
				request.setAttribute("memberInfo", memberInfo);
				
				RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighborProfile.jsp");
				rd.forward(request, response);	
			}
		}
	}
	
	// 미 완성 메소드 추가 완성
	private void requestDeleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		System.out.println("가족구성원을 삭제하러 왔다.");
		HomeManager manager = new HomeManager();
		
		String memberCode = request.getParameter("memberCode");
		String homeCode = request.getParameter("homeCode");
		String birth = request.getParameter("member-birth");
		
		System.out.println("삭제 : memberCode : " + memberCode);
		System.out.println("삭제 : homeCode : " + homeCode);
		System.out.println("삭제 : birth : " + birth);
		
		int checkDelete = manager.updateMember(memberCode);
		if (checkDelete != 0) {
			System.out.println("삭제 되었다.");
			
			HttpSession session = request.getSession();
			//FamilyMemberVO vo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			LoginManager loginManager = new LoginManager();
			FamilyMemberVO managerVO = loginManager.homeManagerInfo(homeCode);
			
			String[][] familyMemberList = loginManager.getMemberListWeb(homeCode, memberCode);
					
			
			for (String[] arr : familyMemberList) {
				for (String str : arr)
					System.out.println(str);
			}
			String homeName = loginManager.getHomeName(homeCode);
			
			System.out.println("삭제 : homeName : " + homeName);
			
			FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), birth, managerVO.getMemberPhoto(), familyMemberList); 
			request.setAttribute("familyHome", bean);
			request.setAttribute("managerCode", managerVO.getMemberCode());
			//session 객체에 사용자 정보 세팅
			session.setAttribute("familyMemberList", familyMemberList);
			
			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
			rd.forward(request, response);	
		}
		
		
	}
	
	private void requestNeighborHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		// 선택한 홈 정보에 해당하는 홈 코드를 받는다.
		String homeCode = request.getParameter("homeCode");
		System.out.println("이웃 홈 코드 : " +homeCode);
		
		PrintWriter out = response.getWriter();
		
		if(homeCode==null)
		{
			// 홈코드가 없다면 메인으로 이동
			response.sendRedirect("Main.jsp");
		}
	
		
		//멤버목록을 저장할 공간
		String[][] familyMemberList = null;
		
		// 홈 이름 가져오기
		String homeName = manager.getHomeInfo(homeCode).getFamilyHomeName();
		
		//매니저 정보 가져오기
		FamilyMemberVO homeManager = manager.homeManagerInfo(homeCode);
		
		System.out.println("매니저-홈코드 : " + homeManager.getFamilyHomecode());
		
		//멤버 목록 가져오기
		familyMemberList = manager.getMemberList(homeCode, homeManager.getMemberCode());
		
		//홈매니저 생일정보
		String birth = homeManager.getMemberBirth();
		
		
		
		
		// 접속 정보
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null) // web이 null
		{
			serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
			if(serviceRouteApp == null) // 앱역시 null일 경우
			{
				System.out.println("접속 에러 - 이웃 홈 보기");
			}
			else // 앱 접속
			{
				String mCode = homeManager.getMemberCode();
               String mNickName = homeManager.getMemberNickName();
               String mBirth = homeManager.getMemberBirth();
               String mColor = homeManager.getMemberColor();
               String mPhoto = homeManager.getMemberPhoto();
	               
               out.println("300|"+mCode+"|"+mNickName+"|"+mBirth+"|"+mColor+"|"+mPhoto);
               //앱 처리
               System.out.println("App 접속 - 이웃 홈 보기");
               for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		}
		else // 웹 접속
		{
			//이웃홈과 체크 정보
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			String myHomeCode = userInfo.getFamilyHomecode();
			
			NeighborHomeManager neighborManager = new NeighborHomeManager();
			int neighborCheck = neighborManager.checkNeighbor(myHomeCode, homeCode);						
			
			System.out.println("내 홈코드 : " + userInfo.getFamilyHomecode() + "이웃 홈 코드 : " + homeCode);
			if(userInfo.getMemberCode().equals(homeCode))
			{
				response.sendRedirect("home.do");
			}
			System.out.println("Web 접속 - 이웃 홈 보기");
			FamilyHomeBean bean = new FamilyHomeBean(homeName, homeManager.getMemberNickName(), birth, homeManager.getMemberPhoto(), familyMemberList); 
							
			request.setAttribute("managerCode", homeManager.getMemberCode());
			request.setAttribute("managerBirth", birth);
			request.setAttribute("homeManager", homeManager);
			System.out.println("-------------------홈 매니저 정보 : " + homeManager);
			request.setAttribute("familyHome", bean);
			String str = Integer.toString(neighborCheck);
			request.setAttribute("neighborCheck", str);
			
							
			RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighborHome.jsp");
			rd.forward(request, response);
			
		}	
		
	}
	
	private void requestUpdateMemberProfileOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		String imgRoute = getServletContext().getInitParameter("imgRoute");
		HttpSession session = request.getSession();
//		
//		//사용자 정보
//		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
//		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
//		
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
//		if(userInfo != null) //사용자 정보가 있을 경우
//		{
//			
			System.out.println("프로필 수정 관련 메소드");
			
			String code = request.getParameter("code");
			System.out.println("code값 : " + code);
			String name = request.getParameter("name");
			System.out.println("이름 : " + name);
			String phone = request.getParameter("phone");
			System.out.println("폰번호 : " + phone);
			String email = request.getParameter("email");
			System.out.println("이메일 : " + email);
			String pw = request.getParameter("pw");
			System.out.println("비번 : " +pw);
			String nickName = request.getParameter("nickName");
			System.out.println("별명 : " + nickName);
			String color = request.getParameter("color");
			String birth = request.getParameter("birth");
			System.out.println("생일"+birth);
			String photo = request.getParameter("photo");
			System.out.println("----여기까지----");
			
			
			if(serviceRouteWeb==null) // 앱으로 접속함
	         {
	            serviceRouteApp = request.getParameter("serviceRoute");
	            if(serviceRouteApp == null)
	            {
	               System.out.println("접속 에러");
	            }
	            else   //앱으로 접속시 처리
	            {         
	               System.out.println("APP 프로필 수정 메소드 접속.");      
	            }
	         }
	         else   //웹 접속 처리
	         {
	            Part part = request.getPart("updateImg"); 

	            String fileName = getFilename(part);
	            
	            if(fileName != null && !fileName.isEmpty())
	            { 
	               String imageFileName = makeFileName() + fileName.substring(fileName.length()-4);
	               part.write(imgRoute + "img\\profile\\" + imageFileName );
	               photo = "img/profile/"+imageFileName;
	            }
	         }
			
//			byte role = Byte.parseByte(request.getParameter("role"));
//			System.out.println(role);
			
			int num = manager.updateMemberProfile(code, name, phone, email, pw, photo, nickName, color, birth);
			if(num==-1)
			{
				//변경실패
				System.out.println("변경에 실패하였습니다.");
				return;
			}
			
			if(serviceRouteApp == null) // Web접속
			{	
	//			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
	//			rd.forward(request, response);
				
				//userInfo 새로 세팅
				FamilyMemberVO newUserInfo = (FamilyMemberVO)session.getAttribute("userInfo");
				newUserInfo.setMemberNickName(nickName);
				newUserInfo.setMemberName(name);
				newUserInfo.setMemberPhone(phone);
				newUserInfo.setMemberEmail(email);
				newUserInfo.setMemberPw(pw);
				newUserInfo.setMemberPhoto(photo);
				newUserInfo.setMemberColor(color);
				newUserInfo.setMemberBirth(birth);
				
				session.setAttribute("userInfo", newUserInfo);
				
				System.out.println("성공 - 프로필 수정");
				response.sendRedirect("home.do");
			}
			else
			{
				System.out.println("앱 접속 성공 - 프로필 수정");
				response.getWriter().println("200|success");
			}
		}
	
	private void requestUpdateMemberProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//사용자 정보
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
		
		//홈 정보
		String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
		request.setAttribute("homeName", homeName);
		
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		if(serviceRouteApp == null) // Web접속
		{	
			String birth = userInfo.getMemberBirth();
			request.setAttribute("birth", birth);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
			rd.forward(request, response);
		}
		else // App접속
		{
			System.out.println("App 접속 - 정보 수정");
			String birth = userInfo.getMemberBirth();
			PrintWriter out = response.getWriter();
			out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
					userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
					userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
					userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +birth + "|" + userInfo.getMemberRole());
		}
//		else
//		{
//			System.out.println("접속 에러");
//		}
		
	}
	
	private void requestRename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//사용자 정보
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
		
		//홈 이름 변경 위해서 jsp에 name 추가
		String homeName = URLDecoder.decode(request.getParameter("changeName"), "UTF-8");
		System.out.println("홈이름 : " + homeName);
		
		//사용자의 홈 코드
		String homeCode = userInfo.getFamilyHomecode();
		
		int num=manager.modifyHomeName(homeCode, homeName);
		
		if(num==1)
		{
			FamilyHomeVO homeInfo = (FamilyHomeVO)session.getAttribute("homeInfo");
			homeInfo.setFamilyHomeName(homeName);
			session.setAttribute("homeInfo", homeInfo);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeName);
		}
		else
		{
			System.out.println("이름 변경 실패");
			return;
		}
	}
	
	private void requestHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HomeManager manager= new HomeManager();		
		HttpSession session = request.getSession();		
		
		// 사용자 정보
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("사용자정보" + userInfo.getMemberCode());
		
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = null;
		if (userInfo != null) { // 사용자 정보가 null이 아니고
			String[][] familyMemberList = null;
			if (serviceRouteApp==null) { // 웹에서 접속
				
				if(userInfo.getFamilyHomecode() == null)
				{
					// 홈 코드가 없다면
					System.out.println("홈코드 없음");
					rd = request.getRequestDispatcher("MakeHome.jsp"); // 홈 생성 화면으로 이동
					rd.forward(request, response);
				}
				else // 홈코드가 있다면
				{	
					FamilyMemberVO managerVO = manager.homeManagerInfo(userInfo.getFamilyHomecode());
					
					System.out.println();
					System.out.println(managerVO);
					System.out.println();
					familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), managerVO.getMemberCode());
					
					String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
					
					request.setAttribute("managerCode", managerVO.getMemberCode());
					FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), managerVO.getMemberBirth(), managerVO.getMemberPhoto(), familyMemberList); 
					request.setAttribute("familyHome", bean);
					rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
					rd.forward(request, response);
				}
			} else {
				
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("APP 접속");
				
				//가족 구성원의 정보를 받아온다.
				familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), userInfo.getMemberCode());
				
				String birth = userInfo.getMemberBirth();
				
				//로그인 한 회원의 정보를 전송한다.
			
				out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
						userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
						userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
						userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +
					birth + "|" + userInfo.getMemberRole());
					
				//가족 구성원의 정보를 전송한다.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} 
	}
	
	private void requestMemberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
				
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
					
		if(serviceRouteApp==null) // 웹이면
		{
			System.out.println("웹접속");
			// 사용자 정보
			FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
			
			if(userInfo!=null)
			{
				//선택된 멤버코드 받아오기
				String memberCode = request.getParameter("memberInfo");
				System.out.println("선택된 멤버코드 전송" + memberCode);
				
				FamilyHomeVO familyHome = manager.getHomeInfo(userInfo.getFamilyHomecode());
				
				FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
				if(memberDetail != null)
				{
					String birth = memberDetail.getMemberBirth();
					System.out.println("생년 : " + birth);
					System.out.println("format 사용 전 : " + memberDetail.getMemberBirth());
					
					//멤버 상세정보와 홈정보 전달				
					request.setAttribute("memberBirth", birth);				
					request.setAttribute("memberDetail", memberDetail);
					request.setAttribute("familyHome", familyHome);
				}
				else // null이면
				{	
					request.setAttribute("memberBirth", userInfo.getMemberBirth());
					request.setAttribute("memberDetail", userInfo);
					request.setAttribute("familyHome", familyHome);
				}	
				rd = request.getRequestDispatcher("JSP/home/familyProfile.jsp");
				rd.forward(request, response);
				
			}
			else
			{
				 response.sendRedirect("/Main.jsp");
				//System.out.println("사용자 정보가 없습니다.");
			}
		}
		else
		{
			System.out.println("상세프로필 - APP 접속 - 성공");
			
			String memberCode = request.getParameter("memberInfo");
			System.out.println("선택된 멤버코드 전송" + memberCode);
			
			FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
			String  birth = memberDetail.getMemberBirth();
			
			out.println("200|" + memberDetail.getMemberCode() + "|" +
					memberDetail.getMemberName() + "|" + memberDetail.getMemberPhone() + "|" +  memberDetail.getMemberEmail() + "|" +
					memberDetail.getMemberPhoto() +"|"+ memberDetail.getMemberNickName() + "|" + memberDetail.getMemberColor() + "|" +
				birth + "|" + memberDetail.getMemberRole());				
		}
	}
	
	private String getFilename(Part part)
	{
		String contentDispositionHeader = part.getHeader("content-disposition");
		String[] elements = contentDispositionHeader.split(";");
		for(String element : elements)
		{
			if(element.trim().startsWith("filename"))
			{
				return element.substring(element.indexOf('=') + 1).trim().replace("\"","");
			}
		}
		return null;
	}
	
	private String makeFileName()
	{
		GregorianCalendar cal = new GregorianCalendar();
		
		String name = "sotong_" + cal.get(Calendar.YEAR) + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + cal.get(Calendar.DATE) + "_" + String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + cal.get(Calendar.MINUTE) + ((int)(Math.random()*10000)); 
		
		return name;
	}
	
}
