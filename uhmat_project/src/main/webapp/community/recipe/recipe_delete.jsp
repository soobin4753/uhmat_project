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
	
<%-- ?idx=${param.idx }&pageNum=${param.pageNum }&nickname=${param.nickname } --%>
	<form action="RecipeDeletePro.co" name="deleteForm" method="post">
	<input type="hidden" name="idx" value="${param.idx }">
	<input type="hidden" name="pageNum" value="${param.pageNum }">
	<input type="hidden" name="nickname" value="${param.nickname }">
	<input type="hidden" name="file1" value="${param.file1 }">
	<input type="hidden" name="file2" value="${param.file2 }">
	<input type="hidden" name="file3" value="${param.file3 }">
	<input type="hidden" name="file4" value="${param.file4 }">
	<input type="hidden" name="file5" value="${param.file5 }">
	
	<h1>글 삭제 하시겠습니까?</h1>
<!-- 	<input type="password" placeholder="패스워드를 적으시오"> -->
	<input type="submit" value="삭제" >&nbsp;&nbsp;
	<input type="button" value="돌아가기" onclick="javascript:history.back()">
	</form>
	
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 푸터 들어가는 곳 -->
	
	
</body>
</html>