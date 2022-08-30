<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
<style type="text/css">
.flex-container {
	display: flex;
}

.flex-container>div {
	background-color: #f1f1f1;
	height: 100%;
	width: 50%;
	padding: 20px;
	font-size: 30px;
	text-align: center;
	border: 1px solid;
	border-bottom: 0;
}

.login-container {
	height: 650px;
	margin: 0 auto;
	width: 995px;
	font-size: 30px;
}

.buttonResult {
	background-color: #f2f2f2;
	width: 100%;
	height: 550px;
	/* 	padding: 20px; */
	text-align: center;
}

.buttonResult2 {
	background-color: #f2f2f2;
	width: 100%;
	height: 550px;
	/* 	padding: 20px; */
	text-align: center;
}

#iframe01 {
	width: 400px;
	height: 75px;
	margin-top: 100px;
}

#iframe02 {
	width: 400px;
	height: 75px;
}

#iframe03 {
	width: 400px;
	height: 75px;
}

#iframe2 {
	height: 500px;
	margin-top: 100px;
}
</style>
<script type="text/javascript">
	$(function() {
		var div1 = document.getElementById('login');
		var div2 = document.getElementById('slmpl');
		$("#log").text("로그인");
		$("#slmpleLog").text("간편로그인");
		div2.style.display = 'none';
		div1.style.display = 'block';
		$("#log").click(function() {
			div2.style.display = 'none';
			div1.style.display = 'block';
			// btn1 숨기기 (display: none)
			if (div2.style.display !== 'none') {
				div2.style.display = 'none';
			}
			// btn` 보이기 (display: block)
			else {
				div1.style.display = 'block';
			}

		});
		$("#slmpleLog").click(function() {
			div1.style.display = 'none';
			div2.style.display = 'block';
			// btn1 숨기기 (display: none)
			if (div1.style.display !== 'none') {
				div1.style.display = 'none';
			}
			// btn` 보이기 (display: block)
			else {
				div2.style.display = 'block';
			}

		});
		// 토글 할 버튼 선택 (btn1)

	});
</script>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->

	<div class="login-container">
		<div class="flex-container">
			<div id="log"></div>
			<div id="slmpleLog"></div>
		</div>
		<div class="buttonResult" id="slmpl">
			<iframe name="iframe1" id="iframe01" src="MemberNaverForm.me"
				frameborder="0" cellspacing="0"></iframe>
			<br>
			<iframe name="iframe1" id="iframe02" src="MemberKakaoForm.me"
				frameborder="0" cellspacing="0"></iframe>
			<br>
			<iframe name="iframe1" id="iframe03" src="MemberGoogleForm.me"
				frameborder="0" cellspacing="0"></iframe>
			<br>
		</div>
		<div class="buttonResult2" id="login">
			<form action="MemberLoginPro.me" method="post">
				<div>
					<label>email</label><br> <input type="text" name="email"
						required="required">
				</div>
				<div>
					<label>비밀번호</label><br> <input type="password" name="passwd"
						required="required">
				</div>
				<br> <input type="submit" value="로그인">
			</form>

			<br> <a href="MemberFindPasswordForm.me">비밀번호 찾기</a>

		</div>
	</div>

	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 -->
</body>
</html>