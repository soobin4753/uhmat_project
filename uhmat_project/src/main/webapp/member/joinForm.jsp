<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 다음 우편번호 API 포함시키기 -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/jquery-3.6.0.js"></script>
<script>
	$(function(){
		
		$("input[type=reset]").on("click",function(){
			$("#checkPasswdResult").html("");
			$("#confirmPasswdResult").html(""); 
			$("#confirmEmailResult").html(""); 
		});
		
		$("#passwd").on("change",function(){
			// 패스워드 검사 패턴 설정
			// 1. 길이 및 사용 가능 문자에 대한 규칙 : 8 ~ 16 자리 영문자, 숫자, 특수문자(!@#$%) 조합
			var lengthRegex = /^[A-Za-z0-9!@#$%]{8,16}$/;
			var password = $("#passwd").val();

			// 각 문자 형식이 포함되는지 여부를 각각 체크하기 위한 패턴 설정
			// => 주의! 부분 패턴 검사이므로 시작(^) 과 끝($)은 사용하면 안된다!
			// 2. 영문 대문자 규칙
			var engUpperRegex = /[A-Z]/;
			// 3. 영문 소문자 규칙
			var engLowerRegex = /[a-z]/;
			// 4. 숫자 규칙
			var numRegex = /[0-9]/;
			// 5. 특수문자 규칙
			var specRegex = /[!@#$%]/;
			if (lengthRegex.exec(password)) {
				var count = 0; // 검증 결과를 포인트화 할 변수 선언

				if (engUpperRegex.exec(password)) { // 대문자 검사
					count++;
				}

				// 주의! 각 조건마다 별도로 검사하므로 else if 가 아닌 각각 단일 if 문 사용
				if (engLowerRegex.exec(password)) { // 소문자 검사
					count++;
				}

				if (numRegex.exec(password)) { // 숫자 검사
					count++;
				}

				if (specRegex.exec(password)) { // 특수문자(!@#$%) 검사
					count++;
				}

				// 패턴 카운팅 결과를 사용하여 복잡도 판별 결과 출력(if 문 또는 switch-case 문 사용)
				if (count == 4) {
					$("#checkPasswdResult").html("사용 가능 : 안전"); 
					$("#checkPasswdResult").css("color","GREEN"); 
				} else if (count == 3) {
					$("#checkPasswdResult").html("사용 가능 : 보통");
					$("#checkPasswdResult").css("color","blue"); 
				} else if (count == 2) {
					$("#checkPasswdResult").html( "사용 가능 : 위험");
					$("#checkPasswdResult").css("color","ORANGE"); 
				} else {
					$("#checkPasswdResult").html("영문자, 숫자, 특수문자 중 2가지 이상 조합 필수!");
					$("#checkPasswdResult").css("color","RED"); 
				}

			} else { // 패스워드 길이 또는 사용 가능 문자 체크 부적합 시
				$("#checkPasswdResult").html("8~16자리 영문자, 숫자, 특수문자 조합 필수!"); 
				$("#checkPasswdResult").css("color","RED"); 
		
			}
		});
		
		
		$("#passwd2").on("change",function(){
			// 비밀번호 & 비밀번호확인란이 같은지 판별
			let passwd = $("#passwd").val();
			let passwd2 = $("#passwd2").val();

			// 두 패스워드 비교
			if (passwd == passwd2) {
				$("#confirmPasswdResult").html("일치 합니다"); 
				$("#confirmPasswdResult").css("color","GREEN"); 
			
			} else {
				$("#confirmPasswdResult").html("일치 하지 않습니다"); 
				$("#confirmPasswdResult").css("color","RED"); 
			
			}
			
			
		});
		
		$("#emailCheck").on("change",function(){ 
			// 비밀번호 & 비밀번호확인란이 같은지 판별
			let email = $("#email").val();
			let email2 = $("#emailCheck").val();
		
			// 두 패스워드 비교
			if (email == email2) {
				$("#confirmEmailResult").html("일치 합니다"); 
				$("#confirmEmailResult").css("color","GREEN"); 
			
			} else {
				$("#confirmEmailResult").html("일치 하지 않습니다");
				
				$("#confirmEmailResult").css("color","RED"); 
			
			}
			
			
		});
		
		
		$("#nickName").on("keyup",function(){
			var regExp =/^[a-z|가-힣]+[a-z|0-9|가-힣]{3,16}$/g;
			if(!regExp.test($("#nickName").val()) || $("#nickName").val()==null){
				 $('#nickNameCheck').text('4글자 ~16글자 사이');    
                 $('#nickNameCheck').css('color', "red");
			}else{
			$.ajax({
				type: "get",
				url: "http://localhost:8080/uhmat_project/CheckDuplicateNickName.me",
				dataType: "text",
				data:{nickName:$("#nickName").val()},
	            success: function(data, textStatus) {
	                if(data === 'usable') {
	                    $('#nickNameCheck').text('사용할 수 있는 닉네임입니다.');     
	                    $('#nickNameCheck').css('color', "green");
	                } else {
	                    $('#nickNameCheck').text('이미 사용 중인 닉네임입니다.');
	                    $('#nickNameCheck').css('color', "red");
	                }
	            },
	            error:function (data, textStatus) {
	                console.log('error');
	            }
			});
		}
		});
		
		$("#email").on("keyup",function(){
			var regExp =/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			if(!regExp.test($("#email").val()) ){
				 $('#EmailResult').text('이메일 형식에 맞지 않습니다.');      
                 $('#EmailResult').css('color', "red");
			}else{
			$.ajax({
				type: "get",
				url: "http://localhost:8080/uhmat_project/CheckDuplicateEmail.me",
				dataType: "text",
				data:{email:$("#email").val()},
	            success: function(data, textStatus) {
	                if(data === 'usable') {
	                    $('#EmailResult').text('사용할 수 있는 이메일입니다.');      
	                    $('#EmailResult').css('color', "green");
	                } else {
	                    $('#EmailResult').text('이미 사용 중인 이메일입니다.');
	                    $('#EmailResult').css('color', "red");
	                }
	            },
	            error:function (data, textStatus) {
	                console.log('error');
	            }
			});
		}
		
	});
	
	});


	// 다음 우편번호 API
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById('sample4_roadAddress').value = roadAddr;
						//                 document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
					}

				}).open();
	}
	
