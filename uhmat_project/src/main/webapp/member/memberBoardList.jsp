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

</head>
<body>
		<!-- 게시판 리스트 -->
		<section id="listForm">
		<h2>${param.title} 게시물</h2>
		

		<table id="list">
			<tr id="tr_top">
			
				<td>번호</td>
				<td>제목</td>
				<td width="150px">작성자</td>
				<td width="150px">날짜</td>


			</tr>
			<!-- 게시물 목록 출력(단, 게시물이 하나라도 존재할 경우에만 출력) -> JSTL과 EL 활용-->
			<!-- JSTL의 c:choose 태그를 사용하여 게시물 존재 여부 판별 -->
			<!--  조건 : boardList 객체가 비어있지 않고 pageInfo 객체의 listCount가 0보다 클 경우 -->
	 		<c:choose>
	 			<c:when test="${not empty list and pageInfo.listCount gt 0 }">
					<!-- c:foreach 태그를 사용하여 boardList 객체의 BoardDTO 객체를 꺼내서 출력 --> 				
					<c:forEach var="AllList"  items="${list}" varStatus="status"> 
						<tr>
						<c:if test="${param.title =='Tmi'}">
						<td ><a href="TmiDetail.co?idx=${AllList.idx }&pageNum=1"> ${AllList.idx }</a></td>
						</c:if>
						<c:if test="${param.title =='FAQ'}">
						<td ><a href="FAQDetail.sc?idx=${AllList.idx }&pageNum=1&keyword="> ${AllList.idx }</a></td>
						</c:if>
						<c:if test="${param.title =='Mate'}">
						<td ><a href="MateDetail.co?idx=${AllList.idx }&pageNum=1"> ${AllList.idx }</a></td>
						</c:if>
						<c:if test="${param.title =='Recipe'}">
						<td ><a href="RecipeDetail.co?idx=${AllList.idx }&pageNum=1"> ${AllList.idx }</a></td>
						</c:if>
							<td >${AllList.idx }</td>
							<td>${AllList.subject }</td>
							<td>${AllList.nickname }</td>
							<td>${AllList.date }</td>
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
					<input type="button" value="이전" onclick="location.href='MemberBoardList.me?pageNum=${pageInfo.pageNum - 1}&keyword=${param.keyword }'">
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
								<a href="MemberBoardList.me?pageNum=${i}&keyword=${param.keyword }">${i} &nbsp;</a>
			
							</c:otherwise>
						</c:choose>
					</c:forEach>
				
			<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
			<c:choose>
				<c:when test="${pageInfo.pageNum lt pageInfo.maxPage}">
					<input type="button" value="다음" onclick="location.href='MemberBoardList.mepageNum=${pageInfo.pageNum + 1}&keyword=${param.keyword }'">
				</c:when>
				<c:otherwise>
					<input type="button" value="다음" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</section>
	
		
		
	
</body>
</html>













