<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload= function(){
		
		var nickName ="<%=request.getParameter("nickName") %>";
		var isDuplicate="<%=request.getParameter("isDuplicate") %>";
		var divCheckIdResult=document.getElementById("checkNickNameResult")
		if(nickName != "null"){
			document.fr.nickName.value=nickName;
			if(isDuplicate == "false"){
				 
				divCheckIdResult.innerHTML = "사용 가능한 닉네임<br>" 
					+ "<input type='button' value='닉네임 사용' onclick='useNickName()'>";
				divCheckIdResult.style.color = "GREEN";
			}else if(isDuplicate == "true"){
				divCheckIdResult.innerHTML = "이미 사용중인 닉네임";
				divCheckIdResult.style.color = "RED";
			}
		}
	}
	
	function checkNickName() { // onsubmit 이벤트 동작 시 호출
		// 정규표현식을 사용하여 아이디 입력 양식(영문자,숫자 조합 4~16자리) 검증
		// 아이디 입력창의 입력 내용 가져와서 변수에 저장
		var nickName = document.fr.nickName.value;
	
		var elemSpan = document.getElementById("checkNickNameResult");
	
		var regex = /^[가-힣|a-z|A-Z]{4,16}$/;
		
		if(!regex.exec(nickName)) { // 부적합한 아이디일 경우
			elemSpan.innerHTML = "한글, 영어로  4~16자리 필수!";
			elemSpan.style.color = "RED";
			document.fr.nickName.select(); // 입력항목 포커스 및 블럭
			return false; // submit 동작 취소
		} 
		
		return true; // submit 동작 수행
	}
	
	function useNickName() { 
		// 아이디 사용 버튼 클릭 시
		// 부모창(join_form.html)의 폼 영역 내의 ID 입력창에 현재 입력된 아이디를 표시
		// => window.opener.document.폼이름.요소이름.value = 값; 형태로 표시 가능
		window.opener.document.fr.nickName.value = document.fr.nickName.value; // 부모창 ID 영역에 표시
		window.close(); // 창 닫기
	}
</script>
</head>
<body>
	<h1 >ID 중복 체크</h1>
	<form action="CheckDuplicateNickName.me" name="fr" onsubmit="return checkNickName()">
		<input type="text" name="nickName" id="nickName" > 
		<input type="submit" value="중복확인" >
		<div id="checkNickNameResult"></div>
	</form>
</body>
</html>











    