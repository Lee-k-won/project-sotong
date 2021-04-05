package control.matching;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.matching.MatchingManager;

/**
 * Servlet implementation class ScheduleMatchingServlet
 */
@WebServlet({ "/ScheduleMatchingServlet", "/matching.do" })
public class ScheduleMatchingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleMatchingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		
		PrintWriter out = response.getWriter();
		
		String serviceRoute = request.getParameter("serviceRoute");
		String homeCode = request.getParameter("homeCode");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		System.out.println("homeCode is : "+homeCode);
		System.out.println("Year is : "+year);
		System.out.println("month is : "+month);
		
		MatchingManager manager = new MatchingManager();
		
		String result[] = manager.requestScheduleMatching(homeCode, year, month);
		
		for(int cnt=0; cnt<result.length; cnt++){
			out.println("200|"+result[cnt]); 
		}
		
	}

}
