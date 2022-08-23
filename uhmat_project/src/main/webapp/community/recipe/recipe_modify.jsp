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
	
	#imgCheck1 {
		width : 150px;
	}
	#imgCheck2 {
		width : 150px;
	}
	#imgCheck3 {
		width : 150px;
	}
	#imgCheck4 {
		width : 150px;
	}
	#imgCheck5 {
		width : 150px;
	}
</style>
<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
<script>
	
	$(function(){
		// 파일 업로드 버튼 비활성화
		$("#file1").attr("disabled", true);
		$("#file2").attr("disabled", true);
		$("#file3").attr("disabled", true);
		$("#file4").attr("disabled", true);
		$("#file5").attr("disabled", true);
		
		// 체크박스 클릭시 업로드 버튼 활성화
		$('#imgCheck1').click(function(){
		if($("#imgCheck1").is(":checked")){
			$("#file1").attr("disabled", false);
			$("#imgCheck1").val("true");
		} else {
			$("#file1").attr("disabled", true);
			$("#imgCheck1").val("false");
		}	

		});
		
		$('#imgCheck2').click(function(){
			if($("#imgCheck2").is(":checked")){
				$("#file2").attr("disabled", false);
				$("#imgCheck2").val("true");
			} else {
				$("#file2").attr("disabled", true);
				$("#imgCheck2").val("false");
			}	

			});
		
		$('#imgCheck3').click(function(){
			if($("#imgCheck3").is(":checked")){
				$("#file3").attr("disabled", false);
				$("#imgCheck3").val("true");
			} else {
				$("#file3").attr("disabled", true);
				$("#imgCheck3").val("false");
			}	

			});
		
		$('#imgCheck4').click(function(){
			if($("#imgCheck4").is(":checked")){
				$("#file4").attr("disabled", false);
				$("#imgCheck4").val("true");
			} else {
				$("#file4").attr("disabled", true);
				$("#imgCheck4").val("false");
			}	

			});
		
		$('#imgCheck5').click(function(){
			if($("#imgCheck5").is(":checked")){
				$("#file5").attr("disabled", false);
				$("#imgCheck5").val("true");
			} else {
				$("#file5").attr("disabled", true);
				$("#imgCheck5").val("false");
			}	

			});
	});
</script>

</head>
<body>
	<!-- 게시판 글 수정 -->
	<section>
		<h1>recipe_modifyForm</h1>
		<form action="RecipeModifyPro.co" name="recipeForm" method="post" enctype="multipart/form-data">
			<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
			<input type="hidden" name="idx" value="${recipe.idx }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<!-- 업로드에 사용된 파일이름을 불러옵니다. -->
			<input type="hidden" name="fileName" value="${param.fileName }">
			<table>
				<tr>
					<td>닉네임</td>
					<td><input type="text" id="nickname" name="nickname" value="${recipe.nickname }" required="required" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="subject" name="subject" value="${recipe.subject }" required="required"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="15" cols="40" id="content" name="content" required="required"> ${recipe.content }</textarea></td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file1" id="file1" multiple="multiple" required="required" /></td>	
					<td width="200"><img src="./upload/recipe_upload/${recipe.real_File1 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck1" name="imgCheck1" value="false"> </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file2" id="file2" multiple="multiple" ></td>
					<td width="200"><img src="./upload/recipe_upload/${recipe.real_File2 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck2" name="imgCheck2" value="false" > </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file3" id="file3" multiple="multiple" ></td>
					<td width="200"><img src="./upload/recipe_upload/${recipe.real_File3 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck3" name="imgCheck3" value="false"> </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file4" id="file4" multiple="multiple" ></td>
					<td width="200"><img src="./upload/recipe_upload/${recipe.real_File4 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck4" name="imgCheck4" value="false" width="50"> </td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td><input type="file" name="file5" id="file5" multiple="multiple" ></td>
					<td width="200"><img src="./upload/recipe_upload/${recipe.real_File5 }" width="100" onerror="this.style.display='none';"></td>
					<!-- 수정작업을 위해 체크박스를 만들어 체크박스 클릭 시 수정이 가능하게 만들어줍니다. -->
					<td>수정할래? 체크 클릭!<input type="checkbox" id="imgCheck5" name="imgCheck5" value="false"> </td>
				</tr>
				
				<tr>
<!-- 			onerror="this.style.display='none';" img alt 아이콘 없애는 기능 -->
			
			</table>
			<section>
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">			
			</section>
		</form>
	</section>
</body>
</html>