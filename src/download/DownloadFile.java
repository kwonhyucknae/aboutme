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

/**
 * <p><b>Description</b> : 포트폴리오를 다운로드 받을 수 있는 서블릿 클래스 입니다.</p>
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
@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Get 메소드로 들어오는 요청에 응답하는 메소드입니다.<br/>
	 * 프로젝트 내부에 저장되어있는 portfolio_1.pdf 파일의 경로를 얻고<br/>
	 * Stream 을 이용해 사용자가 파일을 다운 받을 수 있도록 구현해줬습니다. 
	 *   
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filename = "test.pdf";
		String pdfPath = request.getSession().getServletContext().getRealPath("/static/file/portfolio_1.pdf");

		File file = new File(pdfPath);

		if (file.exists()) {
			System.out.println("있어");

			try {

				long fileSize = file.length();

				response.setContentType("application/pdf");
				response.setContentLength((int) fileSize);

				response.setHeader("Content-Disposition", "attachment; fileName=" + filename + ";");
				response.setHeader("Content-Length", String.valueOf(fileSize));

				byte b[] = new byte[(int) fileSize];

				BufferedInputStream ins = new BufferedInputStream(new FileInputStream(file));

				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());

				int read = 0;

				while ((read = ins.read()) != -1) {
					outs.write(read);
				}
				outs.flush();
				outs.close();
				ins.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("예외 발생");
			}

		} else {
			System.out.println("없어");
		}

	}

}
