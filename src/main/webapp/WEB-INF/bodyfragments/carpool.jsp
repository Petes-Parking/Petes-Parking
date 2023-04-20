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
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
  <style>
    <%@include file="/WEB-INF/css/main-page.css"%>
    <%@include file="/WEB-INF/css/carpool-box.css"%>
  </style>
</head>
<body>
<div class="sidebar">
  <div class="profile-area">
    <img class="profile-pic" src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture">
    <a href="${pageContext.request.contextPath}/profile">
      <button class="profile-button">${user.firstName} ${user.lastName}</button>
    </a>
    <a href="${pageContext.request.contextPath}/main">
      <button class="home-button"><img style="width: 80%; height: 70%;" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png"></button>
    </a>
  </div>

  <div class="button-area">
    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" style="width: 32%; margin-left: 2%">
    <a href="${pageContext.request.contextPath}/parkinglist">
      <button class="sidebarPageButtons">Book Reservation</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" style="width: 24%; margin-left: 2%; display: block;"/>
    <a href="${pageContext.request.contextPath}/history">
      <button class="sidebarPageButtons">View Favorite Parkings</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" style="width: 23%; margin-left: 6%"/>
    <a href="${pageContext.request.contextPath}/parkingpals">
      <button class="sidebarPageButtons">Parking Pals</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" style="width: 22%; margin-left: 7%"/>
    <a href="${pageContext.request.contextPath}/carpool">
      <button class="sidebarPageButtons">Carpools</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/referrals.png" style="width: 23%; margin-left: 6%"/>
    <a href="${pageContext.request.contextPath}/referrals">
      <button class="sidebarPageButtons">My Referrals</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" style="width: 23%; margin-left: 7%"/>
    <a href="${pageContext.request.contextPath}/settings">
      <button class="sidebarPageButtons">Settings</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" style="width: 26%; margin-left: 6%; margin-right: 4%"/>
    <div class="dropdown">
      <button style="width: 100%; position: relative; padding: 13px 30px; margin-top: 6%; background-color: #D6C49B; border-radius: 5px;">File a Report</button>
      <div class="dropdown-content1">
        <a href="${pageContext.request.contextPath}/exp-report">Expiration Report</a>
        <a href="${pageContext.request.contextPath}/poorpark-report">Poor Parking Report</a>
      </div>
    </div>
  </div>
</div>

<main style="display: inline-block; position: relative; vertical-align: top; width: 35%; height: 100%; ">
<section id="create-carpool" style="width: 100%; ">
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
    <a href="${pageContext.request.contextPath}/leaveCarpool" class="btn btn-danger" style="margin-top: 12.4%;">Leave Carpool</a>
  </c:if>
</section>
  <section id="invitations" class="mt-4" style="width: 100%; height: 61%;">
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
      <button class="btn btn-success" type="submit" name="action" value="accept" style="margin-top: 55%;">Accept</button>
      <button class="btn btn-danger" type="submit" name="action" value="reject" style="margin-top: 55%;">Reject</button>
    </form>
  </section>
</main>

<div style="display: inline-block; position: relative; vertical-align: top; margin-top: 5%; width: 30%; height: 100%;">
<div class="container my-4" style="display: inline-block; position: relative; vertical-align: top; margin-top: 1%; width: 100%; height: 5%; float: right;">
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
</div>
<%--<div style="float: right; vertical-align: top; position: relative; display: inline-block; width: 30%;">--%>
  <div style="height: 5%;"></div>
  <c:if test="${hasCarpool}">
  <section id="reservations-carpool" class="mt-4" style="position: relative; vertical-align: top; width: 100%; margin-top: 10%; float: right;">
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


<c:if test="${hasCarpool}">
  <section id="carpool-members" class="mt-4" style="position: relative; vertical-align: top; width: 100%; float: right;">
    <h2>Carpool Members</h2>
    <ul class="list-unstyled">
      <c:forEach items="${members}" var="member">
        <li>${member}</li>
      </c:forEach>
    </ul>
  </section>
</c:if>

<c:if test="${hasCarpool && isLeader}">
  <section id="invite-friends" class="mt-4" style="position: relative; vertical-align: bottom; width: 100%; float: right;">
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
</div>
<%--</div>--%>
</body>
</html>
