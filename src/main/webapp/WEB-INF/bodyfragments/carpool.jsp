<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/16/23
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Carpool Page</title>
  <style>
    <%@include file="/WEB-INF/css/carpool-box.css"%>

  </style>
</head>
<body>
<header>
  <h1>Carpool Page</h1>
</header>
<main>
  <section id="create-carpool">
    <h2>Create a Carpool</h2>
    <form method ="post" action="${pageContext.request.contextPath}/createCarpool">
      <label for="carPoolName">Carpool Name:</label>
<%--      <s:bind path="carPoolName">--%>
      <input type="text" id="carPoolName" name="carPoolName" />
<%--      </s:bind>--%>

      <button type="submit">Create Carpool</button>
    </form>
  </section>
  <section id="invite-friends">
    <h2>Invite Friends to Your Carpool</h2>
    <form>
      <label for="friend-email">Friend's Email:</label>
      <input type="email" id="friend-email" name="friend-email"><br>
      <button type="submit">Invite</button>
    </form>
  </section>
  <section id="invitations">
    <h2>Invitations</h2>
    <ul>
      <li>
        <h3>Carpool Name</h3>
        <p>Pickup Location - Destination</p>
        <div class="invitation-buttons">
          <button class="accept">Accept</button>
          <button class="reject">Reject</button>
        </div>
      </li>
    </ul>
  </section>
</main>
</body>
</html>

