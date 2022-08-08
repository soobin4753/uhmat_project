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
	<h1>welcome reviewList</h1>

	<nav><a href="ReviewWriteForm.re">글 작성</a></nav>
	<hr>
	<section>
		<table border="2">
				<c:choose>
					<c:when test="${not empty reviewList and pageInfo.listCount gt 0}">
						<c:forEach var="board" items="${reviewList}">
							<tr onclick="location.href='ReviewDetail.re?idx=' + ${board.idx}">
								<td>${board.photo }</td>
								<td>${board.idx }</td>
								<td>${board.nickname }</td>
								<td>${board.subject }</td>
								<td>tag</td>
								<td>${board.rating}</td>
								<td>${board.likes}</td>
								<td>${board.content}</td>
								<!-- 이부분에서 나중에 댓글 항목 추가, 더보기 란 할 수 있도록 해야함 -->
							</tr>
						</c:forEach>
					</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8">게시글이 존재하지 않습니다.</td>
					</tr>
				</c:otherwise>
				</c:choose>
		</table>
	</section>


</body>
</html>