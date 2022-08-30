<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/jquery-3.6.0.js"></script>
 <script src="./js/jquery-3.6.0.js"></script>

<script>
	$(function() {
		$.ajax({
			url : "http://localhost:8080/uhmat_project/MemberBoardList.me",  // ./ 현재경로표시
			type : "get",
			data :  {
				title: $("#selectBox").val()
					},
					
			dataType: "text",
			success : function(data) {
			            $('#boadlist').html(data);
			},
			errer : function() {
				alert('errer');
			}
	
		 });
		
		$("#selectBox").on("change",function(){
			$.ajax({
				url : "http://localhost:8080/uhmat_project/MemberBoardList.me",  // ./ 현재경로표시
				type : "get",
				data :  {
					title: $("#selectBox").val()
						},
						
				dataType: "text",
				success : function(data) {
				            $('#boadlist').html(data);
				},
				errer : function() {
					alert('errer');
				}
		
			 });
			
		});
		$("input[type=reset]").on("click", function() {
			$("#EmailResult").html("");
			$("#checkNickNameResult").html("");
		});


		$("#emailCheck").on("change", function() {
			// 비밀번호 & 비밀번호확인란이 같은지 판별
			let email = $("#email").val();
			let email2 = $("#emailCheck").val();

			// 두 패스워드 비교
			if (email == email2) {
				$("#confirmEmailResult").html("일치 합니다");
				$("#confirmEmailResult").css("color", "GREEN");

			} else {
				$("#confirmEmailResult").html("일치 하지 않습니다");

				$("#confirmEmailResult").css("color", "RED");

			}

		});

		$("#nickName").on("keyup",function() {

							var regex = /^[가-힣a-zA-Z][가-힣a-zA-Z0-9!@#$%()]{4,10}$/;

							if (!regex.exec($("#nickName").val())
									|| $("#nickName").val() == null) { // 부적합한 아이디일 경우

								$("#checkNickNameResult").html("한글, 영어로  4~16자리 필수! (주의 : 맨앞 특수문자,숫자 불가)");
								$("#checkNickNameResult").css("color", "RED");

							} else {
								$
										.ajax({
											type : "post",
											url : "http://localhost:8080/uhmat_project/CheckDuplicateNickName.me",
											data : {
												nickName : $("#nickName").val(),
											},
											dataType : "text",
											success : function(data) {
												if (data === 'usable') {
													$('#checkNickNameResult').text('사용할 수 있는 닉네임입니다.');
													$("#checkNickNameResult").css("color","GREEN");
												} else {
													$('#checkNickNameResult').text('이미 사용 중인 닉네임입니다.');
													$("#checkNickNameResult").css("color", "RED");
												}
											},
											error : function(data, textStatus) {
												console.log('error');
											}
										});
							}

						});
		$("#email").on("keyup",function() {

							var regex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
							

							if (!regex.exec($("#email").val())|| $("#email").val() == null) { // 부적합한 아이디일 경우

								$("#EmailResult").html("잘못된 이메일 형식입니다. ");
								$("#EmailResult").css("color", "RED");

							} else {
								$
										.ajax({
											type : "post",
											url : "http://localhost:8080/uhmat_project/CheckDuplicateNickName.me",
											data : {
												nickName : $("#email").val(),
											},
											dataType : "text",
											success : function(data) {
												if (data === 'usable') {
													$('#EmailResult')
															.text(
																	'사용할 수 있는 email입니다.');
													$("#EmailResult").css(
															"color", "GREEN");
												} else {
													$('#EmailResult')
															.text(
																	'이미 사용 중인 email입니다.');
													$("#EmailResult").css(
															"color", "RED");
												}
											},
											error : function(data, textStatus) {
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

<form action="MemberDetailModifyPro.me" method="post">
<label>이름</label><br>
<input type="text" id="name" name="name" value="${member.name }" ><br>
<label>닉네임</label><br>
<input type="text" id="nickName" name="nickName" value="${member.nickname }"><br>
<span id="checkNickNameResult"></span><br>
<label>이메일</label><br>
<input type="text" id="email" name="email" value="${member.email }" readonly="readonly"><br>
<span id="EmailResult"></span><br>
<label>생년월일</label><br>
<input type="date" id="birth" name="birth" value="${member.birthdate }"><br>
<label>비밀번호 변경</label><br>
<input type="button" value="비밀번호 변경 하기" onclick="location.href='MemberPasswordModifyForm.me'"><br>
<label>우편번호</label><br>
	<label>주소</label>
		<div>
			<input type="text" id="sample4_postcode" name="postCode"
				placeholder="우편번호" required="required" readonly="readonly"
				onclick="execDaumPostcode()" value="${member.postCode }"> <input type="button"
				onclick="execDaumPostcode()" value="우편번호 찾기"><br> <input
				type="text" id="sample4_roadAddress" name="address1" value="${member.address1 }"
				placeholder="도로명주소" required="required" readonly="readonly"
				onclick="execDaumPostcode()"> <input type="text" value="${member.address2 }"
				id="sample4_detailAddress" name="address2" placeholder="상세주소"
				required="required">
		</div>
<input type="submit" value="수정하기">
</form>
<hr>
<section>
		<select id="selectBox" id="selectBox" name="title"  >
					<option value="Mate" selected="selected">Mate</option>
					<option value="Tmi">Tmi</option>
					<option value="Recipe">Recipe</option>
					<option value="FAQ" >FAQ</option>
				</select>
				
		<div id="boadlist"></div>

</section>

</body>
</html>