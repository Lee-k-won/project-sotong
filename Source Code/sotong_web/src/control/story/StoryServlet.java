package control.story;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.story.StoryManager;
import dao.home.FamilyMemberVO;
import dao.story.StoryViewVO;

/**
 * Servlet implementation class StoryServlet
 */
@WebServlet({ "/story.do", "/story-update.do", "/story-heart.do", "/story-write.do", "/story-delete.do" })
public class StoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		doPost(request, response);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		switch(action) {
			case "story-update.do" :
				requestUpdateStory(request, response);
				break;
			case "story-heart.do" :
				requestClickHeart(request, response);
				break;
			case "story-write.do" :
				requestAddStory(request, response);
				requestStoryList(request, response);
				break;
			case "story-delete.do" :
				requestDeleteStory(request, response);
				
				break;
			case "story.do" :
				requestStoryList(request, response);
				break;
				
		}
	}
	
private void requestStoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 접속 정보
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		StoryManager manager = new StoryManager();
		
		String homeCode = request.getParameter("homeCode");
		if (homeCode == null) {
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			homeCode = userInfo.getFamilyHomecode();
		}
		
		StoryViewVO[] storyList = manager.getStoryList(homeCode);
		
		if(serviceRouteWeb == null) // web이 null
		{
			serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
			if(serviceRouteApp == null) // 앱역시 null일 경우
			{
				System.out.println("접속 에러 - 이웃 홈");
			}
			else // 앱 접속
			{
				PrintWriter out = response.getWriter();
				if (storyList != null) {
					System.out.println("앱 접속 : 가족 이야기");
					for (StoryViewVO vo : storyList) {
						
						System.out.println("200|" + vo.getStoryCode() + "|"  + vo.getFamilyHomeCode() + "|" + vo.getFamilyHomeName() + "|"
								+ vo.getMemberCode() + "|" + vo.getMemberPhoto() + "|" + vo.getMemberColor() + "|" + vo.getMemberNickname() + "|" 
								+ vo.getContents() + "|"	+ vo.getContetsCode() + "|" + vo.getImageName() + "|" + vo.getImageWritenDate() + "|" 
								+ vo.getEmotioconName() + "|" + vo.getEmoticonRoute() + "|" + vo.getStoryDate() + "|" + vo.getStroyHeart() + "|" 
								+ vo.getStoryModifyDate() + "|" + vo.getStoryScope());
						System.out.println();
						
						out.println("200|" + vo.getStoryCode() + "|"  + vo.getFamilyHomeCode() + "|" + vo.getFamilyHomeName() + "|"
								+ vo.getMemberCode() + "|" + vo.getMemberPhoto() + "|" + vo.getMemberColor() + "|" + vo.getMemberNickname() + "|" 
								+ vo.getContents() + "|"	+ vo.getContetsCode() + "|" + vo.getImageName() + "|" + vo.getImageWritenDate() + "|" 
								+ vo.getEmotioconName() + "|" + vo.getEmoticonRoute() + "|" + vo.getStoryDate() + "|" + vo.getStroyHeart() + "|" 
								+ vo.getStoryModifyDate() + "|" + vo.getStoryScope());
					}
					
				} else {
					System.out.println(" 가족 홈코드 인증 실패");
					out.println("500");
				}
				
			}
		}
		else // 웹 접속
		{
			request.setAttribute("storyList", storyList);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/story/FamilyStory.jsp");
			rd.forward(request, response);
		}
		
	}
	
	
	
	private void requestAddStory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 접속 정보
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		StoryManager manager = new StoryManager();
		
		String storyContents = request.getParameter("family-board-content-write");
		String storyDate = newDate();
		String imageName = "img/nono";
		String emoticonCode = "em1";
		String scope = request.getParameter("public-scope");
		
		if(serviceRouteWeb == null) // web이 null
		{
			PrintWriter out = response.getWriter();
			
			serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
			if(serviceRouteApp == null) // 앱역시 null일 경우
			{
				System.out.println("접속 에러 - 이웃 홈");
			}
			else // 앱 접속
			{
				System.out.println("앱 접속 ");
				String memberCode = request.getParameter("memberCode");
				String homeCode = request.getParameter("homeCode");
				
				boolean addCheck = manager.addStory(homeCode, memberCode, imageName,
						storyContents, emoticonCode, storyDate, scope); 
				
				if (addCheck) {
					out.println("200|succes");
				} else {
					out.println("500");
				}			
			}
		}
		else // 웹 접속
		{
			manager.addStory(userInfo.getFamilyHomecode(), userInfo.getMemberCode(),
					imageName, storyContents, emoticonCode, storyDate, scope); 
		}
		
	}
	
	private void requestDeleteStory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("삭제하러 왔따.");
		
		// 접속 정보
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		StoryManager manager = new StoryManager();
		String storyCode = request.getParameter("story-code");
		String sotongContentsCode = request.getParameter("sotong-contents-code");
		
		String serviceRouteApp = null;
		if(serviceRouteWeb == null) // web이 null
		{
			PrintWriter out = response.getWriter();
			serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
			if(serviceRouteApp == null) // 앱역시 null일 경우
			{
				System.out.println("접속 에러 - 이웃 홈");
			}
			else // 앱 접속
			{
				int checkDelete = manager.deleteStory(storyCode, sotongContentsCode);	
				if (checkDelete != 0) {
					out.println("200|success");
				} else {
					out.println("500");
				}
			}
		}
		else // 웹 접속
		{
			manager.deleteStory(storyCode, sotongContentsCode);	
			requestStoryList(request, response);
		}
		
	}
	
	private void requestUpdateStory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 접속 정보
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		String modifyContents = request.getParameter("modifyContents");
		String scope = request.getParameter("public-scope");
		String sotongContentsCode = request.getParameter("sotong-contents-code");
		String storyCode = request.getParameter("story-code");
		String storyDate = newDate();
		
		System.out.println("modifyContents : " + modifyContents);
		System.out.println("scope : " + scope);
		System.out.println("소통 코드 : " + sotongContentsCode);
		System.out.println("이야기 코드 : " + storyCode);
		System.out.println("수정 시간 : " + storyDate);
		
		if(serviceRouteWeb == null) // web이 null
		{
			PrintWriter out = response.getWriter();
			serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
			if(serviceRouteApp == null) // 앱역시 null일 경우
			{
				System.out.println("접속 에러 - 이웃 홈");
			}
			else // 앱 접속
			{
				System.out.println("앱 접속");
				StoryManager manager = new StoryManager();
				int check = manager.updateStory(sotongContentsCode, modifyContents, storyCode, scope, storyDate);
				if (check == 0) {
					System.out.println("DB 수정 실패");
					out.println("500");
				} else {
					System.out.println("DB 수정 성공");
					out.println("200|success");
				}						
			}
		}
		else // 웹 접속
		{
			StoryManager manager = new StoryManager();
			int check = manager.updateStory(sotongContentsCode, modifyContents, storyCode, scope, storyDate);
			if (check == 0) {
				System.out.println("DB 수정 실패");
			} else {
				System.out.println("DB 수정 성공");
			}
			
		}
	}
	
	private void requestClickHeart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
				
		String storyCode = request.getParameter("story-code");
		String heartNo = request.getParameter("heartNo");
		int heart = Integer.parseInt(heartNo);
				
		if(serviceRouteWeb == null) // web이 null
		{
			serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
			if(serviceRouteApp == null) // 앱역시 null일 경우
			{
				System.out.println("접속 에러 - 이웃 홈");
			}
			else // 앱 접속
			{
				//앱 처리
				/*
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}*/
			}
		}
		else // 웹 접속
		{
			StoryManager manager = new StoryManager();
			
			heart = manager.updateHeart(storyCode,  heart);
			
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(heart));
		}
	}
	
		
	public String newDate() {
		
		Date dateTime = new Date();
		
		int year= dateTime.getYear() - 100;
		String mon = (dateTime.getMonth()+1)>9 ? "" + (dateTime.getMonth()+1) : "0" + (dateTime.getMonth()+1);
		String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
	    String hour = dateTime.getHours()>9 ? "" + dateTime.getHours() : "0" + dateTime.getHours(); 
	    String min = dateTime.getMinutes()>9 ? "" + dateTime.getMinutes() : "0" + dateTime.getMinutes();
	   
		return  year + "-" + mon +"-" + day + " " + hour + ":" + min; 
	}
}
