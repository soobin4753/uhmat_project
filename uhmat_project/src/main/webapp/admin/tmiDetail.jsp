<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	#articleForm {
		width: 500px;
		height: 550px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		border: 1px solid black;
		border-collapse: collapse; 
	 	width: 500px;
	}
	
	th {
		text-align: center;
	}
	
	td {
		width: 150px;
		text-align: center;
	}
	
	#basicInfoArea {
		height: 70px;
		text-align: center;
	}
	
	#articleContentArea {
		background: Lightcoral;
		margin-top: 20px;
		height: 350px;
		text-align: center;
		overflow: auto;
		white-space: pre-line;
	}
	
	#commandList {
		margin: auto;
		width: 500px;
		text-align: center;
	}
</style>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script>
// 	$(function(){
// 		$("#Rereply").on("click",function(){
// 			$.ajax({
// 				type: "get",
// 				url: "TmiRereplyWriteForm.co?idx=${tmiReply.idx }&board_idx=${tmiReply.board_idx }&pageNum=${param.pageNum}",
// 				url: "http://localhost:8080/MVC_Board/MemberLoginPro.me", // 서블릿 주소 요청 가능
// 				data: {
// 				},
// 				dataType: "text",
// 				success: function(response) {
// 					$("#resultArea").html(response);
// 				}
// 			});
// 		});
		
// 	});
	
</script>
<title>TMI 글 상세내용</title>
</head>
<body>
	<!-- 헤더 들어가는 곳 -->
	<jsp:include page="../../inc/header.jsp"/>
	<!-- 헤더 들어가는 곳 -->

	<section id="articleForm">
		<h2>TMI 끄적임 상세 내용</h2>
		<section id="basicInfoArea">
		<table border="1">
			<tr><th width="70">제 목</th><td colspan="3" >${tmiBoard.subject }</td></tr>
			<tr>
				<th width="70">작성자</th><td>${tmiBoard.nickname }</td>
				<th width="70">작성일</th><td>${tmiBoard.date }</td>
			<tr>
				<th width="70">조회수</th>
				<td>${tmiBoard.readcount }</td>
			</tr>
			
		</table>
	</section>
	<section id="articleContentArea">
		${tmiBoard.content }
		</section>
	</section>
	
	<hr>
	<section>
		<input type="button" value="수정" onclick="location.href='TmiModifyForm.co?idx=${tmiBoard.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='TmiDeleteForm.co?idx=${tmiBoard.idx}&pageNum=${param.pageNum}'">
	</section>
</body>
</html>