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
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style type="text/css">
	
	
	.view {
		padding: 20px 20px 20px 20px;
		border-left: 1px solid #ccc;
		border-right: 1px solid #ccc;
		margin: auto;
		width: 800px;
		text-align: left;
	}
	
	
	.subject {
		font-size: x-large;
	}
	
	.nickname_time_readcount {
		font-size: small;
	}
	
	.btn input {
		border: 2px solid #7FB77E;
		background-color: white;
		color: #7FB77E;
		padding: 5px;
		border-radius: 5px
	}
	
	.btn input:hover {
		background-color: #7FB77E;
		color: white;
	}
	
	.reply {
		border-bottom: 2px solid #ccc;
	}
	


</style>
</head>
<body>
	<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 		헤더 들어가는 곳 -->
	
	<div class="view">
		<table width="800">
			<tr>
				<td class="subject">${mate.subject }</td> 
			</tr>
			<tr>
				<td class="nickname_time_readcount"><i class='fas fa-user-alt'></i> ${mate.nickname } | <i class="fa fa-clock-o"></i> ${mate.datetime } | <i class='far fa-eye'></i> ${mate.readcount }</td> 
			</tr>
			<tr>
				<td>${mate.content }</td>
			</tr>
	
		</table>
	
	&nbsp;
	
	<div class="btn">
		<input type="button" value="댓글" onclick="location.href='MateReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="수정" onclick="location.href='MateModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='MateDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='MateList.co?pageNum=${param.pageNum}'">
	
	</div>
	
	&nbsp;
	 
		<table>
		<c:forEach items="${mateReplyList }" var="mateReplyList">
			<tr>
				<td>
					<c:forEach begin="1" end="${mateReplyList.re_lev }">
						&nbsp;&nbsp;<i class="material-icons" style="font-size:20px;color:#7FB77E">subdirectory_arrow_right</i>
					</c:forEach>
					${mateReplyList.nickname } | ${mateReplyList.date }
				</td>
				
			</tr>
			<tr>
				<td width="500" class="reply"> ${mateReplyList.content } </td>
				<td class="btn"><input type="button" value="대댓글" onclick="location.href='MateRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx} '"></td>
				<td class="btn"><input type="button" value="댓글삭제" onclick="location.href='MateReplyDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"> </td>
				<td class="btn"><input type="button" value="댓글수정" onclick="location.href='MateReplyModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"></td>
			</tr>
		</c:forEach>
		</table>
	</div>
	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
</body>
</html>