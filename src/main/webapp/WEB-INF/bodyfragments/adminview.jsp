<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/3/23
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>admin-view</title>
    <link href="register-box.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
    <%@include file="/WEB-INF/css/admin-box.css"%>

    body {
        background-image: url(https://photos.smugmug.com/photos/i-Z5PSS6Q/0/L/i-Z5PSS6Q-L.jpg);
        background-size: cover;
        background-repeat: no-repeat;


    }
</style>

<body>
<div id="admin-box">
    <h2 style="margin:auto; margin-left: 350px;">Admin View</h2>

    <div>
        <nav>

            <c:if test="${not empty userlist}">
                    <ul>
                        <c:forEach items="${userlist}" var="userlist">
                            <li style="">
                                <form method="post" action="${pageContext.request.contextPath}/viewuser">
                                <input type="submit" value="${userlist}">
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>


        </nav>

        <a href="/PeteParkingMgt/">

        <input style="margin-left: 350px;" type="submit" id="back-button" value="Back to Login">
        </a>
    </div>


</div>

</body>
</html>
