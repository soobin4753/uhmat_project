<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
		<jsp:include page="inc/header.jsp" />
	<!-- 헤더 들어가는곳 -->
	
	<hr>		
			
	<!-- 본문들어가는 곳 -->
		<div class="row">
					<div class="col col_6">
						<div class="row">
							<div class="col col_11">
								
	<div id="uhmatRank" class="uhmatRank widget widgetPage widgetRank">
		<div class="widgetHeader row">
			<div class="titleWrapper col">
				<a class="col" href="">
					<h3 class="widgetHeaderTitle"><strong>서면 맛집 인기 순위</strong></h3>
				</a>
			</div>
			<div class="btn_wrapper col r_col">
				<button class="btn_prev" onclick="app.page_change(this, -1);"><strong></strong></button>
				<button class="btn_next" onclick="app.page_change(this, 1);"><strong></strong></button>
			</div>
		</div>
		<br>
		<div class="widget_bottom row">
			<ul class="rank_list widget_page_item active">
				<li class="rank_item top">
					<a href="">
						<span class="rank_count top">1</span>
						<span class="item_title text_over top">어맛1</span>
						<img class="item_img" src="" alt="어맛1" />
						<br>
						<span class="item_score">7.2</span><span class="item_score_text"> 점</span><span class="active_count">(<strong>233</strong>명 참여)</span>
						<br>
						<span class="item_genre">일식</span>
						<br>
						<span class="item_view_text top">새글 <span class="item_view_count">191</span></span>
					</a>
				</li>
				<li class="rank_item">
					<a href="">
						<span class="rank_count">2</span>
						<span class="item_title text_over">멘즈키</span>
						<span class="item_view_text">새글 <span class="item_view_count">57</span></span>
						<span class="r_col">&nbsp;|&nbsp;</span>
						<span class="item_score r_col" style="font-size: 13px;">1.6점</span>
					</a>
				</li>
				<li class="rank_item">
					<a href="">
						<span class="rank_count">3</span>
						<span class="item_title text_over">칸다소바 부산서면점</span>
						<span class="item_view_text">새글 <span class="item_view_count">46</span></span>
						<span class="r_col">&nbsp;|&nbsp;</span>
						<span class="item_score r_col" style="font-size: 13px;">9.4점</span>
					</a>
				</li>
				<li class="rank_item">
					<a href="">
						<span class="rank_count">4</span>
						<span class="item_title text_over">두남자 찜닭</span>
						<span class="item_view_text">새글 <span class="item_view_count">40</span></span>
						<span class="r_col">&nbsp;|&nbsp;</span>
						<span class="item_score r_col" style="font-size: 13px;">7.7점</span>
					</a>
				</li>
				<li class="rank_item">
					<a href="">
						<span class="rank_count">5</span>
						<span class="item_title text_over">부부해장국</span>
						<span class="item_view_text">새글 <span class="item_view_count">34</span></span>
						<span class="r_col">&nbsp;|&nbsp;</span>
						<span class="item_score r_col" style="font-size: 13px;">8점</span>
					</a>
				</li>
			</ul>
		</div>	
	</div>
</div>
</div>
</div>
</div>	

<hr>
		
	<!-- 푸터 들어가는곳 -->
		<jsp:include page="inc/footer.jsp" />
	<!-- 푸터 들어가는곳 -->		

</body>
</html>   