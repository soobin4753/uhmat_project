<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <h2>${resInfo.resName } <small style="font-size: 0.5em">별점 ${resInfo.rating } &nbsp; | &nbsp; <a href="#">${resInfo.reviewCount }개의 리뷰</a></small></h2>
	<img src="upload/${resInfo.photo }" width="200">
	<!-- 식당 정보 출력 -->
        <table>
            <tr>
       			<th><i class="fa fa-phone-square" style="font-size:48px;color:red"></i> </th>
       			<td>${resInfo.phoneNumber }</td>
            </tr>
            <tr>
       			<th>월요일 </th>
       			<td>${mon } </td>
            </tr>
            <tr>
       			<th>화요일 </th>
       			<td>${tue } </td>
            </tr>
            <tr>
       			<th>수요일 </th>
       			<td>${wed } </td>
            </tr>
            <tr>
       			<th>목요일 </th>
       			<td>${thu } </td>
            </tr>
            <tr>
       			<th>금요일 </th>
       			<td>${fri } </td>
            </tr>
            <tr>
       			<th>토요일 </th>
       			<td>${sat } </td>
            </tr>
            <tr>
       			<th>일요일 </th>
       			<td>${sun } </td>
            </tr>
            <tr>
       			<th>식당 상세정보: </th>
       			<td>${resInfo.resInfo } </td>
            </tr>
            <tr>
       			<th><a href="${resInfo.resLink }">식당링크 </a></th>
       			<td> </td>
            </tr>
            <tr>
                <td colspan="4"></td>
            </tr>
        </table>
        
        <!-- 지도 정보 출력 -->
        <div id="map" style="width:100%;height:350px;"></div>
			<p><!-- 지도가 나오는 위치 --></p> 
			<div id="clickLatlng">
			</div>
				<input type="hidden" name="latitude" id="latitude">
				<input type="hidden" name="longitude" id="longitude">
			<br>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=35185e429e5d9c68170c91b88e2d3a84"></script>
			<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(${map.latitude},${map.longitude}), // 지도의 중심좌표
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
			</script>
			
        
        <button onclick="location.href='restaurantDelete.re?resName=${resInfo.resName}'">글 삭제</button>
        <button onclick="location.href='restaurantModifyForm.re?resName=${resInfo.resName}'">글 수정</button>
        <button onclick="location.href='restaurantList.re'">글 목록으로 돌아가기</button>
</body>
</html>