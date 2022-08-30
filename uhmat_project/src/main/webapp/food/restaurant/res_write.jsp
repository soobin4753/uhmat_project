<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 글쓰기 </title>
<style type="text/css">
#writeForm{
	width:500px;
	border: 1px solid red;
	margin: auto;
}

h2{
	text-align: center;
}

table{
	margin:auto;
	width:450px;
}

.td_left{
	width:150px;
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
<script src="js/jquery-3.6.0.js"></script>
<script>
	//ready event
	$(function(){
		$('#photo').change(function(){
		    setImageFromFile(this, '#image');
		});

		function setImageFromFile(input, expression) {
		    if (input.files && input.files[0]) {
		        var reader = new FileReader();
		        reader.onload = function (e) {
		            $(expression).attr('src', e.target.result);
		        }
		        reader.readAsDataURL(input.files[0]);
		    }
		}
	});

</script>
</head>
<body>
	<section id="writeForm">
		<h2>식당 글 등록</h2>
		<form action="restaurantWritePro.re" method="post" enctype="multipart/form-data">
			<table> 
				<tr>
					<th><label for="res_name">식당이름</label></th>
					<td><input type="text" name="res_name" id="res_name" required="required"></td>
				</tr>
				<tr>
					<th><label for="category">카테고리 선택</label></th>
					<td>
						<select name="category" id="category">
							<option value="족발">족발</option>
							<option value="보쌈">보쌈</option>
							<option value="찜">찜</option>
							<option value="일식">일식</option>
							<option value="피자&파스타">피자&파스타</option>
							<option value="고기&구이">고기&구이</option>
							<option value="도시락">도시락</option>
							<option value="중식">중식</option>
						</select>
					</td>
				</tr>
				<!-- 다음 주소로 교체할 수도? -->
				<tr>
					<th><label for="r_postcode">우편번호</label></th>
					<td><input type="text" name="r_postcode" id="r_postcode" required="required"></td>
				</tr>
				<tr>
					<th><label for="address">상세주소</label></th>
					<td><input type="text" name="address" id="address" required="required"></td>
				</tr>
				<tr>
					<th><label for="phone_number">식당 전화번호</label></th>

					<td><input type="tel" pattern="[0-9]{3}-[0-9]{3-4}-[0-9]{4}" id="phone_number" name="phone_number"></td>

				</tr>
				
				<tr>
					<th><label for="res_info">식당 상세 정보</label></th>
					<td><textarea id="res_info" name="res_info" cols="40" rows="15" required="required"></textarea></td>
				</tr>
				<tr>
					<th><label for="photo">식당 사진</label></th>

					<td><input name="photo" type="file" id="photo"  accept="image/*" required="required"></td>

				</tr>
					
				<tr>
					<th><label for="res_link">식당 웹사이트 링크</label></th>
					<td><textarea id="res_link" name="res_link" cols="40" rows="15"></textarea></td>
				</tr>
			</table>
			<h3>이미지 미리보기</h3>
			<img src="" alt="등록된 이미지 없음" id="image" width="300">
			<h3 style="text-align: center"> 영업 시간 등록</h3> 
					(휴무시 선택X)<br>
					<b>월</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<b>화</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<b>수</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<b>목</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<b>금</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<b>토</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<b>일</b> <input type="time" name="opentime" value="09:00">~<input type="time" name="closetime" value="22:00"> <br>
					<br>
			<h3>지도 위치 입력!</h3>
			<div id="map" style="width:100%;height:350px;"></div>
			<p><em>지도를 클릭해주세요!</em></p> 
			<div id="clickLatlng">
			</div>
				<input type="hidden" name="latitude" id="latitude">
				<input type="hidden" name="longitude" id="longitude">
			<br>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
			<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(35.1584642,129.0620414), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };

			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
			// 지도를 클릭한 위치에 표출할 마커입니다
			var marker = new kakao.maps.Marker({ 
			    // 지도 중심좌표에 마커를 생성합니다 
			    position: map.getCenter() 
			}); 
			// 지도에 마커를 표시합니다
			marker.setMap(map);
	
			// 지도에 클릭 이벤트를 등록합니다
			// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
			kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
			    
			    // 클릭한 위도, 경도 정보를 가져옵니다 
			    var latlng = mouseEvent.latLng; 
			    
			    // 마커 위치를 클릭한 위치로 옮깁니다
			    marker.setPosition(latlng);
			    
			    var message = '위도는 ' + latlng.getLat() + ' 이고, ';
			    message += '경도는 ' + latlng.getLng() + ' 입니다';
			    
			    var resultDiv = document.getElementById('clickLatlng'); 
			    resultDiv.innerHTML = message;
			    $("#latitude").val(latlng.getLat());
			    $("#longitude").val(latlng.getLng());
			    
			});
			</script>
			
			

			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
	
</body>
</html>