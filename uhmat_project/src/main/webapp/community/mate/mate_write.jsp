<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 게시판 등록 -->
	<section>
	<h1>community_mate.jsp</h1>
		
	
		<form action="MateWritePro.co" name="MateForm" method="post">
			
			<table>

				<tr>
					<td>닉네임</td>
					<td><input type="text" id="nickname" name="nickname" required="required"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="subject" name="subject" required="required"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" cols="40" id="content" name="content" required="required"></textarea></td>

				</tr>
				
			</table>
			<section>
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>