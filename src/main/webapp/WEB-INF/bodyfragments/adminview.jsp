<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/3/23
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin View</title>
    <style>
        <%@include file="/WEB-INF/css/admin-box.css"%>

    </style>
</head>
<body>
<header>
    <h1>Welcome Admin!</h1>
    <a href="#">Log out</a>
</header>
<div class="widgets">
    <div class="widget">
        <h2>Widget 1</h2>
        <p>Widget 1 content goes here</p>
    </div>
    <div class="widget">
        <h2>Widget 2</h2>
        <p>Widget 2 content goes here</p>
    </div>
    <div class="widget">
        <h2>Widget 3</h2>
        <p>Widget 3 content goes here</p>
    </div>
    <div class="widget">
        <h2>Widget 4</h2>
        <p>Widget 3 content goes here</p>
    </div>
    <div class="widget">
        <h2>Widget 5</h2>
        <p>Widget 3 content goes here</p>
    </div>
    <div class="widget">
        <h2>Widget 6</h2>
        <p>Widget 3 content goes here</p>
    </div>
</div>
<div class="user-list">
    <h2>User List</h2>
    <ul>
    <c:if test="${not empty adminUserList}">

        <c:forEach var="user" items="${adminUserList}"  varStatus="loop">


        <li> ${user.firstName} ${user.lastName}</li>



        </c:forEach>
    </c:if>
    </ul>
</div>
</body>
</html>

