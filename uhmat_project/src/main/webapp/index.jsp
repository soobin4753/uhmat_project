<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>
<body>
 


<h3> <a href="MemberJoinForm.me">회원가입</a></h3>
<h3> <a href="MemberLoginForm.me">로그인</a></h3>
<h3> <a href="MemberLogout.me">로그아웃</a></h3>
<c:choose>
			<c:when test="${empty sessionScope.sEmail}">
				<a href="MemberLoginForm.me">로그인</a> | <a href="MemberJoinForm.me">회원가입</a>
			</c:when>
			<c:otherwise>
				<%-- 하이퍼링크에 자바스크립트 함수 연결 시 href 속성에 아무 경로도 지정하지 않는 방법 --%>
				${sessionScope.sEmail } 님 | <a href="MemberLogout.me" >로그아웃</a>
				<%-- 세션 아이디가 "admin" 일 때만 관리자페이지 링크("AdminMain.me") 표시 --%>
				<c:if test="${sessionScope.sEmail eq 'admin'}"> | <a href="AdminMain.me">관리자페이지</a></c:if>
			</c:otherwise>
</c:choose>

 <nav>
 	<details> <!-- 음식카테고리 리스트 -->
 		<summary>음식카테고리</summary>
 		<a href="RestaurantInfo.re">음식으로 보기</a>
 		<a href="ReviewList.re">리뷰로 보기</a>
 		<a href="#">지도로 보기</a>
 	</details>
 </nav>
<h3> <a href="NoticeList.sc">Notice</a></h3>
<h3> <a href="FAQList.sc">FAQ</a></h3>
 <h3><a href="MateWriteForm.mate">글쓰기</a></h3>
<h3><a href="MateList.mate">글목록</a></h3>



</body>
</html>   