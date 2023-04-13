<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 4/13/23
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Booking Payment</title>
</head>
<body>
<h1>Book in-advance payment</h1>
<p>Points required: ${requiredPoints}</p>
<p>Your available points: ${userPoints}</p>

<c:choose>
  <c:when test="${userPoints >= requiredPoints}">
    <form action="${pageContext.request.contextPath}/booking/confirmPayment" method="post">
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
    </form>
  </c:when>
  <c:otherwise>
    <p>You don't have enough points to make this booking.</p>
  </c:otherwise>
</c:choose>
</body>
</html>
