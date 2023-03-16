<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/15/23
  Time: 1:01 AM


  Current goals:
  Need to create paging
  Potentially create a better design for the leaderboard that shows the points and levels in a more standout way
--%>

<c:set var="ENTRIES_PER_PAGE" value="10" />
<c:set var="currentPage" value="${param.page ne null ? param.page : 1}" />
<c:set var="startIndex" value="${(currentPage - 1) * ENTRIES_PER_PAGE}" />
<c:set var="endIndex" value="${fn:length(leaderboardList)}" />

<c:if test="${startIndex + ENTRIES_PER_PAGE > fn:length(leaderboardList)}">
  <c:set var="endIndex" value="${fn:length(leaderboardList)}" />
</c:if>

<c:if test="${startIndex + ENTRIES_PER_PAGE <= fn:length(leaderboardList)}">
  <c:set var="endIndex" value="${startIndex + ENTRIES_PER_PAGE}" />
</c:if>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%!
  int min(int a, int b) {
  return Math.min(a, b);
  }
%>

<%--
current page = 1

--%>


<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Leaderboard</title>
</head>
<style type="text/css">
  <%@include file="/WEB-INF/css/leaderboard.css"%>

  body {
    background-color: black;
    background-size: cover;
    background-repeat: no-repeat;


  }
</style>


<%--
Design for paginated leaderboard.

Thinking of doing a way where an integer represents a page of 10.
For example, "1" would signify the first page and would be a model attribute, which would grab the first ten
entries. This assumes that the list is continuous except at end points.
--%>
<body>
<div id="header">
  <a href="${pageContext.request.contextPath}/main">
    <button class="button" type="button" >Back</button>
  </a>
  <h2 style="margin: auto; margin-left: 525px;">Global Leaderboard</h2>
</div>
<div id="leaderboard-page">
  <c:if test="${not empty leaderboardList}">
<%--    <c:forEach items="${leaderboardList}" var="user" varStatus="loop">--%>

    <c:forEach var="user" items="${leaderboardList}" begin="${startIndex}" end="${endIndex}" varStatus="loop">



       <div class="entry"><p> <b>#${loop.count}</b> Name: ${user.firstName} ${user.lastName}  | Points: ${user.points} | Level: 1</p></div>
    </c:forEach>
</c:if>


  <c:url var="prevUrl" value="?page=${currentPage - 1}" />
  <c:url var="nextUrl" value="?page=${currentPage + 1}" />

  <div id="buttons-layout">
    <c:if test="${currentPage > 1}">
      <a href=${prevUrl}>
        <button class="button">←</button>
      </a>
    </c:if>


    <a href="${nextUrl}">
      <button class="button">→</button>
    </a>
  </div>

</div>
</body>
</html>


