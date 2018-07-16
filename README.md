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



DateTimeFormatter를 static 을 이용해 전역 변수로 만들어 성능 향상을 시킨 코드입니다.

<pre><code>

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	
</pre></code>

 DateTimeFormatter 객체를 static final 로 사용한 이유 입니다.
 
 서블릿은 요청이 들어올 때 마다 쓰레드를 호출하게 됩니다.
 
 각각의 쓰레드는 스택 메모리를 가지고 있는데 모든 쓰레드에서 ofPattern 함수를 호출하게 되면 
 
 각각의 스택에 메모리가 쌓이면서 성능이 저하 될 거라고 생각했습니다.
 
 따라서 미리 static 전역 변수로 설정을 해서 모든 쓰레드들이 이미 만들어져 있는
 
 DateTimeFormatter 객체를 공동으로 사용해서 성능의 향상을 생각했습니다.
  
  
 이 때 많은 쓰레드들이 동시에 DateTimeFormatter 객체를 사용할 때를 생각해봐야하는데
 
 DateTimeFormatter 의 javadoc 을 봐보게 되면 마지막 부분에 
 
 This class is immutable and thread-safe. 라는 것을 볼 수가 있었습니다.
 
 thread-safe 하다는 것이 뭔지 찾아보니 임계 영역을 사용하려고 할 때 뮤텍스등으로 
 
 공유 자원을 보호 해서 쓰레드를 사용해도 안전하다고 되어있었습니다.
 
 따라서 개발자가 임계 영역을 따로 관리 하지 않고 사용해도 괜찮다고 되어있어서
 
 static 으로 DateTimeFormatter를 전역 변수로 사용해 미리 만들어 놓고 사용했습니다.
 
  
 final 을 사용한 이유는 format 형식이 이 프로젝트에서는 바뀌지 않기 때문에 사용해줬습니다.
  
  
  
 크롬 개발자 도구를 사용해 성능을 측정해보니 load 하는데 static final 을 사용했을때는 11ms로 불러오고
 
 static final 을 사용하지 않았을 때는 12ms 로 불러와서 조금 더 빠른 속도로 불러오는 것을 볼 수 있었습니다.
 
 한 번 불러올 때는 속도 차이가 크지 않지만 동시에 많은 요청이 들어왔을때는 속도차이가 더 크게 날 것이라 생각합니다. 
 



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


![image](https://user-images.githubusercontent.com/18182656/42613829-e5059276-85dd-11e8-9907-19f64c6abb66.png)



출처: http://wjsb.tistory.com/26 [우주신비 드림 팩토리]




# 라이센스
