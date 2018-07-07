package download;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DownloadFile() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filename=request.getParameter("filename");
		
		//String uploadFilePath="./static/file/portfolio.pptx";
		//URL res = getClass().getClassLoader().getResource("/static/file");
		
		
		//System.out.println(res);
		
		//Set pathSet = request.getSession().getServletContext().getResourcePaths("/static/file");

		//System.out.println(pathSet);	
		
		String pdfPath = request.getSession().getServletContext().getRealPath("/static/file/portfolio.pdf");
		//System.out.println(pdfPath);
		//System.out.println(new File(pdfPath).exists());
		
		
		File file = new File(pdfPath);
		
		if(file.exists())
		{
			System.out.println("있어");
		}
		else {
			System.out.println("없어");
		}
		
		
	}

	
}
