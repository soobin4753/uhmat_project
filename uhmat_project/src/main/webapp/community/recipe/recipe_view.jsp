<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>recipe_view.jsp</h1>
	
	<section>
		<table>
			<tr>
				<td>${recipe.nickname }</td> 
			</tr>
			<tr>
				<td>${recipe.datetime } | ${recipe.readcount }</td> 
			</tr>
	
		</table>
	</section>
	
	<section>
		${recipe.subject }
	</section>
	<!-- 	사진 -->
	<section>
		<table>
			<tr>
				<td width="200"><img src="../recipe_upload/${recipe.real_File1 }"></td>
				<td width="200"><img src="../recipe_upload/${recipe.real_File2 }"></td>
				<td width="200"><img src="../recipe_upload/${recipe.real_File3 }"></td>
				<td width="200"><img src="../recipe_upload/${recipe.real_File4 }"></td>
				<td width="200"><img src="../recipe_upload/${recipe.real_File5 }"></td>
			</tr>
		</table>
	</section>
	<section>
		${recipe.content }
	</section>
	<hr>
	
	<section>
<%-- 		<input type="button" value="댓글" onclick="location.href='MateReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'"> --%>
		<input type="button" value="수정" onclick="location.href='RecipeModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='RecipeDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="목록" onclick="location.href='RecipeList.co?pageNum=${param.pageNum}'">
	</section>
	
</body>
</html>