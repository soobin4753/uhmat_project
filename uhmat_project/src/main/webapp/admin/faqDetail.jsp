<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 글 보기</title>
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
		background: orange;
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
</head>
<body>
	<!-- 게시판 상세내용 보기 -->
	<section id="articleForm">
		<h2>글 상세내용 보기</h2>
		<section id="basicInfoArea">
				<table border="1">
					<tr><th width="70">제 목</th><td colspan="3" >${faq.subject }</td></tr>
					<tr>
						<th width="70">작성자</th><td>${faq.nickname }</td>
						<th width="70">작성일</th><td>${faq.date }</td>
					</tr>
					<tr>
						<th>조회수</th><th>${faq.readcount }</th>
					<tr>
						<th width="70">첨부파일</th>
						<td>
						<!-- 
						파일명은 원본 파일명을 표시하고, 다운로드 파일 대상은 실제 업로드 파일명,
						실제 다운로드 되는 파일명은 원본 파일명으로 변경하여 다운로드
						-->
							<a href="upload/${faq.real_File }" download="${faq.original_File }">
							${faq.real_File }
							</a>
						</td>
					</tr>
				</table>
		</section>
		<br><br>
		<section id="articleContentArea">
			${faq.content }
		</section>
	</section>
	<section id="replyArea">
		<!-- insertForm 섹션(댓글 작성 영역)은 세션 아이디가 존재할 경우에만 출력 -->

	<section id="insertForm">
		<c:choose>
			<c:when test="${not empty reply.board_idx}">
			
			</c:when>
			<c:otherwise>
				<form action="FAQDetailReply.sc" style="position: relative; left: 40%; top:50%;">
					<!-- 댓글 전송 시 현재 게시물 글번호(idx)도 함께 전송 -->
					<input type="hidden" name="idx" value="${param.idx }">
					<!-- 댓글 전송 시 현재 게시물 닉네임(nickname) 함께 전송 -->
					<input type="hidden" name="nickname" value="${faq.nickname }">
					<!-- 페이지번호도 함께 전송 -->
					<input type="hidden" name="pageNum" value="${param.pageNum}">
					<textarea rows="3" cols="50" name="answer"></textarea>
					<input type="submit" value="등록">
				</form>
			</c:otherwise>
		</c:choose>	
	</section>

		<section id="replyViewArea" style="position: relative; left: 40%; top:50%;">
			<!-- ArrayList(replyList) 객체 크기만큼 for문 반복 -->
			<br>
			     <table>
			     	<tr>
				     	<td>답변 : </td><td> ${reply.answer }</td>
				     	<td><input type="button" value="삭제" onclick="location.href='FAQDetailReplyDelete.sc?idx=${faq.idx}&pageNum=${param.pageNum}'"></td>

			     	</tr>
			     </table>
			<br>
		</section>
	</section>
	<section id="commandList" >
		<input type="button" value="수정" onclick="location.href='FAQModifyForm.sc?idx=${faq.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='FAQDelete.sc?idx=${faq.idx}&pageNum=${param.pageNum}'">
	</section>
</body>
</html>
















