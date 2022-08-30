<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	.write_table {
		border: 2px solid #7FB77E;
		padding: 20px 20px 20px 20px;
		border-radius: 10px
	}
	
	.text {
		border: 2px solid #ccc;
		border-radius: 5px;
	}
	
	.btn {
		border: 2px solid #7FB77E;
		background-color: white;
		color: #7FB77E;
		padding: 5px;
		border-radius: 5px
	}
	
	.btn:hover {
		background-color: #7FB77E;
		color: white;
	}
	
	
</style>
</head>
<body>
	<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 		헤더 들어가는 곳 -->
	&nbsp;
	<!-- 게시판 등록 -->
	<div align="center">
		<form action="MateWritePro.co" name="MateForm" method="post">
			
			<table class="write_table">
				<tr>
					<td>닉네임</td>
					<td><input type="text" id="nickname" name="nickname" required="required" class="text"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="subject" name="subject" required="required" class="text"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" cols="40" id="content" name="content" required="required" class="text"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="등록" class="btn"></td>
					<td><input type="reset" value="다시쓰기" class="btn"></td>
					<td><input type="button" value="취소" onclick="history.back()" class="btn"></td>
				</tr>
			</table>
				
		<!-- 		푸터 들어가는 곳 -->
		<jsp:include page="../../inc/footer.jsp"/>
		<!-- 		푸터 들어가는 곳 -->	
					
		</form>
	</div>
</body>
</html>