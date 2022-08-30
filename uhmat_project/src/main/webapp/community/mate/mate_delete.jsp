<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->

	<form action="MateDeletePro.co?idx=${param.idx }&pageNum=${param.pageNum}" name="deleteForm" method="post">
	<h1>글 삭제 하시겠습니까?</h1>
<!-- 	<input type="password" placeholder="패스워드를 적으시오"> -->
	<input type="submit" value="삭제" >&nbsp;&nbsp;
	<input type="button" value="돌아가기" onclick="javascript:history.back()">
	</form>
	
	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
	
</body>
</html>