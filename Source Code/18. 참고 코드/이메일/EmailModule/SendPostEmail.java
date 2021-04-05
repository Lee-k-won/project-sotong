package EmailModule;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendPostEmail
 */
@WebServlet(urlPatterns={"/SendPostEmail.do"})
public class SendPostEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String smtphost;
	private String smtpport;
	private String serverhostaccount;
	private String serverhostpwd;

	/***
	 * SMTP ���� ���� ���õ� �����͸� ServletContext���� ������ ��������� �����Ű�� �޼ҵ�
	 */
	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		smtphost = context.getInitParameter("smtphost");
		smtpport = context.getInitParameter("smtpport");
		serverhostaccount = context.getInitParameter("serverhostaccount");
		serverhostpwd = context.getInitParameter("serverhostpwd");
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
	private String getHTMLCode(String marketName, String postContent, String actionurl ){	// �����ؾ��� parameter �߰�
		String htmlCode = "<div id=\"all\" style=\"text-align:center;\"><div id=\"contentdiv\" style=\"text-align : center; background-color:WhiteSmoke;\"><h1>"+
	marketName+
	"���� �����մϴ�.</h1><p>"+
	postContent+
	"</p><div id=\"btndiv\"><form method=\"get\"action=\""+
	actionurl+
	"\"><input type=\"hidden\" name=\"emailcode\" value=\"hiddencode\"><input type=\"submit\" id=\"marketboardlinkbtn\" value=\"�Խñ� ��������\" style=\"font-size:20pt;background-color:orange;	color: white;	border: none;	border-radius: 5pt;	margin-bottom: 20px;\"></form></div></div></div></body></html>";
		return htmlCode;
	}
	/**
	 * Email�� ������ static method EmailSender.sendEmail()�� ���� ������ �����ϴ� doPost �޼ҵ�
	 */
  	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
  		request.setCharacterEncoding("utf-8");
		
  		// form fields �о����
		String marketName = request.getParameter("marketName");	// Market��
		String[] recipients = request.getParameterValues("recipient");	// ������ email List
		String marketUrl = request.getParameter("marketUrl");	// marketUrl
		String subject = request.getParameter("subject");	// email ����
		String content = request.getParameter("content");	// email ����
		String postcategory = request.getParameter("postcategory");	// �������� ī�װ�(����, ����)
		System.out.println(postcategory);
		subject ="["+ postcategory + "] " + subject;
		// ���� �޾ƾ���
		
		String resultMessage = "";
		
		String htmlcode = getHTMLCode(marketName,content,marketUrl);//�̸��� ȭ��� HTML�ڵ� ����
		
		try {
			EmailSender.sendHTMLEmail(smtphost, smtpport, serverhostaccount, serverhostpwd, recipients, subject,
					htmlcode); // �̸��� ����
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			// �̸��� ���� ���н�
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			// ajax�� modalâ�� ��� MarketManager���� �̸��� ������ �Ǿ����� �˷��� ��
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/jsp/marketManagement.jsp").forward(
					request, response);
		}
	}
 
}
