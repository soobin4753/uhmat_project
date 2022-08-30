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
	
	<section>
		<h1>recipe_replyForm.jsp</h1>
		<form action="RecipeReplyWrite.co?idx=${param.idx }&pageNum=${param.pageNum}" name="replyForm" method="post">
<!-- 		<input type="hidden" name="nickname" value="admin"> -->
		<input type="hidden" name="idx" value="${param.idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		
		<table>
			<tr>
				<td><input type="text" name="nickname" required="required" ></td>
			</tr>
			<tr>
				<td><textarea rows="10" cols="70" placeholder="댓글을 작성하세요" name="content"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="댓글 전송"></td>
			</tr>
			
		</table>
		</form>
	</section>
	
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 푸터 들어가는 곳 -->
</body>
</html>