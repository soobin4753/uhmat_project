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
	
	<!-- 게시글 등록 -->
	<h3>어맛? 주절주절</h3>
	<section>
		<form action="TmiWritePro.co" name=tmiBoardForm method="POST">
		<table>
			<tr>
				<td><label for="nickname">어맛인</label></td>
				<td><input type="text" name="nickname" id="nickname" required="required"></td>
			</tr>
				
			<tr>
				<td><label for ="subject">제목</label></td>
				<td><input type="text" name="subject" id="subject" required="required" ></td>
			</tr>
				
			<tr>
				<td><label for ="content">내용</label></td>
				<td><textarea id="content" name="content" cols="40" rows="15" required="required" ></textarea></td>
			</tr>
		</table>
		<br>
		<section>
			<input type="submit" value="등록">&nbsp;&nbsp;
			<input type="reset" value="다시쓰기" />&nbsp;&nbsp;
			<input type="button" value="취소" onclick="history.back()">
		</section>
		</form>
	</section>
	<!-- 푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 푸터 들어가는 곳 -->
</body>
</html>