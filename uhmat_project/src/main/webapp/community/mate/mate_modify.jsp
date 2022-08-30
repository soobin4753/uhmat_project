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
	
	<form action="MateModifyPro.co" name="MateForm" method="post">
		<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
		<input type="hidden" name="idx" value="${mate.idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		
		<table>
				<tr>
					<td>닉네임</td>
					<td><input type="text" id="nickname" name="nickname" value="${mate.nickname }" required="required" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="subject" name="subject" value="${mate.subject }" required="required"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" cols="40" id="content" name="content" required="required"> ${mate.content }</textarea></td>
				</tr>
				
			</table>
			<section>
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			
			</section>
	</form>
</body>
</html>