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
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
  <style>
    <%@include file="/WEB-INF/css/carpool-box.css"%>
  </style>
</head>
<body>
<header class="bg-primary text-white py-3">
  <div class="container">
    <h1 class="text-center">Carpool Page</h1>
  </div>
</header>

<div class="container my-4">
<c:if test="${not empty messages}">
  <div class="alert alert-success">
    <ul class="list-unstyled">
      <c:forEach items="${messages}" var="message">
        <li>${message}</li>
      </c:forEach>
    </ul>
  </div>
</c:if>

<c:if test="${not empty errors}">
  <div class="alert alert-danger">
    <ul class="list-unstyled">
      <c:forEach items="${errors}" var="error">
        <li>${error}</li>
      </c:forEach>
    </ul>
  </div>
</c:if>

<main>
<section id="create-carpool">
  <c:if test="${hasCarpool == false}">
    <h2>Create a Carpool</h2>
    <form method="post" action="${pageContext.request.contextPath}/createCarpool">
      <div class="mb-3">
        <label for="carPoolName" class="form-label">Carpool Name:</label>
        <input type="text" class="form-control" id="carPoolName" name="carPoolName" />
      </div>
      <button type="submit" class="btn btn-primary">Create Carpool</button>
    </form>
  </c:if>
  <c:if test="${hasCarpool == true}">
    <h2>Your Carpool</h2>
    <p>Name: ${carPoolName}</p>
    <a href="${pageContext.request.contextPath}/leaveCarpool" class="btn btn-danger">Leave Carpool</a>
  </c:if>
</section>

<c:if test="${hasCarpool}">
  <section id="reservations-carpool" class="mt-4">
  <h2>${carPoolName}'s reservations</h2>
  <c:if test="${hasReservation}">
    <h4>${reservation.parkingName}</h4>
    <p>${reservation.fromBookingDate} - ${reservation.toBookingDate}</p>
    <p>${reservation.fromTime} to ${reservation.toTime}</p>
  </c:if>
    <c:if test="${!hasReservation}">
      <h4>No active reservations!</h4>
    </c:if>
  </section>
  </c:if>
  <c:if test="${hasCarpool && isLeader}">
    <section id="invite-friends" class="mt-4">
      <h2>Invite Friends to Your Carpool</h2>
      <form method="post" action="${pageContext.request.contextPath}/carpoolInvite">
        <div class="mb-3">
          <label for="friend-email" class="form-label">Friend's Email:</label>
          <input type="email" class="form-control" id="friend-email" name="email">
        </div>
        <button type="submit" class="btn btn-primary">Invite</button>
      </form>
    </section>
  </c:if>

  <c:if test="${hasCarpool}">
    <section id="carpool-members" class="mt-4">
      <h2>Carpool Members</h2>
      <ul class="list-unstyled">
        <c:forEach items="${members}" var="member">
          <li>${member}</li>
        </c:forEach>
      </ul>
    </section>
  </c:if>

  <section id="invitations" class="mt-4">
    <h2>Invitations</h2>
    <h3>Carpool Name</h3>
    <form action="${pageContext.request.contextPath}/inviteResponse" method="post">
      <div class="mb-3">
        <label for="carPoolName" class="form-label">Select Carpool</label>
        <select name="carPoolName" class="form-select">
          <c:forEach items="${invitations}" var="invite">
            <option name="option" value="${invite}">${invite}</option>
          </c:forEach>
        </select>
      </div>
      <button class="btn btn-success" type="submit" name="action" value="accept">Accept</button>
      <button class="btn btn-danger" type="submit" name="action" value="reject">Reject</button>
    </form>
  </section>
  <a href="${pageContext.request.contextPath}/main" class="btn btn-secondary mt-4">Back</a>
</main>
</div>
</body>
</html>
