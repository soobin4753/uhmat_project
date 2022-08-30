<%@page import="vo.MateReplyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<section>
		<table>
			<tr>
				<td>${mate.nickname }</td> 
			</tr>
			<tr>
				<td>${mate.date } | ${mate.readcount }</td> 
			</tr>
	
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
		<input type="button" value="댓글" onclick="location.href='MateReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="수정" onclick="location.href='MateModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='MateDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
	
	</section>
	
	
	
	
</body>
</html>