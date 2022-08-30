<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	function likeAdd(target) {
		alert("click!");
		
		$.ajax({
			type: "post",
			url: "ReviewLikeAction.re?idx=${dto.idx}&nickname='nickname2'&pageNum=${param.pageNum}",
			dataType: "text",
			success:
				function(response) {
					location.reload();
// 					alert("yes");
// 					$("#like").load(window.location.href+" #like");
// 					alert("yas");
			}
			
		});
	}
</script>
<style>
	.star-rating {width:205px; }
	.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(image/star3.png)no-repeat; }
	.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>
</head>
<body>
	<h1>ReviewDetailView.jsp</h1>
	<nav>
		<details>
			<summary>∙∙∙(more button)</summary> <!-- 이 부분은 로그인 되어 있는 경우에만 사용 가능  세션이 없을 경우
			alert("로그인이 필요합니다") 처리-->

			<input type="button" value="수정" onclick="location.href='ReviewModifyForm.re?idx=${dto.idx}&pageNum=${param.pageNum}'">
			<input type="button" value="삭제" onclick="location.href='ReviewDeleteForm.re?idx=${dto.idx}&fileName=${dto.photo }&pageNum=${param.pageNum}'">
			<input type="button" value="리스트로" onclick="location.href='ReviewList.re'">
		</details>
	</nav>
	<section>
		<table border ="1">

			<tr>
				<th>작성자</th><td >${dto.nickname }</td>
				<!--  회원가입 로그인이 연동되면 nickname을 세션값으로받습니다. -->
			</tr>
			<tr>
				<th>주제</th><td>${dto.subject }</td>
			</tr>
			<tr>
				<th>Tag</th><td>${dto.tag_name }</td> 
				<!-- select box 해쉬태그 제시를 통한 추가 조사 selectbox를DB에 추가해야함?
					
				-->
			</tr>
			<tr>
				<th>음식점정보</th><td>${dto.res_name }</td>
				<!-- 지도 api와 연동 -->
			</tr>
			<tr>
				<th>별점</th><td><div class='star-rating'><span style ="width:${dto.rating*20}%; "></span></div></td> <!--  CSS 로 구현 -->
			</tr>
			<tr>
				<th>내용</th><td><textarea rows="20" cols="100">${dto.content }</textarea></td>
			</tr>
			<tr>
				<th><input type="button" id="like" value="${dto.likes }" onclick="likeAdd(this)"></th>
				<td><input type="button" value="댓글작성버튼">&nbsp;<input type="button" value="공유버튼"></td></tr>

			<tr>
				<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
				<th>이미지</th><td width="200" height="300"><img width="90%" src="upload/${dto.photo }" alt="파일"></td>
			</tr>
		</table>
	</section>
</body>
</html>