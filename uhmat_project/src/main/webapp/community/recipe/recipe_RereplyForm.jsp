<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<section>
		<h1>recipe_RereplyForm.jsp</h1>
		<form action="RecipeRereplyWrite.co" name="RereplyForm" method="post">
<!-- 		<input type="hidden" name="nickname" value="admin"> -->
		<input type="hidden" name="idx" value="${param.reply_idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="board_idx" value="${param.idx }">
		<!-- 답글에 대한 원본글 정보 담고 있는 re_ref, re_lev, re_seq 도 전달 -->
		<input type="hidden" name="re_ref" value="${recipeRereply.re_ref }">
		<input type="hidden" name="re_lev" value="${recipeRereply.re_lev }">
		<input type="hidden" name="re_seq" value="${recipeRereply.re_seq }">
<%-- 		${recipeRereply.re_ref } | ${recipeRereply.re_lev } | ${recipeRereply.re_seq } --%>
<%-- 		| ${param.reply_idx } | ${param.pageNum } | ${param.idx } --%>
		<table>
			<tr>
				<td><input type="text" name="nickname" required="required" ></td>
			</tr>
			<tr>
				<td><textarea rows="10" cols="70" placeholder="댓글을 작성하세요" name="content"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="댓글 전송"></td>
			</tr>
			
		</table>
		</form>
	</section>
</body>
</html>