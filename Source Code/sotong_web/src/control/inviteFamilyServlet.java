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
		
		
		// form fields �о����
		String marketUrl = request.getParameter("marketUrl");	// marketUrl
		String content = request.getParameter("content");	// email ����

				// ���� �޾ƾ���
		
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		String subject = "So, tong �����̱⿡ ���Ѵ�. ���뿡�� ������ �ʴ밡 �Խ��ϴ�!";
		String recipient = request.getParameter("recipient");
		String homeCode = userInfo.getFamilyHomecode();
		String memberNickname = userInfo.getMemberNickName();
				
		String resultMessage = "";
				
		String htmlcode = getHTMLCode(homeCode, memberNickname);//�̸��� ȭ��� HTML�ڵ� ����
		
		try 
		{
			EmailSender.sendHTMLEmail(smtphost, smtpport, serverhostaccount, serverhostpwd, recipient, subject, htmlcode); // �̸��� ����
			resultMessage = "The e-mail was sent successfully";
		} 
		catch (Exception ex) 
		{
			// �̸��� ���� ���н�
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		}
		finally 
		{
			// ajax�� modalâ�� ��� MarketManager���� �̸��� ������ �Ǿ����� �˷��� ��
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/home.do").forward(request, response);
		}
			
	}
	
	
	/***
	 * 
	 * �̸��� �������� ���Ͽ� �ߴ� ȭ�� ���� HTML �ڵ带 ���뿡 ���� �����ϴ� �޼ҵ�
	 * @param marketName	: �ø����� ��
	 * @param postContent	: �������� ����
	 * @param actionurl		: �ش� ������ url
	 * @return ������ ���� ȭ��� HTML �ڵ�
	 * 
	 */
	
	
	private String getHTMLCode(String homeCode, String memberNickname){	// �����ؾ��� parameter �߰�
		String ip =  getServletContext().getInitParameter("ipAddress");
		
		String htmlCode = "<div id=\"messageDiv\" style=\"width:500px; height:200px; padding:20px; text-align:center; margin:40px auto ; border: 1px soid #00bfff; border-radius:25px; background-color:#00bfff;\">	<h3>�ȳ��ϼ���, �����Դϴ�!</h3> <p>" 
		+ memberNickname +"�Բ��� ���������� �ʴ����� �����̾��!</p> <p>����� �Բ� ��������� ��ſ� �߾��� ��������! </p><a href=\"http://" + ip + ":8089/final_so_tong?homeCode=" + homeCode + "\" style=\"margin-top:30px;\">������� �����Ϸ� ����!</a></div>";
		
		return htmlCode;
	}
	/*
	 * 
	 * String htmlCode = "<div id=\"messageDiv\" style=\"width:500px; height:200px; padding:20px; text-align:center; margin:40px auto ; border: 1px soid #00bfff; border-radius:25px; background-color:#00bfff;\">	<h3>�ȳ��ϼ���, �����Դϴ�!</h3> <p>" 
		+ memberNickname +"�Բ��� ���������� �ʴ����� �����̾��!</p> <p>����� �Բ� ��������� ��ſ� �߾��� ��������!</p> <form method=\"post\" action=\"http://192.168.0.14:8089:/final_so_tong\" id=\"join-form\"> <input type=\"hidden\" value=\"" 
		+ homeCode +"\"><input type=\"submit\" value=\"������ �����Ϸ� ����!\" style=\"margin-top:20px; backgourd:White;\"></div>";
	 */

}
