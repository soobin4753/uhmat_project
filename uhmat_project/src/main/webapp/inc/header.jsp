<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/header.css" rel="stylesheet">
<script type="text/javascript">
	function confirmLogout() {
		if(confirm("로그아웃 하시겠습니까?")) { // 확인 : true, 취소 : false 리턴됨
			// MemberLogout.me 포워딩
			location.href = "MemberLogout.me";
		}
	}
</script> 
</head>
<body>
	<header>
		<!-- 로고들어가는 곳 -->
    	<div id="logo"><img src="#"></div>
		<!-- 로고들어가는 곳 -->
		
		<!-- 로그인 및 회원가입 부분 -->
		<c:choose>
			<c:when test="${empty sessionScope.sId}">
				<a href="MemberLoginForm.me">로그인</a> &nbsp;<a href="MemberJoinForm.me">회원가입</a>
			</c:when>
			<c:otherwise>
				${sessionScope.sId } 님 &nbsp;<a href="javascript:void(0)" onclick="confirmLogout()">로그아웃</a>
				<%-- 세션 아이디가 "admin" 일 때만 관리자페이지 링크("AdminMain.me") 표시 --%>
				<c:if test="${sessionScope.sId eq 'admin'}"><a href="AdminMain.me">| 관리자페이지</a></c:if>
			</c:otherwise>
		</c:choose>
		<!-- 로그인 및 회원가입 부분 -->
		
	<!-- 탑 메뉴 부분 -->
	<div class="topMenu">
    <ul>
    	<li>
    	<div class="dropdown">
    		<a href="#">회사소개</a>
    			<div class="dropdownContent">
				<ul>
					<li><a href="#">link1</a></li>
				</ul>	
				</div>
  	  	</div>
  	  	</li>
  	  	
  	  	<li>
  	  	<div class="dropdown">
    		<a href="#">어맛리뷰</a>
    			<div class="dropdownContent">
				<ul>
					<li><a href="#">link2</a></li>
					<li><a href="#">link2</a></li>
					<li><a href="#">link2</a></li>
					<li><a href="#">link2</a></li>
					<li><a href="#">link2</a></li>
				</ul>	
				</div>
  	  	</div>
  	  	</li>
  	  	
  	  	<li>
  	  	<div class="dropdown">
    		<a href="#">커뮤니티</a>
    			<div class="dropdownContent">
				<ul>
					<li><a href="#">어맛 메이트</a></li>
					<li><a href="#">어맛인들의 tmi</a></li>
					<li><a href="#">어맛 레시피</a></li>
				</ul>	
				</div>
  	  	</div>
  	  	</li>
  	  	
  	  	<li>
  	  	<div class="dropdown">
    		<a href="#">이벤트</a>
    			<div class="dropdownContent">
				<ul>
					<li><a href="#">이벤트</a></li>
					<li><a href="#">어맛 MBTI</a></li>
					<li><a href="#">어맛룰렛/사다리</a></li>
				</ul>	
				</div>
  	  	</div>
  	  	</li>	
  	  	
  	  	<li>
  	  	<div class="dropdown">
    		<a href="#">고객센터</a>
    			<div class="dropdownContent">
				<ul>
					<li><a href="#">1 대 1 라이브 채팅</a></li>
					<li><a href="#">FAQ</a></li>
					<li><a href="#">공지사항</a></li>
				</ul>	
				</div>
  	  	</div>
  	  	</li> 
	  		
  	</ul>
    </div>
   
	<!-- 탑 메뉴 부분 -->
	
	
	<!-- 검색 창 부분 -->
	<div class="container">
		<div class="row">
			<form method="post" name="search" action="">
				<table class="pull-right">
					<tr>
						<td><input type="text" class="form-control"
							placeholder="검색할 음식명이나 음식점명" name="searchText" maxlength="100"></td>
						<td><button type="submit" class="btn btn-success">검색</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 검색 창 부분 -->
</header>
</body>
</html>   