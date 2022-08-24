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
	<!-- 헤더 자리 -->
	
	<!-- 게시글 등록 -->
	<section>
		<form action="TmiDeletePro.co" name="deleteTmiForm" method="post">
			<!-- 입력받지 않은 글번호(board_num)와 페이지번호(pageNum)도 함께 포함시켜 전달 -->
			<input type="hidden" name="idx" value="${param.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<tr>
					<td><label>닉네임</label></td>
					<td><input type="text" name="nickname" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
	<!-- 푸터 자리 -->
</body>
</html>