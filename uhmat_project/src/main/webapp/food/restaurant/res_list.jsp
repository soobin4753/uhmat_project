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
	<h1>restaurantList Page</h1>
	
	<table border="1">
		<tr>
			<th> 식당이름 </th>
			<th> 별점 </th>
			<th> 리뷰 개수</th>
			<th> 사진 </th>
		</tr>
		
		<c:choose>
			<c:when test="${empty restaurantInfo }">
				<tr>
					<td colspan="4">
						게시된 식당이 없습니다.
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${restaurantInfo }" var="resInfo">
					<tr onclick="location.href='restaurantDetail.re?resName=${resInfo.resName}'">
						<td>${resInfo.resName }</td>
						<td>${resInfo.rating }</td>
						<td>${resInfo.reviewCount }</td>
						<td><img width="200" src="upload/${resInfo.photo }"></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<button onclick="location.href='restaurantWriteForm.re'">글쓰기</button>

</body>
</html>