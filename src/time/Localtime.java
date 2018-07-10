package time;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/today")
public class Localtime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Localtime() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		LocalDateTime lt=LocalDateTime.now();
		System.out.println(lt.getYear()+":"+lt.getMonthValue()+":"+lt.getDayOfMonth()+":"+lt.getHour()+":"+lt.getMinute());
		out.print("<html>");
		
		out.print("<body>");
		out.print("<a href=\"/test5/index.html?v=3\">메인화면</a>");
		out.print("<br/>");
		out.print("<br/>");
		out.print("<br/>");
		out.print("<br/>");
		
		out.print("<h2 style='text-align:center; padding:200px;'>" + lt.getYear()+ ":" + lt.getMonthValue()+":"+lt.getDayOfMonth()+":"+lt.getHour()+":"+lt.getMinute()+"</h2>");
		out.print("</body>");
		out.print("</html>");
	}


}
