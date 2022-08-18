<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>

</head>
<body>
		
	<section id="writeForm">
<!-- 		헤더 들어가는 곳 -->
		<jsp:include page="../../inc/header.jsp"/>
<!-- 		헤더 들어가는 곳 -->
		
		<h1>레시피 글 등록</h1>
		<!-- 
		form 데이터 중 파일 정보가 포함될 경우
		<form> 태그 속성에 enctype="multipart/form-data" 명시 필수!
		(생략 시 enctype="application/x-www-form-urlencoded" 속성이 기본값으로 설정됨)
		-->
		<form action="RecipeWritePro.co" name="recipeForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td><input type="text" name="nickname" required="required" placeholder="닉네임"/></td>
				</tr>
				<tr>
					<td><input type="text" name="subject" required="required" placeholder="제목"/></td>
				</tr>
				<tr>
					<td>
						<textarea name="content" cols="40" rows="15" required="required" placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file1" multiple="multiple"></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file2" multiple="multiple" ></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file3" multiple="multiple"></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file4" multiple="multiple"></td>
				</tr>
				<tr>
<!-- 					파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file5" multiple="multiple"></td>
				</tr>
				
			</table>
			<section>
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
		<!-- 		푸터 들어가는 곳 -->
		<jsp:include page="../../inc/footer.jsp"/>
<!-- 		푸터 들어가는 곳 -->
	</section>
</body>
</html>