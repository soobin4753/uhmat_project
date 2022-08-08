<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 글 수정</title>
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
		<form action="FAQModify.sc" name="faqModifyForm" method="post">
		<!-- 게시물 수정에 필요한 글번호와 페이징 처리에 필요한 페이지번호도 함께 전달 -->
		<input type="hidden" name="idx" value="${faq.idx }">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<tr>
					<td class="td_left"><label for="nickname">글쓴이</label></td>
					<td class="td_right"><input type="text" name="nickname" value="${faq.nickname}" required="required" readonly="readonly"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="subject">제목</label></td>
					<td class="td_right"><input type="text" name="subject" value="${faq.subject}" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td class="td_right"><textarea id="content" name="content" cols="40" rows="15" required="required" >${faq.content }</textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="file">파일</label></td>
					<!-- 파일 수정 기능은 제외(파일명만 표시) -->
					<td class="td_right">${faq.original_File }(수정불가)</td>
				</tr>
				<tr>
					<td class="td_left"><label for="category">카테고리</label></td>
					<td colspan="3">
						<select id="selectBox" name="category">
							<option value="오류신고">오류신고</option>
							<option value="음식점등록">음식점등록</option>
<<<<<<< HEAD
							<option value="지도 오류">지도 오류</option>
=======
							<option value="자도 오류">지도 오류</option>
>>>>>>> master
						</select>
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








