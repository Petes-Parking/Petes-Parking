<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/3/23
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>view-history</title>
</head>
<style type="text/css">
    <%@include file="/WEB-INF/css/adminview-box.css"%>

    body {
        background-image: url(https://photos.smugmug.com/photos/i-Z5PSS6Q/0/L/i-Z5PSS6Q-L.jpg);
        background-size: cover;
        background-repeat: no-repeat;


    }
</style>

<body>
<div id="admin-box">
    <h2 style="margin:auto; margin-left: 350px;">View History</h2>

    <div>

        <c:if test="${not empty dateList}">
        <ul>
            <c:forEach items="${dateList}" var="dateList">
                <li style=""> ${dateList}
                </li>
            </c:forEach>
        </ul>
    </div>
    </c:if>

        <a href="/PeteParkingMgt/adminview">

        <input style="margin-left: 350px;" type="submit" id="back-button" value="Back to Admin View">
        </a>
    </div>


</div>

</body>
</html>
