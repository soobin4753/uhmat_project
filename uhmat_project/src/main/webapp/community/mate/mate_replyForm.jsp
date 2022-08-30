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
		<h1>mate_commentForm.jsp</h1>
		<form action="MateReplyWrite.co?idx=${param.idx }&pageNum=${param.pageNum}" name="replyForm" method="post">
<!-- 		<input type="hidden" name="nickname" value="admin"> -->
		<input type="hidden" name="idx" value="${param.idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<!-- 답글에 대한 원본글 정보 담고 있는 re_ref, re_lev, re_seq 도 전달 -->
<%-- 		<input type="hidden" name="re_ref" value="${mateReply.re_ref }"> --%>
<%-- 		<input type="hidden" name="re_lev" value="${mateReply.re_lev }"> --%>
<%-- 		<input type="hidden" name="re_seq" value="${mateReply.re_seq }"> --%>
		
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
	
	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
</body>
</html>