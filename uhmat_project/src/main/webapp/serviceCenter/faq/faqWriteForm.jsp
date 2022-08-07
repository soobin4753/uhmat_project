<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 글 등록</title>
<style type="text/css">
#writeForm{
width: 500px;
height: 610px;
border: 1px solid red;
margin: auto;

}

h2 {
text-align: center;
background: #0f0;
}

table{
margin: auto;
width: 450px;
}

.td_left{
width: 150px;
background: orange;
}


.td_right{
width: 300px;
background: skyblue;
}

#commandCell{
text-align: center;
}
</style>
</head>
<body>
	<section id="writeForm">
	<h2>FAQ게시판 글 등록</h2>
		<form action="FAQWritePro.sc" method="post" enctype="multipart/form-data" name="boardform">
			<table border="1">
				<tr>
					<td class="td_left"><label for="nickname">글쓴이</label></td>
					<td class="td_right"><input type="text" name="nickname" id="nickname" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="subject">제목</label></td>
					<td class="td_right"><input name="subject" type="text" id="subject" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content" cols="40"	rows="15" required="required"></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="file">파일 첨부</label></td>
					<td class="td_right"><input name="file" type="file" id="file" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="category">카테고리</label></td>
					<td colspan="3">
						<select id="selectBox" name="category">
							<option value="오류신고">오류신고</option>
							<option value="음식점등록">음식점등록</option>
							<option value="자도 오류">지도 오류</option>
						</select>
					</td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록"> &nbsp;&nbsp;
				<input type="reset" value="다시쓰기" /> &nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>