</script>
</head>
<body>
	<h1>회원가입</h1>
	<form action="MemberJoinPro.me" method="post" name="fr">

		<div>이름</div>
		<div><input type="text" name="name" required="required"></div>
		<br>
		<div>닉네임</div>
		<div><input type="text" name="nickName" id="nickName"required="required" > <span
			id="nickNameCheck"></span>
		
		</div>
<br>

		<div>비밀번호</div>
		<div>
			<!-- 패스워드 변화할 때마다 checkPasswd() 함수 호출 => 파라미터로 입력 패스워드 전달 --> 
			<input type="password" name="passwd" id="passwd" placeholder="영문자,숫자,특수문자 8~16글자" required="required"> 
			<span id="checkPasswdResult"><!-- 패스워드 검증 결과 표시할 위치 --></span>
		</div>
<br>

		<div>비밀번호확인</div>
		<div><input type="password" name="passwd2" id="passwd2" required="required"> <span
			id="confirmPasswdResult"></span></div>
<br>

		<div>E-Mail</div>
		<div><input type="text" name="email" id="email" required="required" > 
		<span id="EmailResult"></span>
		</div>
		<br>
		<div>E-Mail 확인</div>
		<div><input type="text" name="emailCheck" id="emailCheck" required="required"><span
			id="confirmEmailResult"></span></div>
			<br>
			<div>생년월일</div>
			<div>
	<input type="date" name="birth" required="required">
</div>
     <br>
		<div>주소</div>
		<div><input type="text" id="sample4_postcode" name="postCode"
			placeholder="우편번호" required="required" readonly="readonly"
			onclick="execDaumPostcode()"> 
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br> 
			<input type="text" id="sample4_roadAddress" name="address1"
			placeholder="도로명주소" required="required" readonly="readonly"
			onclick="execDaumPostcode()"> 
			<input type="text" id="sample4_detailAddress" name="address2" placeholder="상세주소"
			required="required"></div>


		<div>
		<input type="submit" value="가입"> 
		<input type="reset" value="초기화"> 
		
		</div>


	</form>
</body>
</html>
















