<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/15/23
  Time: 1:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<body>
<div id="header">
  <button class="button">Back</button>
  <h2 style="margin: auto; margin-left: 525px;">Global Leaderboard</h2>
</div>
<div id="leaderboard-page">
  <c:if test="${not empty leaderboardList}">
    <c:forEach items="${leaderboardList}" var="user">


      <div class="entry"><p> <b>#(Count)</b> Name: ${user.firstName} ${user.lastName}  | Points: ${user.points} | Level: 1</p></div>

    </c:forEach>
</c:if>


  <div id="buttons-layout">
    <button class="button">←</button>
    <button class="button">→</button>
  </div>

</div>
</body>
</html>
