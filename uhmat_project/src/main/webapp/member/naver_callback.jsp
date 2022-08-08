
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
 <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script src="../js/jquery-3.6.0.js"></script>
  </head>
  <body>
   <div id="logout">로그아웃</div>
<script type="text/javascript">
  var naver_id_login = new naver_id_login("LQgI_KqqDNAMZNve6EbO", "http://localhost:8080/uhmat_project/member/naver_callback.jsp");
  // 접근 토큰 값 출력
  $('body').append('<h4>접속토큰:'+naver_id_login.oauthParams.access_token+'</h4>');
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    const email = naver_id_login.getProfileData('email');
    const nickname = naver_id_login.getProfileData('nickname');
    const name = naver_id_login.getProfileData('name');
    const age = naver_id_login.getProfileData('age');
    const gender = naver_id_login.getProfileData('gender');
    const birthday = naver_id_login.getProfileData('birthday');
    const birthyear = naver_id_login.getProfileData('birthyear');
    const mobile = naver_id_login.getProfileData('mobile');
    
	let body = $('body');
	body.append('로그인 성공!');
	body.append('<h4>이메일:'+email+'</h4>');
	body.append('<h4>닉네임:'+nickname+'</h4>');
	body.append('<h4>이름:'+name+'</h4>');
	//body.append('<h4>나이:'+age+'</h4>');
	body.append('<h4>성별:'+gender+'</h4>');
	body.append('<h4>생일:'+birthday+'</h4>');
	body.append('<h4>생일:'+birthyear+'</h4>');
	body.append('<h4>전화번호:'+mobile+'</h4>');
  }
	$("#logout").on("click",function(){
  		location.href="https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=LQgI_KqqDNAMZNve6EbO&client_secret=QVWfBxiqNK&access_token="+naver_id_login.oauthParams.access_token+"&service_provider=NAVER";
  		location.href="../index.jsp"
  	});
</script>
  </body>
</html>