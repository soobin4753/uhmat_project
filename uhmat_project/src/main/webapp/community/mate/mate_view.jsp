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
	<h1>mate_view.jsp</h1>
	<!-- 	<h1>깃 놈,, 당장 커밋해라</h1>	 -->
	
	<section>
		<table>
			<tr>
				<td>${mate.nickname }</td> 
			</tr>
			<tr>
				<td>${mate.datetime } | ${mate.readcount }</td> 
			</tr>
<!-- 			<tr> -->
<%-- 				<td colspan="5" width="800" hight="50">${mate.subject }</td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<%-- 				<td colspan="5" width="800" height="400">${mate.content }</td> --%>
<!-- 			</tr> -->
		</table>
	</section>
	<section>
		${mate.subject }
	</section>
	<section>
		${mate.content }
	</section>
	<hr>
<!-- 	<section> -->
<!-- 		<input type="text" placeholder="댓글쓰세요" name="reply"> -->
<!-- 		<input type="button" value="댓글 전송"> -->
<!-- 	</section> -->
	<section>
		<input type="button" value="댓글" onclick="location.href='MateReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="수정" onclick="location.href='MateModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='MateDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='MateList.co?pageNum=${param.pageNum}'">
	
	</section>


	<section>
		<table>
		<c:forEach items="${mateReplyList }" var="mateReplyList">
			<tr>
				<td>${mateReplyList.nickname }</td>
			</tr>
			<tr>
				<td>${mateReplyList.content }</td>
			</tr>
			<tr>
				<td>${mateReplyList.date }</td>
			</tr>
		</c:forEach>
		</table>
	</section>
	
</body>
</html>