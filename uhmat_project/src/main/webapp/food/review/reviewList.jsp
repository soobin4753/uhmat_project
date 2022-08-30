<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="food/review/reviewList.css" rel="stylesheet">
<script src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	window.onscroll = function() {
	
		if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
		
			$.ajax({
				type: "post",
				url: "ReviewList.re?pageNum=${pageInfo.pageNum + 1}",
				data: $("#list").serialize(),
				dataType: "text",
				success: 
					function(response) {
						if(${pageInfo.endPage} == ${pageInfo.pageNum + 1}) {
							$("#writeForm").remove();
							$("#header").remove();
							$("#footer").remove();
							$("#append").html(response);
						
						}
					}
			});
		}
	}

	

	$(document).ready(function(){
	    $('td[id^=content]').each(function(){
	        if ($(this).text().length > 250) {
	        $(this).html($(this).text().substr(0,250)+"<span onclick='more()'>...더보기</span>");
	        }
	    });
	
	});
	
	$(document).ready(function() {
		$('td[id^=tagName]').each(function(){
				var tagList = $(this).text();
				tagList = tagList.split("#");
// 				alert(tagList);
			for(var i = 1; i < tagList.length; i++) {
				var arr = tagList[i];
				$(this).append("<input type='button' value='#"+arr+"'/>");
// 				$(this).append("<input type='button' value='#"+arr+"'/>");
				
// 				$(this).value("<input type='button' value='#"+arr+"'/>");
				}
			});
		});
		
		
// 	})
// 	function more() {
// 		location.href="ReviewDetail.re?idx=" + ${board.idx} + "&pageNum=" +${pageInfo.pageNum};
// 	}
	
	
	
// 	$(document).ready(function() {
// 		$('td[id^=rating]').each(function(){
// 			var colorWidth = $(this).text();
// 			colorWidth = Number(colorWidth) * 20;
// 			colorWidth = String(colorWidth) + "%";
// 			alert(colorWidth);
			
// 			$('span[id^=star]').each(function () {
// 				$(this).css({width: colorWidth, color: "red"});
				
// 				})
// 			})
// 		});

// 		$(document).ready(function() {
// 			var colorWidth = $("#rating").text();
// 			colorWidth = Number(colorWidth) * 20;
// 			colorWidth = String(colorWidth) + "%";
// 			alert(colorWidth);
			
// 			$('span[id^=star]').each(function () {
// 				$(this).css({width: colorWidth, color: "red"});
				
// 				})
// 			})
</script>
<style>
	.star-rating {width:205px; }
	.star-rating,.star-rating span {display:inline-block; height:39px; overflow:hidden; background:url(image/star3.png)no-repeat; }
	.star-rating span{background-position:left bottom; line-height:0; vertical-align:top; }
</style>
</head>
<body>
	<header id="header"> <!--헤더라인 -->
<%-- 		<jsp:include page="../inc/"> --%>
			<hr>
				<h1>헤더라인</h1>
			<hr>
	</header>
		<aside id="aside"><a href="ReviewWriteForm.re">글 작성</a></aside>

	<section id="list">
		<c:choose>
			<c:when test="${not empty reviewList and pageInfo.listCount gt 0}">
				<c:forEach var="board" items="${reviewList}" varStatus="state">
					<!--  -->
					<table id="listView">
						<tr><td>${board.subject }</td></tr>
						<tr><th><img height="200px"src="upload/${board.photo }" alt="파일"></th></tr>
						<tr><td><span id="result${state.count }"></span></td></tr>
						<tr><td id="tagName${state.count }"><span style="display:none;">${board.tag_name}</span></td></tr>
<%-- 						<tr><td><button id="tagName${state.count }">${board.tag_name}</button> </td></tr> --%>
						<tr><td id="result${state.count }"></td></tr>
						<tr><td> <div class='star-rating'><span style ="width:${board.rating*20}%"></span></div></td><td id="rating">${board.rating }점</td></tr>
						<tr><td><span>&#9829;</span>${board.likes} 개</td></tr>				
						<tr><td id="content${state.count }" onclick="location.href='ReviewDetail.re?idx=' + ${board.idx}+'&pageNum=' +${pageInfo.pageNum}">${board.content}</td></tr> <!-- 이부분에서 나중에 댓글 항목 추가, 더보기 란 할 수 있도록 해야함 -->
					</table>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8">게시글이 존재하지 않습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</section>
	<section id="append">
	</section>

	<footer id="footer"> <!--푸터라인 -->
				<hr>
				<h1>푸터라인</h1>
				<hr>
<%-- 		<jsp:include page="../inc/"> --%>
	</footer>
</body>
</html>