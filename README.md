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


# 톰캣 에러 해결과정

깃과 연동해서 사용하다 보면 .classpath 와 .project 파일을 제외해서 깃에 올리게 됩니다.

그 파일들이 제외된 프로젝트를 클론해서 사용해 보면 다이나믹 프로젝트가 자바 프로젝트로 보이게 되서

톰캣으로 실행이 안되는 문제가 발생했습니다.


해결 방법

프로젝트를 다시 다이나믹으로 설정해줘야합니다.

![image](https://user-images.githubusercontent.com/18182656/42551553-bb9f93da-8512-11e8-949e-b010318f4b25.png)

프로젝트 우클릭 properties

![image](https://user-images.githubusercontent.com/18182656/42551655-59e9fd6e-8513-11e8-8a60-5d9ded9465ff.png)

아파치 jar 파일 추가

![image](https://user-images.githubusercontent.com/18182656/42551684-7b19d9dc-8513-11e8-9e02-b2965c05ecb7.png)

jre 경로 다시 설정

![image](https://user-images.githubusercontent.com/18182656/42551703-941e9076-8513-11e8-87a3-0d07f399fa6a.png)

project facet -> 다이나믹과 자바 클릭 후 완료


다시 정상 작동하는것을 알 수 있습니다.


-- 다른 톰캣 오류

이런 오류가 발생하면서 재시작 되지 않습니다.

![image](https://user-images.githubusercontent.com/18182656/42551744-c82e9582-8513-11e8-994d-a11072459faf.png)


![image](https://user-images.githubusercontent.com/18182656/42551772-fec491a0-8513-11e8-800c-11f8dc02a32d.png)


포트가 사용중인 것을 볼 수 있습니다.

![image](https://user-images.githubusercontent.com/18182656/42551787-11814a7c-8514-11e8-9df3-02c8dac5e656.png)

사용중인 포트를 없애면 사용 가능 해집니다.



# javadoc 주석과 일반 클래스 주석 달기


1) JavaDoc주석을 사용하는 경우  /** */
	
     Interface, Class, 멤버함수와 변수 바로 위에서 선언
     
     JavaClass의 Document를 자동 생성하여 활용하는 경우 필요한 정보를
     
     기술합니다. 작성된 Document는 이후 이해하기 쉬운 용어를 이용하여 기술합니다.
     
2) 일반Class 주석을 사용하는 경우

     코드 그 자체로 쉽게 이해할 수 없는 부분을 자세히 설명해 준다. 코드 내에서
     
     사용되는 변수의 의미 설명을 한다
     
   // 기본적으로 멀티라인 주석을 사용하는 것을 원칙으로 하나 임시로 사용하거나
           
      간단한주석처리 시 사용
   /* */ 
      더 이상 적용하지 않는 코드 라인들에 코멘트처리를 하거나 Method 안의
      변수 또는 코드에 주석을 달고자 할 때 사용
	

파라미터 사용법

	/**
	 * @see class_name : "See also"라는 항목을 만들어 해당 클래스와 연결해준다.
	 * @see class_name#method_name : "See also"라는 항목을 만들어 특정 메소드와 연결해준다.
	 * @version text : HTML 파일의 버전을 표시하는 항목을 만들어 준다.
	 * @author text : HTML 파일의 저자를 표시하는 항목을 만들어 준다. 
	 *
	 * @param name description : 특정 메소드가 취하는 파라메터를 기술할 때 사용된다.
	 * @return description : 특정 메소드의 리턴값을 기술할 때 사용된다.
	 * @exception class_name : 특정 메소드가 발생시킬 수 있는 에외상황을 기술할 때 사용된다.
	*/


![image](https://user-images.githubusercontent.com/18182656/42613861-076708fe-85de-11e8-8c22-7b1432b6efe3.png)



출처: http://wjsb.tistory.com/26 [우주신비 드림 팩토리]




# 라이센스
