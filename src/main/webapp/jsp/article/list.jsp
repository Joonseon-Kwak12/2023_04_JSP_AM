<%@page import="com.KoreaIT.java.jam.dto.Article"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트</title>
</head>
<body>
	<%@ include file="../part/topbar.jspf" %>

	<h1>게시물 리스트</h1>

	<table style="border-collapse: collapse; border-color: green"
		border="2px">
		<tr>
			<th>번호</th>
			<th>작성날짜</th>
			<th>제목</th>
			<th>작성자</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		
		<%
		for (Article article : articles) {
		%>
		<tr style="text-align: center;">
			<th><%=article.id%></th>
			<th><%=article.regDate%></th>
			<th><a href="detail?id=<%=article.id%>"><%=article.title%></a></th>
			<th><%=article.extra__writer%></th>
			<th><a href="modify?id=<%=article.id%>">수정</a></th>
			<th><a href="doDelete?id=<%=article.id%>">삭제</a></th>
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
		<%
		if (cPage > 1) {
		%>
		<a href = "list?page=1">◀◀</a>
		<%
		}
		int pageSize = 5;
		int from = cPage - pageSize;
		if (from < 1) {
			from =1;
		}
		
		int end = cPage + pageSize;
		if (end > totalPage) {
			end = totalPage;
		}
		
		for (int i = from; i <= end; i++) {
		%>
		<a class="<%=cPage == i ? "red" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		%>
		
		<%
		if (cPage < totalPage) {
		%>
		<a href="list?page=<%=totalPage%>">▶▶</a>
		<%
		}
		%>
	</div>
</body>
</html>