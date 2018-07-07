package download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
		
		//String filename=request.getParameter("filename");
		String filename="test.pdf";
		
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
			
			try {
				
				long fileSize=file.length();
				
				response.setContentType("application/pdf");
				response.setContentLength((int)fileSize);
				
				response.setHeader("Content-Disposition", "attachment; fileName="+filename+";");
				response.setHeader("Content-Length", String.valueOf(fileSize));
				
				byte b[] =new byte[(int)fileSize];
				
				BufferedInputStream ins = new BufferedInputStream(new FileInputStream(file));
				
				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
				
				int read=0;
				
				while((read=ins.read())!= -1)
				{
					outs.write(read);
				}
				outs.flush();
				outs.close();
				ins.close();
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("예외 발생");
			}
		
		}
		else {
			System.out.println("없어");
		}
		
		
	}

	
}
