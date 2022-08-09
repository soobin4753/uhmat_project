<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type='text/javascript'>
	Kakao.init('6daf74bc6bdb2ab6d0c28741ed94ae3a'); //★ 수정 할 것 : 내 애플리케이션>앱 설정>앱 키 : JAVASCRIPT KEY 입력하세요
	// SDK 초기화 여부를 판단합니다.
	console.log(Kakao.isInitialized());
</script>

<script type="text/javascript">
	function loginWithKakao() {
		Kakao.Auth.login({
			success : function(authObj) {
				alert("login :" + JSON.stringify(authObj));
				//★ 추가 할 것 : 로그인 성공 후 처리 
			},
			fail : function(err) {
				alert(JSON.stringify(err))
			},
		})
	}

	function logoutWithKakao() {
		if (!Kakao.Auth.getAccessToken()) {
			console.log('Not logged in.');
			return;
		}
		console.log(Kakao.Auth.getAccessToken()); //before Logout
		Kakao.Auth.logout(function() {
			alert("logout");
			//★ 추가 할 것 : 로그아웃 성공 후 처리 
		});
	}

	function profileWithKakao() {
		Kakao.API
				.request({
					url : '/v2/user/me',
					success : function(response) {
						console.log(response);
						document.getElementById("userid").innerText = response.id;
						document.getElementById("nickname").innerText = response.kakao_account.profile.nickname;
						document.getElementById("email").innerText = response.kakao_account.email;
						document.getElementById("gender").innerText = response.kakao_account.gender;
						document.getElementById("birthday").innerText = response.kakao_account.birthday;
				
					},
					fail : function(error) {
						console.log(error);
					}
				});
	}
</script>
</head>
<body>
	<a id="custom-login-btn" href="javascript:loginWithKakao()"> <img
		src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
		width="222" />
	</a>
	<Button onclick="javascript:logoutWithKakao()">Logout</Button>
	 <Button onclick="javascript:profileWithKakao()">Profile</Button>
                <p id="userid"></p>
                <p id="nickname"></p>
                <p id="email"></p>
                <p id="gender"></p>
                <p id="birthday"></p>
              
</body>
</html>