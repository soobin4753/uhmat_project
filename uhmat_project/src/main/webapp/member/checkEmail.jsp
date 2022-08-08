<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload= function(){
		
		var email ="<%=request.getParameter("email") %>";
		var isDuplicate="<%=request.getParameter("isDuplicate") %>";
		var divCheckIdResult=document.getElementById("checkEmailResult")
		if(email != "null"){
			document.fr.email.value=email;
			if(isDuplicate == "false"){
				 
				divCheckIdResult.innerHTML = "사용 가능한 아이디<br>" 
					+ "<input type='button' value='아이디 사용' onclick='useEmail()'>";
				divCheckIdResult.style.color = "GREEN";
			}else if(isDuplicate == "true"){
				divCheckIdResult.innerHTML = "이미 사용중이거나 탈퇴한 아이디";
				divCheckIdResult.style.color = "RED";
			}
		}
	}


	
	function useEmail() { 
		// 아이디 사용 버튼 클릭 시
		// 부모창(join_form.html)의 폼 영역 내의 ID 입력창에 현재 입력된 아이디를 표시
		// => window.opener.document.폼이름.요소이름.value = 값; 형태로 표시 가능
		window.opener.document.fr.email.value = document.fr.email.value; // 부모창 ID 영역에 표시
		window.close(); // 창 닫기
	}
</script>
</head>
<body>
	<h1 >ID 중복 체크</h1>
	<form action="CheckDuplicateEmail.me" name="fr" >
		<input type="text" name="email" id="email" > 
		<input type="submit" value="중복확인" >
		<div id="checkEmailResult"></div>
	</form>
</body>
</html>











    