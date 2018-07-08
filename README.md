# aboutme


자기 소개 웹 페이지 입니다.



# 결과물

![pic1](https://user-images.githubusercontent.com/18182656/42418316-419d5446-82d8-11e8-87bd-ead1ad6a437c.PNG)


![pic2](https://user-images.githubusercontent.com/18182656/42418318-454799b2-82d8-11e8-8276-c1da8c32c235.PNG)




# 공부한 코드

로컬 내부에 저장 되어있는 (프로젝트 내부에 저장 되어있는) 

pdf 파일 다운로드

*경로를 받는 request 메소드 기억


<pre><code>

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filename="test.pdf";
		String pdfPath = request.getSession().getServletContext().getRealPath("/static/file/portfolio_1.pdf");

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



</pre></code>


# 라이센스
