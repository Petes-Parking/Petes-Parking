<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <c:if test="${not empty messages}">
    <div class="alert alert-danger">
      <ul>
        <c:forEach items="${messages}" var="message">
          <li style="font-size: 20px; color: limegreen">${message}</li>
        </c:forEach>
      </ul>
    </div>
  </c:if>

  <c:if test="${not empty errors}">
    <div class="alert alert-danger">
      <ul>
        <c:forEach items="${errors}" var="error">
          <li style="font-size: 20px; color: firebrick">${error}</li>
        </c:forEach>
      </ul>
    </div>
  </c:if>
</header>
<main>
  <section id="create-carpool">
    <c:if test="${hasCarpool == false}">
    <h2>Create a Carpool</h2>
    <form method ="post" action="${pageContext.request.contextPath}/createCarpool">
      <label for="carPoolName">Carpool Name:</label>
      <input type="text" id="carPoolName" name="carPoolName" />

      <button type="submit">Create Carpool</button>
    </form>
    </c:if>
    <c:if test="${hasCarpool == true}">
      <h2>Your Carpool</h2><br>
      <p>Name: ${carPoolName}</p><br>
      <a href="${pageContext.request.contextPath}/leaveCarpool">
      <button type="submit">Leave Carpool</button>
      </a>

    </c:if>
  </section>
<c:if test="${hasCarpool && isLeader}">
<section id="invite-friends">
    <h2>Invite Friends to Your Carpool</h2>
    <form method = "post" action="${pageContext.request.contextPath}/carpoolInvite">
      <label for="friend-email">Friend's Email:</label>
      <input type="email" id="friend-email" name="email"><br>
      <button type="submit">Invite</button>
    </form>
  </section>
</c:if>

  <c:if test="${hasCarpool}">
    <section id="carpool-members">
      <h2>Carpool Members</h2>

      <ul>

      <c:forEach items="${members}" var="member">
        <li>${member.firstName} ${member.lastName}</li>

      </c:forEach>
      </ul>



    </section>



  </c:if>
  <section id="invitations">
    <h2>Invitations</h2>
        <h3>Carpool Name</h3>

    <form action="${pageContext.request.contextPath}/inviteResponse" method="post">
      <select name="carPoolName">

    <c:forEach items="${invitations}" var="invite">
        <option name="option" value="${invite}"> ${invite}</option>

    </c:forEach>

      </select>
      <br><br>

      <button class="accept" type="submit" name="action" value="accept">Accept</button>
      <button class="reject" type="submit" name="action" value="reject">Reject</button>
    </form>



  <%--    <label>--%>
<%--      <select name="invitationList">--%>
<%--      <c:forEach items="${invitations}" var="invite">--%>
<%--        <c:out value="${invite}"/>--%>
<%--        <option value="${invite}">${invite}</option>--%>
<%--      </c:forEach>--%>
<%--      </select>--%>
<%--    </label>--%>

<%--    <%--%>
<%--      String selectedInvite = request.getParameter("invitationList");--%>
<%--      System.out.println(selectedInvite + " in JSP.");--%>
<%--      String option = selectedInvite != null ? selectedInvite : "null";--%>
<%--      pageContext.setAttribute("choice", option);--%>

<%--    %>--%>
<%--    <div class="invitation-buttons">--%>
<%--      <form method="post" action="${pageContext.request.contextPath}/acceptInvite">--%>
<%--        <input class="accept" type="submit" name="carPoolName" value="Accept"/>--%>
<%--        <input type="hidden" name="carPoolName" value="${choice}" />--%>
<%--      </form>--%>
<%--      <form method="post" action="${pageContext.request.contextPath}/rejectInvite">--%>
<%--        <input class="reject" type="submit" name="carPoolName" value="Reject"/>--%>
<%--        <input type="hidden" name="carpoolName" value="${choice}" />--%>

<%--      </form>--%>
<%--    </div>--%>

  </section>
</main>
</body>
</html>
