<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Booking Payment</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: darkgoldenrod;
      color: white;
    }
    .container, .info-container {
      background-color: white;
      border-radius: 10px;
      padding: 30px;
      max-width: 500px;
      margin: 50px auto;
      color: black;
    }
    h1 {
      font-size: 36px;
      text-align: center;
      margin-bottom: 30px;
    }
    p {
      font-size: 18px;
      text-align: center;
    }
    input[type="submit"], input[type="button"] {
      display: block;
      width: 100%;
      background-color: goldenrod;
      border: none;
      padding: 15px;
      color: white;
      font-size: 18px;
      border-radius: 5px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: darkgoldenrod;
    }
    input[type="button"]:hover {
      background-color: darkgoldenrod;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Book in-advance payment</h1>
  <p>Points required: ${requiredPoints}</p>
  <p>Your available points: ${userPoints}</p>

  <c:choose>
    <c:when test="${userPoints >= requiredPoints}">
      <form action="${pageContext.request.contextPath}/booking/confirmPayment" method="post">
        <!-- hidden inputs -->
        <input type="hidden" id="firstName" name="firstName" value="${firstName}" />
        <input type="hidden" id="lastName" name="lastName" value="${lastName}" />
        <input type="hidden" id="email" name="email" value="${email}" />
        <input type="hidden" id="phoneNumber" name="phoneNumber" value="${phoneNumber}" />
        <input type="hidden" id="parkingName" name="parkingName" value="${parkingName}" />
        <input type="hidden" id="slotId" name="slotId" value="${slotId}" />
        <input type="hidden" id="fromBookingDate" name="fromBookingDate" value="${fromBookingDate}" />
        <input type="hidden" id="toBookingDate" name="toBookingDate" value="${toBookingDate}" />
        <input type="hidden" id="fromTime" name="fromTime" value="${fromTime}" />
        <input type="hidden" id="toTime" name="toTime" value="${toTime}" />
        <input type="hidden" id="cost" name="cost" value="${requiredPoints}" />

        <input type="submit" value="Confirm Payment" />
        <input type="button" style="margin-top: 10px;" value="Cancel Payment" onclick="location.href='${pageContext.request.contextPath}/parkinglist'" />

      </form>
    </c:when>
    <c:otherwise>
      <p>You don't have enough points to make this booking.</p>
      <input type="button" value="Back" onclick="location.href='${pageContext.request.contextPath}/parkinglist'" />

    </c:otherwise>
  </c:choose>
</div>
<div class="info-container">
  <h2 style="margin: auto">Booking Information</h2>
  <p>Parking Name: ${parkingName}</p>
  <p>Slot ID: ${slotId}</p>
  <p>From Booking Date: ${fromBookingDate}</p>
  <p>To Booking Date: ${toBookingDate}</p>
  <p>From Time: ${fromTime}</p>
  <p>To Time: ${toTime}</p>
  <p>Cost: ${requiredPoints} points</p>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
