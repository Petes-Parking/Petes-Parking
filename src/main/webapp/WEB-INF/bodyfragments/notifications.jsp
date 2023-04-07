<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Notifications</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }

    .container {
      width: 80%;
      margin: 0 auto;
    }

    .notification-list {
      list-style: none;
      padding: 0;
    }

    .notification-list li {
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    .notification-list li a {
      text-decoration: none;
      color: #333;
      display: block;
    }

    .timestamp {
      font-size: 12px;
      color: #777;
      margin-left: 10px;
    }



  </style>
</head>
<body>
<div class="container">
  <h1>Notifications</h1>
  <ul class="notification-list">
    <c:forEach var="notification" items="${notifications}">
      <li>
<%--        <a href="${notification.url}">--%>
            ${notification.message}
          <span class="timestamp">${notification.timeStamp}</span>
<%--        </a>--%>
      </li>
    </c:forEach>
  </ul>
</div>
</body>
</html>
