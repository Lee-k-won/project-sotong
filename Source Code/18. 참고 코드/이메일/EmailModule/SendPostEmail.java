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
	 * SMTP 서버 사용과 관련된 데이터를 ServletContext에서 꺼내와 멤버변수로 저장시키는 메소드
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
	 * 이메일 수신자의 메일에 뜨는 화면 내용 HTML 코드를 내용에 따라 생성하는 메소드
	 * @param marketName	: 플리마켓 명
	 * @param postContent	: 공지사항 내용
	 * @param actionurl		: 해당 공지글 url
	 * @return 수신자 메일 화면용 HTML 코드
	 * 
	 */
	private String getHTMLCode(String marketName, String postContent, String actionurl ){	// 수정해야함 parameter 추가
		String htmlCode = "<div id=\"all\" style=\"text-align:center;\"><div id=\"contentdiv\" style=\"text-align : center; background-color:WhiteSmoke;\"><h1>"+
	marketName+
	"에서 공지합니다.</h1><p>"+
	postContent+
	"</p><div id=\"btndiv\"><form method=\"get\"action=\""+
	actionurl+
	"\"><input type=\"hidden\" name=\"emailcode\" value=\"hiddencode\"><input type=\"submit\" id=\"marketboardlinkbtn\" value=\"게시글 보러가기\" style=\"font-size:20pt;background-color:orange;	color: white;	border: none;	border-radius: 5pt;	margin-bottom: 20px;\"></form></div></div></div></body></html>";
		return htmlCode;
	}
	/**
	 * Email을 보내는 static method EmailSender.sendEmail()을 통해 메일을 전송하는 doPost 메소드
	 */
  	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
  		request.setCharacterEncoding("utf-8");
		
  		// form fields 읽어오기
		String marketName = request.getParameter("marketName");	// Market명
		String[] recipients = request.getParameterValues("recipient");	// 수신인 email List
		String marketUrl = request.getParameter("marketUrl");	// marketUrl
		String subject = request.getParameter("subject");	// email 제목
		String content = request.getParameter("content");	// email 내용
		String postcategory = request.getParameter("postcategory");	// 공지사항 카테고리(공지, 모집)
		System.out.println(postcategory);
		subject ="["+ postcategory + "] " + subject;
		// 파일 받아야함
		
		String resultMessage = "";
		
		String htmlcode = getHTMLCode(marketName,content,marketUrl);//이메일 화면용 HTML코드 생성
		
		try {
			EmailSender.sendHTMLEmail(smtphost, smtpport, serverhostaccount, serverhostpwd, recipients, subject,
					htmlcode); // 이메일 전송
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			// 이메일 전송 실패시
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			// ajax로 modal창을 띄워 MarketManager에게 이메일 전송이 되었음을 알려야 함
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/jsp/marketManagement.jsp").forward(
					request, response);
		}
	}
 
}
