package control.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.LoginManager;
import model.bean.FamilyHomeBean;
import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/login.do", "/logout.do"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("LoginServelt : get-method");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//App을 위해 PrintWriter를 생성한다.
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		if (action.equals("login.do")) {
			login(request, response);
		} else {
			logout(request, response);
		}
		
		
	}
	
	private void logout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userInfo");
		session.removeAttribute("homeInfo");
	    session.invalidate();    
	    
	    System.out.println("로그아웃");
	    RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
		rd.forward(request, response);
	}
	
	private void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("user-id");
		String userPw = request.getParameter("user-pw");
		
		//Web와 App을 구분해준다.
		String serviceRoute = request.getParameter("serviceRoute");
		
		
		//로그인 매니저를 생성한다.
		LoginManager loginManager = new LoginManager();
		
		//로그인 승인을 확인한다.
		FamilyMemberVO vo = loginManager.checkLogin(userId, userPw);
		System.out.println(vo);
		
		RequestDispatcher rd = null;
		if (vo != null) {
			String checkHomeCode = vo.getFamilyHomecode();
			String[][] familyMemberList = null;
			if (serviceRoute.equals("2000")) {
				// web 으로 접속할 때 들어오는 곳 
				System.out.println("Web 접속");
				
				//세션 객체를 생성한다.
				HttpSession session = request.getSession();
				
				//session 객체에 로그인 정보를 저장한다.
				session.setAttribute("userInfo", vo);
				
				//session 객체에 접속정보 저장
				session.setAttribute("serviceRoute", serviceRoute);
				
				if (checkHomeCode == null) {
					rd = request.getRequestDispatcher("MakeHome.jsp");
					rd.forward(request, response);
				}  else {
					FamilyMemberVO managerVO = loginManager.homeManagerInfo(vo.getFamilyHomecode());
					
					familyMemberList = loginManager.getMemberListWeb(vo.getFamilyHomecode(), managerVO.getMemberCode());
					System.out.println("사용자목록");
					for(int i=0; i<familyMemberList.length;i++)
					{
						for(int j=0; j<familyMemberList[i].length;j++)
						{
							System.out.println(familyMemberList[i][j]);
						}
					}
					
					String homeName = loginManager.getHomeName(vo.getFamilyHomecode());
					System.out.println(homeName);
					FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), managerVO.getMemberBirth(), managerVO.getMemberPhoto(), familyMemberList); 
					request.setAttribute("familyHome", bean);
					
					//로그인한 사용자의 홈정보 세션에 세팅
					FamilyHomeVO homeInfo = new FamilyHomeVO(vo.getFamilyHomecode(), homeName);
					session.setAttribute("homeInfo", homeInfo);
					request.setAttribute("managerCode", managerVO.getMemberCode());
					//session 객체에 사용자 정보 세팅
					session.setAttribute("familyMemberList", familyMemberList);
					
					rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
					rd.forward(request, response);
				}
			} else {
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("APP 접속");
				
				//가족 구성원의 정보를 받아온다.
				familyMemberList = loginManager.getMemberListApp(vo.getFamilyHomecode());
				
				String birth = vo.getMemberBirth();
				//로그인 한 회원의 정보를 전송한다.
				out.println("200|" + vo.getMemberCode() + "|" + vo.getFamilyHomecode() + "|" +
					vo.getMemberName() + "|" + vo.getMemberPhone() + "|" +  vo.getMemberEmail() + "|" +
					vo.getMemberId() + "|" + vo.getMemberPw() + "|" + vo.getMemberPhoto() + "|" +
					vo.getMemberNickName() + "|" + vo.getMemberColor() + "|" +
					birth + "|" + vo.getMemberRole());
					
				//가족 구성원의 정보를 전송한다.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} else {
			if (serviceRoute.equals("2000")) {
				// web 으로 접속할 때 들어오는 곳 
				System.out.println("Web 접속");
				
				out.println("<script>");
				out.println("alert('로그인에 실패하셨습니다. 다시 시도해주세요');");
				out.println("history.back();");
				out.println("</script>");

			} else {
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("APP 접속");
				out.println("500");
			}
		}
	}
}
