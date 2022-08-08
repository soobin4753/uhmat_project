<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>reviewWriteForm</h1>
	<section>
		<form  action="ReviewWritePro.re" method="post" enctype="multipart/form-data">
			<table border>
			<!-- 
					1.제목
					2. Tag(db수정해야함?)
					3. 음식점 찾기
					4. ratings
					likes (추가해야함)
					5. content				
				 -->
				<tr>
					<td>작성자&nbsp;<input type="text" name="nickname" placeholder="세션 nickname" required="required" ></td>
					<!--  회원가입 로그인이 연동되면 nickname을 세션값으로받습니다. -->
				</tr>
				<tr>
					<td><input type="text" name="subject" placeholder="제목을 입력하세요" required="required"></td>
				</tr>
				<tr>
					<td><input type="text" name="Tag" value="tag"></td> 
					<!-- select box 를 통한 추가 조사 
						DB에 추가해야함
					-->
				</tr>
				<tr>
					<td><input type="text" name="res_name" placeholder="음식점 위치 찾기" required="required">&nbsp;<button>찾기</button></td>
					
					<!-- 지도 api와 연동 -->
				</tr>
				<tr>
					<td><input type="text" name="rating" placeholder="별점을 숫자로 입력해주세요" required="required"></td>
				</tr>
				<tr>
					<td><textarea rows="20" cols="100" name="content" placeholder="내용을 입력하세요" required="required"></textarea></td>
				</tr>
				<tr>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td>파일첨부<input type="file" name="photo" required="required" /></td>
				</tr>
			</table>
		<section>
			<input type="submit" value="제출해버렷">
		</section>
		</form>
	</section>
</body>
</html>