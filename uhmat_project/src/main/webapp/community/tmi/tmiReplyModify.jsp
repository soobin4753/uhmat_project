<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#modifyForm {
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
<!-- 게시판 글 수정 -->
	<section id="modifyForm">
		<h1>게시판 글 수정</h1>
		<form action="TmiReplyModifyPro.co" name="tmiReplform" method="post">
			<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
			<input type="hidden" name="idx" value="${param.idx }">
			<input type="hidden" name="board_idx" value="${tmiReply.board_idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="date" value="${tmiReply.date }">
			<table>
				<tr>
					<td class="td_left"><label for="nickname">글쓴이</label></td>
					<td class="td_right">
						<input type="text" name="nickname" value="${tmiReply.nickname }" required="required">
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td class="td_right">
						<textarea rows="15" cols="40" name="content" required="required">${tmiReply.content }</textarea>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>