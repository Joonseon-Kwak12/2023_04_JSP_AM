<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
int itemsInAPage = (int) request.getAttribute("itemsInAPage");
int pageDiv = (int) Math.ceil((double)cPage / itemsInAPage);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>
	<div>
		<a href="write">글쓰기</a>
	</div>

	<h1>게시물 리스트</h1>

	<table style="border-collapse: collapse; border-color: green"
		border="2px">
		<tr>
			<th>번호</th>
			<th>작성날짜</th>
			<th>제목</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>

		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<tr style="text-align: center;">
			<th><%=articleRow.get("id")%></th>
			<th><%=articleRow.get("regDate")%></th>
			<th><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></th>
			<th><a href="modify?id=<%=articleRow.get("id")%>">수정</a></th>
			<th><a href="doDelete?id=<%=articleRow.get("id")%>">삭제</a></th>
		</tr>
		<%
		}
		%>
	</table>
	
	<style type="text/css">
	.page > a {
		color: black;
		text-decoration: none;
	}
	.page > a.red {
		color: red;
	}
	</style>
	
	<div class="page">
		<a href="list?page=<%=(pageDiv-1)*itemsInAPage%>"><</a>
		<%
		for (int i = pageDiv*itemsInAPage-9; i <= pageDiv*itemsInAPage; i++) {
		%>
		<a class="<%=cPage == i ? "red" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		%>
		<a href="list?page=<%=(pageDiv+1)*itemsInAPage-9%>">></a>
	</div>

</body>
</html>