<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RecipeDeletePro.co?idx=${param.idx }&pageNum=${param.pageNum }&nickname=${param.nickname }" name="deleteForm" method="post">
<%-- 	<input type="hidden" name="file1" value="${recipe.real_File1 }"> --%>
	
	<h1>글 삭제 하시겠습니까?</h1>
<!-- 	<input type="password" placeholder="패스워드를 적으시오"> -->
	<input type="submit" value="삭제" >&nbsp;&nbsp;
	<input type="button" value="돌아가기" onclick="javascript:history.back()">
	</form>
	
</body>
</html>