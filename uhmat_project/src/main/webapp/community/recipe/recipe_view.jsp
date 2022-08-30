<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
	<!-- 		헤더 들어가는 곳 -->
	
	<h1>recipe_view.jsp</h1>
	
	<section>
		<table>
			<tr>
				<td>${recipe.nickname } | ${recipe.datetime } | ${recipe.readcount }</td> 
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
<!-- 			onerror="this.style.display='none';" img alt 아이콘 없애는 기능 -->
				<td width="200"><img src="recipe_upload/${recipe.real_File1 }" width="150" onerror="this.style.display='none';"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File2 }" width="150" onerror="this.style.display='none';"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File3 }" width="150" onerror="this.style.display='none';"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File4 }" width="150" onerror="this.style.display='none';"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File5 }" width="150" onerror="this.style.display='none';"></td>
			</tr>
		</table>
	</section>
	<section>
		${recipe.content }
	</section>
	<hr>
	
	<section>
		<input type="button" value="댓글" onclick="location.href='RecipeReplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="수정" onclick="location.href='RecipeModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='RecipeDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&nickname=${recipe.nickname}&file1=${recipe.real_File1 }&file2=${recipe.real_File2 }&file3=${recipe.real_File3 }&file4=${recipe.real_File4 }&file5=${recipe.real_File5 }'">
		<input type="button" value="목록" onclick="location.href='RecipeList.co?pageNum=${param.pageNum}'">
	</section>
	
	<section> 
		<table border="1">
		<c:forEach items="${recipeReplyList }" var="recipeReplyList">
			<tr>
				<td>
					<c:forEach begin="1" end="${recipeReplyList.re_lev }">
						<i class="material-icons" style="font-size:20px;color:red">subdirectory_arrow_right</i>
					</c:forEach>
					${recipeReplyList.nickname }
				</td>
				<td width="500"> ${recipeReplyList.content } </td>
				<td>${recipeReplyList.date } </td>
				<td><input type="button" value="대댓글" onclick="location.href='RecipeRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx} '"></td>
				<td><input type="button" value="댓글삭제" onclick="location.href='RecipeReplyDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx}&nickname=${recipeReplyList.nickname}'"> </td>
				<td><input type="button" value="댓글수정" onclick="location.href='RecipeReplyModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&reply_idx=${recipeReplyList.idx}&nickname=${recipeReplyList.nickname}'"></td>
			</tr>
		</c:forEach>
		</table>
	</section>

	<!-- 		푸터 들어가는 곳 -->
	<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
	
</body>
</html>