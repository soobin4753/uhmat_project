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
		<input type="button" value="수정" onclick="location.href='RecipeModifyForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}'">
		<input type="button" value="삭제" onclick="location.href='RecipeDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&nickname=${recipe.nickname}&file1=${recipe.real_File1 }&file2=${recipe.real_File2 }&file3=${recipe.real_File3 }&file4=${recipe.real_File4 }&file5=${recipe.real_File5 }'">
	</section>
	
	
</body>
</html>