<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- 헤더 들어가는곳 -->
		<jsp:include page="inc/header.jsp" flush="false" />
	<!-- 헤더 들어가는곳 -->
	
	<!-- 사이드바 들어가는곳 -->
<%-- 		<jsp:include page="inc/sidebar.jsp" flush="false" /> --%>
	<!-- 사이드바 들어가는곳 -->
<!-- 	<hr>		 -->
			
	<!-- 메인페이지 영역 시작 -->
	<div class="mainContainer">	
	<!-- 검색창 시작 -->
	<nav class="search">
	<div class="searchContainer">
		<div class="row">
		<!-- 검색 버튼 눌렀을 때 mainController 로 통해 uhmatSearch 페이지로 가게끔 구현해야함 -->
			<form method="post" name="search" action="UhmatSearch.sch">
				<table class="pullRight">
					<tr>
						<td>
							<i class="fa-solid fa-magnifying-glass" style="font-size: 1.1em"></i>
							<input type="search" id="searchControl"
								placeholder="검색할 음식명이나 음식점명" name="searchText" value="" maxlength="100" autocomplete="off">
						</td>
						<td>
							<button id="searchBtn" type="submit" title="검색"
							    class="submitBtn">
								<span class="blind">검색</span>
								<span class="searchIconSubmit"></span>
							</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	</nav>
	<!-- 검색창 끝 -->
	
	<!-- 본문들어가는 곳 시작 -->
		<!-- 최고의 리뷰 순위 시작 -->
			<div class="rankContainer">
				<div class = "imgContainer" ><h2>최고의 리뷰</h2>
					<div class = "mainView" style="width: 800px">
						<ul>
							<li><img src="image/sample1.jpg" width="100%" /></li>
							<li><img src="image/sample2.jpg" width="100%" /></li>
							<li><img src="image/sample3.jpg" width="100%" /></li>
							<li><img src="image/sample4.jpg" width="100%" /></li>
							<li><img src="image/sample5.jpg" width="100%" /></li>
						</ul>
					</div>
				</div>
			</div>
		<!-- 최고의 리뷰 순위 끝 -->
		
		<!-- 최신 리뷰 시작 -->
			<div class="rankReview">
				<div class = "imgContainer"><h2>최신 리뷰</h2>
					<div class = "mainView">
						<ul>
							<li><img src="image/sample1.jpg" width="100%" /></li>
							<li><img src="image/sample2.jpg" width="100%" /></li>
							<li><img src="image/sample3.jpg" width="100%" /></li>
							<li><img src="image/sample4.jpg" width="100%" /></li>
							<li><img src="image/sample5.jpg" width="100%" /></li>
						</ul>
					</div>
				</div>
			</div>
		<!-- 최신 리뷰 끝 -->
		
		<!-- 리뷰어 창 시작 -->
			<div class="reviewer">
				<div class = "imgContainer"><h2>어맛 리뷰어</h2>
					<div class = "mainView" >
						<ul>
							<li><img src="image/sample1.jpg" width="100%" /></li>
							<li><img src="image/sample2.jpg" width="100%" /></li>
							<li><img src="image/sample3.jpg" width="100%" /></li>
							<li><img src="image/sample4.jpg" width="100%" /></li>
							<li><img src="image/sample5.jpg" width="100%" /></li>
						</ul>
					</div>
				</div>
			</div>
		<!-- 리뷰어 창 끝 -->
		
		
		
	</div>
<!-- 메인페이지 영역 끝 -->		
<!-- <hr> -->
		
	<!-- 푸터 들어가는곳 시작 -->
		<jsp:include page="inc/footer.jsp" flush="false" />
	<!-- 푸터 들어가는곳 끝 -->		

</body>
</html>   