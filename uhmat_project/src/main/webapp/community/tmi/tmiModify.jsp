<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 자리 -->
	
	<!-- tmi 게시글 수정 -->
	<section>
		<form action="TmiModifyPro.co" name="tmiBoardForm" method="post">
			<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
			<input type="hidden" name="idx" value="${param.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<tr>
					<td><label for="nickname">어맛인</label></td>
					<td><input type="text" name="nickname" value="${tmiBoard.nickname }" required="required"></td>
				</tr>
					
				<tr>
					<td><label for ="subject">제목</label></td>
					<td><input type="text" name="subject" value="${tmiBoard.subject }" required="required" ></td>
				</tr>
					
				<tr>
					<td><label for ="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40" rows="15" required="required" >${tmiBoard.content }</textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
	<!-- 푸터 자리 -->
</body>
</html>