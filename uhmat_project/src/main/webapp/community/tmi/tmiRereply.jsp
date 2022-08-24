
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#replyForm {
		width: 500px;
		height: 450px;
		border: 1px solid red;
		margin: auto;
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
		text-align: center;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 게시판 답글 작성 -->
	<section id="replyForm">
		<h1>댓글의 답글 작성</h1>
		<form action="TmiRereplyWritePro.co" name="TmiRereplyForm" method="post">
			<!-- 글번호와 페이지번호 전달 -->
			<input type="hidden" name="board_idx" value="${tmiRereply.board_idx }">
			<input type="hidden" name="idx" value="${tmiRereply.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<!-- 답글에 대한 원본글 정보 담고 있는 re_ref, re_lev, re_seq 도 전달 -->
			<input type="hidden" name="re_ref" value="${tmiRereply.re_ref }">
			<input type="hidden" name="re_lev" value="${tmiRereply.re_lev }">
			<input type="hidden" name="re_seq" value="${tmiRereply.re_seq }">
			<table>
				<tr>
					<td class="td_left"><label for="nickname">글쓴이</label></td>
					<td class="td_right">
						<input type="text" name="nickname" value="${tmiRereply.nickname }" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">댓글 내용</label></td>
					<td class="td_right">
						<input type="text" name="cotent" value="Re:${tmiRereply.content }" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td class="td_right">
						<textarea id="content" name="content" cols="40" rows="15" required="required"></textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답글등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>








