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
		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
		String homeName = userInfo.getMemberName() + "의 홈";
		String memberCode = userInfo.getMemberCode();
		session.setAttribute("serviceRoute", "2000");
		
		MakeHomeManager manager = new MakeHomeManager();
		String homeCode = manager.addHome(homeName);
		if (homeCode != null) {
			int checkMemberRole = manager.updateMember(homeCode, memberCode);
			FamilyHomeVO homeInfo = new FamilyHomeVO(homeCode, homeName);
			
			//홈정보 저장
			session.setAttribute("homeInfo", homeInfo);

			//사용자 정보 변경
			userInfo.setFamilyHomecode(homeCode);
			userInfo.setMemberRole("1");
			
			session.setAttribute("userInfo", userInfo);
		} else {
			System.out.println("홈 생성 에러");
		}
		
		//String birth = sendChangeDate(vo.getMemberBirth());
		System.out.println("홈 생성 확인");
		RequestDispatcher rd = request.getRequestDispatcher("/home.do");
		rd.forward(request, response);
		
	}
	
	private String format(Date dateTime){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
		if (dateTime == null) {
			return "00월 00일";
		} else {
			String mon = (dateTime.getMonth())>9 ? "" + (dateTime.getMonth()) : "0" + (dateTime.getMonth());
			String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
			return  mon +"월 " + day + "일";
		}
	}
	
	
}
