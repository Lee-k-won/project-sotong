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
		
		//App�� ���� PrintWriter�� �����Ѵ�.
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
	    
	    System.out.println("�α׾ƿ�");
	    RequestDispatcher rd = request.getRequestDispatcher("Main.jsp");
		rd.forward(request, response);
	}
	
	private void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("user-id");
		String userPw = request.getParameter("user-pw");
		
		//Web�� App�� �������ش�.
		String serviceRoute = request.getParameter("serviceRoute");
		
		
		//�α��� �Ŵ����� �����Ѵ�.
		LoginManager loginManager = new LoginManager();
		
		//�α��� ������ Ȯ���Ѵ�.
		FamilyMemberVO vo = loginManager.checkLogin(userId, userPw);
		System.out.println(vo);
		
		RequestDispatcher rd = null;
		if (vo != null) {
			String checkHomeCode = vo.getFamilyHomecode();
			String[][] familyMemberList = null;
			if (serviceRoute.equals("2000")) {
				// web ���� ������ �� ������ �� 
				System.out.println("Web ����");
				
				//���� ��ü�� �����Ѵ�.
				HttpSession session = request.getSession();
				
				//session ��ü�� �α��� ������ �����Ѵ�.
				session.setAttribute("userInfo", vo);
				
				//session ��ü�� �������� ����
				session.setAttribute("serviceRoute", serviceRoute);
				
				if (checkHomeCode == null) {
					rd = request.getRequestDispatcher("MakeHome.jsp");
					rd.forward(request, response);
				}  else {
					FamilyMemberVO managerVO = loginManager.homeManagerInfo(vo.getFamilyHomecode());
					
					familyMemberList = loginManager.getMemberListWeb(vo.getFamilyHomecode(), managerVO.getMemberCode());
					System.out.println("����ڸ��");
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
					
					//�α����� ������� Ȩ���� ���ǿ� ����
					FamilyHomeVO homeInfo = new FamilyHomeVO(vo.getFamilyHomecode(), homeName);
					session.setAttribute("homeInfo", homeInfo);
					request.setAttribute("managerCode", managerVO.getMemberCode());
					//session ��ü�� ����� ���� ����
					session.setAttribute("familyMemberList", familyMemberList);
					
					rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
					rd.forward(request, response);
				}
			} else {
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("APP ����");
				
				//���� �������� ������ �޾ƿ´�.
				familyMemberList = loginManager.getMemberListApp(vo.getFamilyHomecode());
				
				String birth = vo.getMemberBirth();
				//�α��� �� ȸ���� ������ �����Ѵ�.
				out.println("200|" + vo.getMemberCode() + "|" + vo.getFamilyHomecode() + "|" +
					vo.getMemberName() + "|" + vo.getMemberPhone() + "|" +  vo.getMemberEmail() + "|" +
					vo.getMemberId() + "|" + vo.getMemberPw() + "|" + vo.getMemberPhoto() + "|" +
					vo.getMemberNickName() + "|" + vo.getMemberColor() + "|" +
					birth + "|" + vo.getMemberRole());
					
				//���� �������� ������ �����Ѵ�.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} else {
			if (serviceRoute.equals("2000")) {
				// web ���� ������ �� ������ �� 
				System.out.println("Web ����");
				
				out.println("<script>");
				out.println("alert('�α��ο� �����ϼ̽��ϴ�. �ٽ� �õ����ּ���');");
				out.println("history.back();");
				out.println("</script>");

			} else {
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("APP ����");
				out.println("500");
			}
		}
	}
}
