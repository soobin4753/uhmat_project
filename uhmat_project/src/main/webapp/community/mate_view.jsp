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
	<section>
		<input type="text" placeholder="댓글" name="reply">
		<input type="button" value="댓글 전송">
	</section>
	<section>
		<input type="button" value="수정" onclick="location.href='MateModifyForm.mate?idx=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='MateDeleteForm.mate?board_num=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='MateList.mate?pageNum=${param.pageNum}'">
	
	</section>
	
	
</body>
</html>