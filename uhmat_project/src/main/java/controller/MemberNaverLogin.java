package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import action.Action;
import action.member.MemberAuthAction;
import action.member.MemberChechDuplicateEmailAction;
import action.member.MemberChechDuplicateNickNameAction;
import action.member.MemberJoinProAction;
import action.member.MemberLoginProAction;
import action.member.MemberLogoutAction;
import action.member.MemberSendAuthMailAction;
import vo.ActionForward;


@WebServlet("/MemberNaverLogin")
public class MemberNaverLogin extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null; 
		Action action = null;
		String clientId = "LQgI_KqqDNAMZNve6EbO";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "QVWfBxiqNK";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8080/uhmat_project/MemberNaverLogin", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    
	    System.out.println("apiURL="+apiURL+"\n");
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode+"\n");
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    		System.out.println(res.toString()+"\n");
	    	  JSONParser parsing = new JSONParser();
	    		Object obj = parsing.parse(res.toString());
	    		JSONObject jsonObj = (JSONObject)obj;
	    		access_token = (String)jsonObj.get("access_token");
	    		refresh_token = (String)jsonObj.get("refresh_token");
	    		String header = "Bearer " + access_token;
	    	
	    		if(access_token != null) { // access_token을 잘 받아왔다면
	    			try {
	    				String apiurl = "https://openapi.naver.com/v1/nid/me";
	    				URL url2 = new URL(apiurl);
	    				HttpURLConnection con2 = (HttpURLConnection)url2.openConnection();
	    				con2.setRequestMethod("GET");
	    				con2.setRequestProperty("Authorization", header);
	    				int responseCode2 = con.getResponseCode();
	    				BufferedReader br2;
	    				if(responseCode2==200) { // 정상 호출
	    				 br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
	    				} else {  // 에러 발생
	    				br2 = new BufferedReader(new InputStreamReader(con2.getErrorStream()));
	    				}
	    				String inputLine2;
	    				StringBuffer res2 = new StringBuffer();
	    				 while ((inputLine2 = br2.readLine()) != null) {
	    				res2.append(inputLine2);
	    				}
	    				br2.close();
	    				if(responseCode2==200) { // 정상 호출
	    					System.out.println(res2.toString()+"\n");
	    					JSONParser parsing2 = new JSONParser();
	    			    	Object obj2 = parsing2.parse(res2.toString());
	    			    	JSONObject jsonObj2 = (JSONObject)obj2;
	    					JSONObject resObj2 = (JSONObject)jsonObj2.get("response");	        
	    		    		
	    					  
	    		    		String naverCode = (String)resObj2.get("id");
	    		    		String email = (String)resObj2.get("email");
	    		    		String name = (String)resObj2.get("name");
	    		    		String nickName = (String)resObj2.get("nickname");
	    		    		String gender = (String)resObj2.get("gender");
	    		    		String age = (String)resObj2.get("age");
	    		    		String birthday = (String)resObj2.get("birthday");
	    		    		String birthyear = (String)resObj2.get("birthyear");
	    		    		String mobile = (String)resObj2.get("mobile");
	    		    		System.out.println("네이버코드 = "+naverCode+ " 이메일 :"+ email 
	    		    				+" 이름 : "+ name+" 닉네임 : "+nickName+" 성별 : "+ gender+" 나이 : "+age
	    		    				+" 생일 : "+ birthyear+" 생일년도 : "+birthday+" 핸드폰 : "+ mobile);
		    				}
	    		    } catch (Exception e) {
	    		    	e.printStackTrace();
	    		    }
	    		}
	    		
	    	
	    	
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
		forward= new ActionForward();
		forward.setPath("/member/naver_callback.jsp");
		forward.setRedirect(false);

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

}
