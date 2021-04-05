package control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class inviteFamilyServlet
 */
@WebServlet("/SendEmail.do")
public class inviteFamilyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inviteFamilyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		String smtphost = context.getInitParameter("smtphost");
		String smtpport = context.getInitParameter("smtpport");
		String serverhostaccount = context.getInitParameter("serverhostaccount");
		String serverhostpwd = context.getInitParameter("serverhostpwd");
		
		
		// form fields 읽어오기
		String marketUrl = request.getParameter("marketUrl");	// marketUrl
		String content = request.getParameter("content");	// email 내용

				// 파일 받아야함
		
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		String subject = "So, tong 가족이기에 통한다. 소통에서 가족의 초대가 왔습니다!";
		String recipient = request.getParameter("recipient");
		String homeCode = userInfo.getFamilyHomecode();
		String memberNickname = userInfo.getMemberNickName();
				
		String resultMessage = "";
				
		String htmlcode = getHTMLCode(homeCode, memberNickname);//이메일 화면용 HTML코드 생성
		
		try 
		{
			EmailSender.sendHTMLEmail(smtphost, smtpport, serverhostaccount, serverhostpwd, recipient, subject, htmlcode); // 이메일 전송
			resultMessage = "The e-mail was sent successfully";
		} 
		catch (Exception ex) 
		{
			// 이메일 전송 실패시
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		}
		finally 
		{
			// ajax로 modal창을 띄워 MarketManager에게 이메일 전송이 되었음을 알려야 함
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/home.do").forward(request, response);
		}
			
	}
	
	
	/***
	 * 
	 * 이메일 수신자의 메일에 뜨는 화면 내용 HTML 코드를 내용에 따라 생성하는 메소드
	 * @param marketName	: 플리마켓 명
	 * @param postContent	: 공지사항 내용
	 * @param actionurl		: 해당 공지글 url
	 * @return 수신자 메일 화면용 HTML 코드
	 * 
	 */
	
	
	private String getHTMLCode(String homeCode, String memberNickname){	// 수정해야함 parameter 추가
		String ip =  getServletContext().getInitParameter("ipAddress");
		
		String htmlCode = "<div id=\"messageDiv\" style=\"width:500px; height:200px; padding:20px; text-align:center; margin:40px auto ; border: 1px soid #00bfff; border-radius:25px; background-color:#00bfff;\">	<h3>안녕하세요, 소통입니다!</h3> <p>" 
		+ memberNickname +"님께서 소통으로의 초대장을 보내셨어요!</p> <p>소통과 함께 가족들과의 즐거운 추억을 만들어보세요! </p><a href=\"http://" + ip + ":8089/final_so_tong?homeCode=" + homeCode + "\" style=\"margin-top:30px;\">가족들과 소통하러 가기!</a></div>";
		
		return htmlCode;
	}
	/*
	 * 
	 * String htmlCode = "<div id=\"messageDiv\" style=\"width:500px; height:200px; padding:20px; text-align:center; margin:40px auto ; border: 1px soid #00bfff; border-radius:25px; background-color:#00bfff;\">	<h3>안녕하세요, 소통입니다!</h3> <p>" 
		+ memberNickname +"님께서 소통으로의 초대장을 보내셨어요!</p> <p>소통과 함께 가족들과의 즐거운 추억을 만들어보세요!</p> <form method=\"post\" action=\"http://192.168.0.14:8089:/final_so_tong\" id=\"join-form\"> <input type=\"hidden\" value=\"" 
		+ homeCode +"\"><input type=\"submit\" value=\"가족과 소통하러 가기!\" style=\"margin-top:20px; backgourd:White;\"></div>";
	 */

}
