package time;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p><b>Description</b> : 현재 시간을 출력하는 서블릿 클래스입니다.</p>
 * 
 * <p><b>Copyright</b> : Copyright(c) nts-corp All right reserved.</p>
 * 
 * 
 * <pre>
 * @author "Hyeoknae.Kwon"
 * <br/>
 * </pre>
 * @version 1.0 2018.07.12 초기작성
 * 
 * @see HttpServlet
 */
@WebServlet("/today")
public class TodayServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	/**
	 * time 패키지에 있는 LocalDateTime 을 이용해 현재 시간을 구한 후
	 * DateTimeFormatter 로 출력하고 싶은 형식에 맞춰
	 * out 리소스를 이용해 클라이언트에 출력 시켜주는 메소드입니다.
	 *   
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object obj = new Object();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		LocalDateTime lt = LocalDateTime.now();
		String formattedString = "현재시간 : "+lt.format(formatter);
		
		out.print("<html class=\"time\">");
		out.print("<head>");
		out.print("<link rel=\"stylesheet\" href=\"static/css/about_me.css?v=1.1\">");
		out.print("</head>");
		out.print("<body class=\"time\">");
		out.print("<div class=\"timeServlet\">");
		out.print("<a href=\"/aboutme/index.html?v=3\">메인화면</a>");
		out.print("<p>" + formattedString + "</p>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		
		out.flush();
		out.close();
		
	}
}
