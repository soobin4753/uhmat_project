<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 자리 -->
	
	<!-- 탭 형식의 커뮤니티페이지 내의 서브 페이지 -->
	
	<!-- 게시판리스트 -->	
	<table border="1">
	<c:choose>
		<c:when test="${not empty tmiBoardList and tmiPageInfo.listCount gt 0 }">
				<c:forEach var="tmi" items="${tmiBoardList }">
					<tr>
						<td width="800" height="50"><a href="TmiDetail.co?idx=${tmi.idx }&pageNum=${tmiPageInfo.pageNum}">${tmi.subject }</a> <br> 
							${tmi.nickname } | ${tmi.readcount } | ${tmi.date }</td>
					</tr>
					
				</c:forEach>
		</c:when>
		
		<c:when test="${not empty tmiPageInfo.listCount }">
			<c:forEach var="tmiBoardList" items="${tmiBoardList }"> 
				<tr>
					<td>${tmiBoardList.idx }</td>
					<td id="subject">
						<a href="TmiDetail.co?idx=${tmiBoardList.idx }&pageNum=${tmiPageInfo.pageNum}&keyword=${param.keyword }">
							${tmiBoardList.subject }
						</a>
					</td>
					<td>${tmiBoardList.nickname }</td>
					<td>${tmiBoardList.datetime }</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr><td colspan="5"> 게시물이 존재하지 않습니다</td></tr> 			
		</c:otherwise>
 	</c:choose>	
	</table>
	
	<form action="TmiList.co" method="get">
		<input type="text" name="keyword" value="${param.keyword }" placeholder="검색어를 입력하세요">
		<input type="submit" value="검색">
	</form>
	
	<section>
		<input type="button" value="글쓰기" onclick="location.href='TmiWriteForm.co'">
		<input type="button" value="메인" onclick="location.href='./'">
	</section>
	<!-- 
	현재 페이지 번호(pageNum)가 1보다 클 경우에만 [이전] 링크 동작
	=> 클릭 시 TmiList.jsp 로 이동하면서 
	   현재 페이지 번호(pageNum) - 1 값을 page 파라미터로 전달
	-->
	<section>
	<c:choose>
		<c:when test="${tmiPageInfo.pageNum > 1 }">
			<input type="button" value="이전" onclick="location.href='TmiList.co?pageNum=${tmiPageInfo.pageNum - 1}&keyword=${param.keyword }'">
		</c:when>
		<c:otherwise>
			<input type="button" value="이전" disabled="disabled">
		</c:otherwise>
	</c:choose>
			
	<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
	
	<c:forEach var="i" begin="${tmiPageInfo.startPage }" end="${tmiPageInfo.endPage }" >
			<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
			<c:choose>
				<c:when test="${tmiPageInfo.pageNum eq i}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="TmiList.co?pageNum=${i}&keyword=${param.keyword }">${i} &nbsp;</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		
		<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
		<c:choose>
			<c:when test="${tmiPageInfo.pageNum lt tmiPageInfo.maxPage}">
				<input type="button" value="다음" onclick="location.href='TmiList.co?pageNum=${tmiPageInfo.pageNum + 1}&keyword=${param.keyword }'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음" disabled="disabled">
			</c:otherwise>
		</c:choose>
	</section>
	<!-- 푸터 자리 -->
	
</body>
</html>