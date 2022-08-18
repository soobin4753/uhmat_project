<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
				<td width="200"><img src="recipe_upload/${recipe.real_File1 }"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File2 }"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File3 }"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File4 }"></td>
				<td width="200"><img src="recipe_upload/${recipe.real_File5 }"></td>
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
		<input type="button" value="삭제" onclick="location.href='RecipeDeleteForm.co?idx=${recipe.idx}&pageNum=${param.pageNum}&nickname=${recipe.nickname}'">
		<input type="button" value="목록" onclick="location.href='RecipeList.co?pageNum=${param.pageNum}'">
	</section>
	
<!-- 	<section>  -->
<!-- 		<table border="1"> -->
<%-- 		<c:forEach items="${mateReplyList }" var="mateReplyList"> --%>
<!-- 			<tr> -->
<!-- 				<td> -->
<%-- 					<c:forEach begin="1" end="${mateReplyList.re_lev }"> --%>
<!-- 						<i class="material-icons" style="font-size:20px;color:red">subdirectory_arrow_right</i> -->
<%-- 					</c:forEach> --%>
<%-- 					${mateReplyList.nickname } --%>
<!-- 				</td> -->
<%-- 				<td width="500"> ${mateReplyList.content } </td> --%>
<%-- 				<td>${mateReplyList.date } </td> --%>
<%-- 				<td><input type="button" value="대댓글" onclick="location.href='MateRereplyForm.co?idx=${param.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx} '"></td> --%>
<%-- 				<td><input type="button" value="댓글삭제" onclick="location.href='MateReplyDeleteForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"> </td> --%>
<%-- 				<td><input type="button" value="댓글수정" onclick="location.href='MateReplyModifyForm.co?idx=${mate.idx}&pageNum=${param.pageNum}&reply_idx=${mateReplyList.idx}&nickname=${mateReplyList.nickname}'"></td> --%>
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
<!-- 		</table> -->
<!-- 	</section> -->
	<!-- 		푸터 들어가는 곳 -->
		<jsp:include page="../../inc/footer.jsp"/>
	<!-- 		푸터 들어가는 곳 -->
</body>
</html>