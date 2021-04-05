package control.home;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.LoginManager;
import manager.home.MakeHomeManager;
import model.bean.FamilyHomeBean;
import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class MakeHomeServlet
 */
@WebServlet("/makeHome.do")
public class MakeHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		System.out.println("����� ���� : " + userInfo.getMemberCode());
		String homeName = userInfo.getMemberName() + "�� Ȩ";
		String memberCode = userInfo.getMemberCode();
		session.setAttribute("serviceRoute", "2000");
		
		MakeHomeManager manager = new MakeHomeManager();
		String homeCode = manager.addHome(homeName);
		if (homeCode != null) {
			int checkMemberRole = manager.updateMember(homeCode, memberCode);
			FamilyHomeVO homeInfo = new FamilyHomeVO(homeCode, homeName);
			
			//Ȩ���� ����
			session.setAttribute("homeInfo", homeInfo);

			//����� ���� ����
			userInfo.setFamilyHomecode(homeCode);
			userInfo.setMemberRole("1");
			
			session.setAttribute("userInfo", userInfo);
		} else {
			System.out.println("Ȩ ���� ����");
		}
		
		//String birth = sendChangeDate(vo.getMemberBirth());
		System.out.println("Ȩ ���� Ȯ��");
		RequestDispatcher rd = request.getRequestDispatcher("/home.do");
		rd.forward(request, response);
		
	}
	
	private String format(Date dateTime){ // Date�� String���� ������/ ������ ���� �� ���
		if (dateTime == null) {
			return "00�� 00��";
		} else {
			String mon = (dateTime.getMonth())>9 ? "" + (dateTime.getMonth()) : "0" + (dateTime.getMonth());
			String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
			return  mon +"�� " + day + "��";
		}
	}
	
	
}
