<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>닫기가 가능한 커스텀 오버레이</title>
<style>
.wrap {
	position: absolute;
	left: 0;
	bottom: -40px;
	width: 288px;
	height: 132px;
	margin-left: -70px;
	text-align: left;
	overflow: hidden;
	font-size: 12px;
	font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
	line-height: 1.5;
}

.wrap * {
	padding: 0;
	margin: 0;
}

.wrap .info {
	width: 286px;
	height: 120px;
	border-radius: 5px;
	border-bottom: 2px solid #ccc;
	border-right: 1px solid #ccc;
	overflow: hidden;
	background: #fff;
}

.wrap .info:nth-child(1) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .title {
	padding: 5px 0 0 10px;
	height: 30px;
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-size: 18px;
	font-weight: bold;
}

.info .close {
	position: absolute;
	top: 10px;
	right: 10px;
	color: #888;
	width: 17px;
	height: 17px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');
}

.info .close:hover {
	cursor: pointer;
}

.info .body {
	position: relative;
	overflow: hidden;
}

.info .desc {
	position: relative;
	margin: 13px 0 0 90px;
	height: 75px;
}

.desc .ellipsis {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.desc .jibun {
	font-size: 11px;
	color: #888;
	margin-top: -2px;
}

.info .img {
	position: absolute;
	top: 6px;
	left: 5px;
	width: 73px;
	height: 71px;
	border: 1px solid #ddd;
	color: #888;
	overflow: hidden;
}

.info:after {
	content: '';
	position: absolute;
	margin-left: -12px;
	left: 50%;
	bottom: 0;
	width: 22px;
	height: 12px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
	color: #5085BB;
}
</style>
<script type="text/javascript">
window.onload = function(){
	var mapContainer = document.getElementById('map'), // 지도의 중심좌표
    mapOption = { 
        center: new kakao.maps.LatLng(35.1584642,129.0620414), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    }; 
	
		 var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	

		$("#list").empty();
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		$.ajax({
			type: "get",
			url: "http://localhost:8080/uhmat_project/map.re",
			data : {keyword:$('#keyword').val()}, 
			dataType: "json",
			success : function(data){
				
				if(data==""){
					alert("존재 하지않는 음식점 이름 입니다.");
				}
				$.each(data, function(key, val){
		             <!-- 로그 찍어주는 부분 -->
		             var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		             <!-- 로그 찍어주는 부분 -->
		             // 지도에 마커를 표시합니다 
		             var marker = new kakao.maps.Marker({
		                 map: map, 
		                 position: new kakao.maps.LatLng(val['latitude'], val['longitude'])
		             });

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div class="wrap">' + 
		            '    <div class="info">' + 
		            '        <div class="title">' + 
		        
		            val['resName']+ 
		            '        </div>' + 
		            '        <div class="body">' + 
		            '            <div class="img">' +
		            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
		            '           </div>' + 
		            '            <div class="desc">' + 
		            '                <div class="ellipsis">'+val['address']+'</div>' + 
		            '                <div class="jibun ellipsis">(우)'+ val['rPostcode'] +'(지번) 영평동 2181</div>' + 
		            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		        var placePosition = new kakao.maps.LatLng(val['latitude'], val['longitude']);
		         
		        var infowindow = new kakao.maps.InfoWindow({
		            content:content // 인포윈도우에 표시할 내용
		        });


		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다

		        // 마커와 검색결과 항목에 mouseover 했을때
		        // 해당 장소에 인포윈도우에 장소명을 표시합니다
		        // mouseout 했을 때는 인포윈도우를 닫습니다
		       
		        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
		        kakao.maps.event.addListener(marker, 'mouseover', makeOutListener(infowindow));
		         

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div>' + 
		            '    <div>' + 
		            '        <div>' + 
		            val['resName']+ 
		            '        </div>' + 
		            '        <div>' + 
		            '            <div >' +
		            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
		            '           </div>' + 
		            '            <div>' + 
		            '                <div >'+val['address']+'</div>' + 
		            '                <div >(우)'+ val['rPostcode'] +'(지번) 영평동 2181</div>' + 
		            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		      		$("#list").append(content);
		         
		         });
				function makeOverListener(map, marker, infowindow) {
				    return function() {
				        infowindow.open(map, marker);
				    };
				}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow.close();
				    };
				}
},
	error : function() {
		alert('error');
	}
			
		});			
	
}
</script>
</head>
<body>
	<input type="button" value="홈" onclick="location.href='index.jsp'">
	<input type="button" value="음식점목록"
		onclick="location.href='restaurantList.re'">


		<input type="text" placeholder="음식적을 검색하세요" id="keyword"
			name="keyword" val size="15"> <input type="button" value="검색"
			id="keywordSelect" >
	

	<div id="map" style="width: 100%; height: 500px;"></div>
	
	<div id="list"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=cc765c4cdf63c6a5bcc8181887cebf33"></script>
	<script type="text/javascript" src="./js/jquery-3.6.0.js"></script>
	<script>
 

	
	
