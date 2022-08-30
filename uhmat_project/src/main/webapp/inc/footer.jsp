<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/5382a0d7e0.js" crossorigin="anonymous"></script>
</head>
<body>
<div id="container">	
	<footer>
		<!-- 로고 들어가는 곳 -->
		<div class="incLogo">
			<img src="image/incLogo.jpg">
		</div>
		<div class="incText">
			<p>여가그가(주)</p>
		</div>	
		<!-- 로고 들어가는 곳 -->	
			
			<div class="footerMenu">
				<table id="footerTable"> 
					<tr>
						<td id="whatUhmat"><a href="CompanyDetail.cp">어맛 소개</a></td>
						<td id="notice"><a href="NoticeList.sc">공지사항</a></td>
					</tr>
					<tr>
						<td id="whoDeveloper"><a href="DeveloperDetail.cp">개발자 소개</a></td>
						<td id="serviceCenter"><a href="NoticeList.sc">고객센터</a></td>
					</tr>
					<tr>
						<td id="policy"><a href="Policy.cp">이용약관</a></td>
						<td id="event"><a href="#">이벤트</a></td>
					<tr>
				</table>
			</div>
			
		<p class="contact">
			<span id="contactUs">CONTACT US</span></br>
			<span id="call">051 - 803 - 0909</span>
			<span id="faqTime">문의 가능 시간</span><br>
			<span id="time">09:00 - 18:00</span>
		</p>
		
		<div class="sns">
<!-- 			<img class="insta" src="" onclick="()"> -->
<!-- 			<img class="twitter" src="" onclick="()"> -->
<!-- 			<img class="facebook" src="" onclick="()"> -->
				<a href="" class="fa-brands fa-instagram"></a>&nbsp;
				<a href="" class="fa-brands fa-twitter"></a>&nbsp;
				<a href="" class="fa-brands fa-facebook" ></a>
		</div>
		
		<div class="address">
			<p>부산광역시 부산진구 동천로 109 삼한골든게이트빌딩 7층(접수)</p>
		</div>
		<div class="copyright">
			<p>COPYRIGHT (C)Yeogageuga co., itd. ALL RIGHTS RESERVED.</p>
		</div>
		
		<div class="languageTranslate">
			한국어 | 日本語 | English
		</div>
		
	</footer>
</div>	
</body>
</html>