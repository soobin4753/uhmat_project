<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AllBoardList.jsp</title>
 <script src="./js/jquery-3.6.0.js"></script>
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
	}
	
	#tr_top {
		background: orange;
		text-align: center;
	}
	
	table td {
		text-align: center;
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
	}
		#articleForm {
		width: 500px;
		height: 550px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		border: 1px solid black;
		border-collapse: collapse; 
	 	width: 500px;
	}
	
	th {
		text-align: center;
	}
	
	td {
		width: 150px;
		text-align: center;
	}
	
	#basicInfoArea {
		height: 70px;
		text-align: center;
	}
	
	#articleContentArea {
		background: orange;
		margin-top: 20px;
		height: 350px;
		text-align: center;
		overflow: auto;
		white-space: pre-line;
	}
	
	#commandList {
		margin: auto;
		width: 500px;
		text-align: center;
	}
</style>
<script type="text/javascript">

	
		


</script>
<script type="text/javascript">

	


$(function(){
	$("#selectBox").val("${param.title}").attr("selected", "selected");
	
	
	$("input[name^=detail]").on("click",function(){
		var title = "";
		if($("#selectBox").val()=='Notice'){
			title="NoticeDetail.ad";
			alert(title);
		}
		if($("#selectBox").val()=='FAQ'){
			title="FAQDetail.ad";
			alert(title);
		}
		if($("#selectBox").val()=='Mate'){
			title="MateDetail.ad";
			alert(title);
		}
		if($("#selectBox").val()=='Tmi'){
			title="TmiDetail.ad";
			alert(title);
		}
		if($("#selectBox").val()=='Recipe'){
			title="RecipeDetail.ad";
			alert(title);
		}
		
		
			 
		$.ajax({
			url : "http://localhost:8080/uhmat_project/"+title,  // ./ 현재경로표시
			type : "get",
			data :  {
				idx: 
					$(this).val()
					},
					
			dataType: "text",
			success : function(data) {
			            $('#div').html(data);
				
			

			},
			errer : function() {
				alert('errer');
			}
	
		 });
		
	});
	
	
});


</script>
</head>
<body>
		<!-- 게시판 리스트 -->
		<section id="listForm">
		<h2>FAQ</h2>
		<form action="AllBoardList.ad"  id="fr" method="get"  >
				<select id="selectBox" name="title" onchange="this.form.submit()" >
					<option value="Notice" selected="selected">Notice</option>
					<option value="FAQ" >FAQ</option>
					<option value="Mate">Mate</option>
					<option value="Tmi">Tmi</option>
					<option value="Recipe">Recipe</option>
				</select>
		
			
				<!-- 검색하기 기능 -->
			
				<input type="text" placeholder="검색어를 입력하세요" id="keyword" name="keyword" value=${param.keyword }>
				<input type="submit" id="submit1" value="검색">
			</form>
<!-- 		<select id="selectBox"> -->
<!-- <!-- 			<option value="전체">전체</option> -->
<!-- 			<option value="오류신고">오류신고</option> -->
<!-- 			<option value="음식점등록">음식점등록</option> -->
<!-- 			<option value="지도 오류">지도 오류</option> -->
<!-- 		</select> -->

		<table id="list">
			<tr id="tr_top">
				<td width="150px">카테고리</td>
				<td width="100px">번호</td>
				<td>제목</td>
				<td width="150px">작성자</td>
				<td width="150px">날짜</td>
				<td width="100px">조회수</td>


			</tr>
			<!-- 게시물 목록 출력(단, 게시물이 하나라도 존재할 경우에만 출력) -> JSTL과 EL 활용-->
			<!-- JSTL의 c:choose 태그를 사용하여 게시물 존재 여부 판별 -->
			<!--  조건 : boardList 객체가 비어있지 않고 pageInfo 객체의 listCount가 0보다 클 경우 -->
	 		<c:choose>
	 			<c:when test="${not empty list and pageInfo.listCount gt 0 }">
					<!-- c:foreach 태그를 사용하여 boardList 객체의 BoardDTO 객체를 꺼내서 출력 --> 				
					<c:forEach var="AllList"  items="${list}" varStatus="status"> 
						<tr>
							<td id="category">${(empty AllList.category && AllList.category==null)?param.title:AllList.category}</td>
							<td ><input type="text" id="detail" name="detail${status.count}" value="${AllList.idx }"></td>
							<td>${AllList.subject }</td>
							<td>${AllList.nickname }</td>
							<td>${AllList.date }</td>
<%-- 						<td>${AllList.readcount }</td> --%>
						</tr>
						</c:forEach>
	 			</c:when>
	 			<c:otherwise>
					<tr><td colspan="5"> 게시물이 존재하지 않습니다</td></tr> 			
	 			</c:otherwise>
	 		</c:choose>
		</table>
		</section>
		
		<section id="pageList">
		<!-- 
		현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
		=> 클릭 시 BoardList.bo 서블릿 주소 요청하면서 
		   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
		-->
			<c:choose>
				<c:when test="${pageInfo.pageNum > 1}">
					<input type="button" value="이전" onclick="location.href='AllBoardList.ad?pageNum=${pageInfo.pageNum - 1}&keyword=${param.keyword }'">
				</c:when>
				<c:otherwise>
					<input type="button" value="이전" disabled="disabled">
				</c:otherwise>
			</c:choose>
				
			<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
					<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" >
						<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
						<c:choose>
							<c:when test="${pageInfo.pageNum eq i}">
								${i}
							</c:when>
							<c:otherwise>
								<a href="AllBoardList.ad?pageNum=${i}&keyword=${param.keyword }">${i} &nbsp;</a>
			
							</c:otherwise>
						</c:choose>
					</c:forEach>
				
			<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
			<c:choose>
				<c:when test="${pageInfo.pageNum lt pageInfo.maxPage}">
					<input type="button" value="다음" onclick="location.href='AllBoardList.ad?pageNum=${pageInfo.pageNum + 1}&keyword=${param.keyword }'">
				</c:when>
				<c:otherwise>
					<input type="button" value="다음" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</section>
		<div id="div"></div>
		<!-- 검색시 detail 부분 -->
		
		
	
</body>
</html>