$(function(){
	

	
		
	
$("#keywordSelect").on("click", function() {
		$("#list").empty();
		var mapContainer = document.getElementById('map'), // 지도의 중심좌표
	    mapOption = { 
	        center: new kakao.maps.LatLng(35.1584642,129.0620414), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    }; 
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		$.ajax({
			type: "get",
			url: "http://localhost:8080/uhmat_project/map.re",
			data : {keyword:$('#keyword').val()}, 
			dataType: "json",
			success : function(data){
				
				if(data==""){
					alert("존재 하지않는 음식점 이름 입니다.");
				}
				$.each(data, function(key, val){
		             <!-- 로그 찍어주는 부분 -->
		             var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		             <!-- 로그 찍어주는 부분 -->
		             // 지도에 마커를 표시합니다 
		             var marker = new kakao.maps.Marker({
		                 map: map, 
		                 position: new kakao.maps.LatLng(val['latitude'], val['longitude'])
		             });

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div class="wrap">' + 
		            '    <div class="info">' + 
		            '        <div class="title">' + 
		        
		            val['resName']+ 
		            '        </div>' + 
		            '        <div class="body">' + 
		            '            <div class="img">' +
		            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
		            '           </div>' + 
		            '            <div class="desc">' + 
		            '                <div class="ellipsis">'+val['address']+'</div>' + 
		            '                <div class="jibun ellipsis">(우)'+ val['rPostcode'] +'(지번) 영평동 2181</div>' + 
		            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		        var placePosition = new kakao.maps.LatLng(val['latitude'], val['longitude']);
		         
		        var infowindow = new kakao.maps.InfoWindow({
		            content:content // 인포윈도우에 표시할 내용
		        });


		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다

		        // 마커와 검색결과 항목에 mouseover 했을때
		        // 해당 장소에 인포윈도우에 장소명을 표시합니다
		        // mouseout 했을 때는 인포윈도우를 닫습니다
		       
		        kakao.maps.event.addListener(marker, 'click', makeOverListener(map, marker, infowindow));
		        kakao.maps.event.addListener(marker, 'mouseover', makeOutListener(infowindow));
		         

		             // 커스텀 오버레이에 표시할 컨텐츠 입니다
		             // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		             // 별도의 이벤트 메소드를 제공하지 않습니다 
		            

		             // 마커 위에 커스텀오버레이를 표시합니다
		             // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
		            content=  '<div>' + 
		            '    <div>' + 
		            '        <div>' + 
		            val['resName']+ 
		            '        </div>' + 
		            '        <div>' + 
		            '            <div >' +
		            '                <img src="https://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
		            '           </div>' + 
		            '            <div>' + 
		            '                <div >'+val['address']+'</div>' + 
		            '                <div >(우)'+ val['rPostcode'] +'(지번) 영평동 2181</div>' + 
		            '                <div><a href="https://www.kakaocorp.com/main" target="_blank" class="link">홈페이지</a></div>' + 
		            '            </div>' + 
		            '        </div>' + 
		            '    </div>' +    
		            '</div>';
		      		$("#list").append(content);
		         
		         });
				function makeOverListener(map, marker, infowindow) {
				    return function() {
				        infowindow.open(map, marker);
				    };
				}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow.close();
				    };
				}
},
	error : function() {
		alert('error');
	}
			
		});			
	});
	
// 	$.AJAX({ 
// 		TYPE: "GET",
// 		URL: "HTTP://LOCALHOST:8080/UHMAT_PROJECT/MAP.RE",
// 		DATA : {KEYWORD:$('#KEYWORD').VAL()}, 
// 		DATATYPE: "JSON",
// 		SUCCESS : FUNCTION(DATA) {
			
		         
// 				$.EACH(DATA, FUNCTION(KEY, VAL){
				
						
// 			         });
		
// 	},
// 	ERROR : FUNCTION() {
// 		ALERT('ERROR');
// 	}
	
// 	});
		 
		 
		 
	   
});


</script>
</body>
</html>




