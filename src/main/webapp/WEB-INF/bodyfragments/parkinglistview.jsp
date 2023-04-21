<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>User List</title>
  <style><%@include file="/WEB-INF/css/main-page.css"%></style>
</head>
<body >
<div class="sidebar">
  <div class="profile-area">
    <img class="profile-pic" src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture">
    <a href="${pageContext.request.contextPath}/profile">
      <button id="profileBtn" class="profile-button">${user.firstName} ${user.lastName}</button>
    </a>
    <a href="${pageContext.request.contextPath}/main">
      <button id="homeBtn" class="home-button"><img id="homeIcon" style="width: 62%; height: 65%;" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png"></button>
    </a>
  </div>

  <div class="button-area">
    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" style="width: 32%; margin-left: 2%">
    <a href="${pageContext.request.contextPath}/parkinglist">
      <button id="bookBtn" class="sidebarPageButtons">Book Reservation</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" style="width: 24%; margin-left: 6%"/>
    <a href="${pageContext.request.contextPath}/history">
      <button id="starBtn" class="sidebarPageButtons">View Favorite Parkings</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" style="width: 23%; margin-left: 6%"/>
    <a href="${pageContext.request.contextPath}/parkingpals">
      <button id="palBtn" class="sidebarPageButtons">Parking Pals</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" style="width: 22%; margin-left: 7%"/>
    <a href="${pageContext.request.contextPath}/carpool">
      <button id="carBtn" class="sidebarPageButtons">Carpools</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/referrals.png" style="width: 23%; margin-left: 6%"/>
    <a href="${pageContext.request.contextPath}/referrals">
      <button id="refBtn" class="sidebarPageButtons">My Referrals</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" style="width: 23%; margin-left: 7%"/>
    <a href="${pageContext.request.contextPath}/settings">
      <button id="settingsBtn" class="sidebarPageButtons">Settings</button>
    </a>

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" style="width: 26%; margin-left: 6%; margin-right: 4%"/>
    <div class="dropdown">
      <button id="reportBtn" style="width: 100%; position: relative; padding: 13px 30px; margin-top: 6%; background-color: #D6C49B; border-radius: 5px;">File a Report</button>
      <div class="dropdown-content1">
        <a href="${pageContext.request.contextPath}/exp-report">Expiration Report</a>
        <a href="${pageContext.request.contextPath}/poorpark-report">Poor Parking Report</a>
      </div>
    </div>
  </div>
</div>
<div class="container" style="background-color: #CEB888; border-radius: 20px; display: inline-block; vertical-align: top; margin-top: .8%; height: 90%; width: 75%; margin-left: 2%;">

  <h2 style="padding: 30px; margin-top: 2%;">Parking List</h2>
  <%@include file="businessMessage.jsp" %>
  <table class="table text-dark" bgcolor="#CEB888" style="margin-top: 3%; padding-bottom: 5%; margin-bottom: 5%;">
    <thead>
    <tr>
      <th scope="col" style="background-color: #B89954;">Parking Name</th>
      <th scope="col" style="background-color: #B89954;">Address</th>
      <th scope="col" style="background-color: #B89954;">Number of Slots</th>
      <c:choose>
        <c:when test="${sessionScope.user.userRole=='Admin'}">
          <th scope="col">Action</th>
        </c:when>
      </c:choose>
      <th scope="col" style="background-color: #B89954;">View Parking Slot</th>
      <th scope="col" style="background-color: #B89954;">Book</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="li" varStatus="u">
      <tr>
        <td>${li.parkingName}</td>
        <td>${li.address}</td>
        <td>${li.numberOfSlot}</td>

        <c:choose>
          <c:when test="${sessionScope.user.userRole=='Admin'}">
            <td>
              <a href="${pageContext.request.contextPath}/parkingEdit?id=${li.id}">Edit</a>
              <a href="${pageContext.request.contextPath}/parkingDelete?id=${li.id}">Delete</a>
            </td>
          </c:when>
        </c:choose>

        <td>
          <a href="${pageContext.request.contextPath}/viewparkingSlot?id=${li.id}">View</a>
        </td>
        <td>
          <a href="${pageContext.request.contextPath}/booking?id=${li.id}">Book</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div height="10"></div>

</div>
<script>
  window.onload = function colorSwap() {
    const sidebar = document.querySelector('.sidebar');
    const profileBtn = document.getElementById('profileBtn');
    const homeBtn = document.getElementById('homeBtn');
    const homeIcon = document.getElementById('homeIcon');
    const bookBtn = document.getElementById('bookBtn');
    const starBtn = document.getElementById('starBtn');
    const palBtn = document.getElementById('palBtn');
    const carBtn = document.getElementById('carBtn');
    const refBtn = document.getElementById('refBtn');
    const settingsBtn = document.getElementById('settingsBtn');
    const main = document.getElementById('main');
    const parkingTitle = document.getElementById('parkingTitle');
    const body = document.getElementById('body');
    const container = document.querySelector('.container');

// Check if themePreference is already set in localStorage
    if (!localStorage.getItem('themePreference')) {
      // Set default value if themePreference is not set
      localStorage.setItem('themePreference', "light");
    }

// Get the user's theme preference
    const savedMode = localStorage.getItem('themePreference');

// Set the radio button based on the current mode
    if (savedMode === "light") {
      updateTheme("light");
    } else if (savedMode === "dark") {
      updateTheme("dark");
    }

    function updateTheme(mode) {
      if (mode === "light") {
        sidebar.style.backgroundColor = "#CEB888";
        container.style.backgroundColor = "#CEB888";
      } else if (mode === "dark") {
        sidebar.style.backgroundColor = "#565656";
        profileBtn.style.backgroundColor = "#333";
        homeBtn.style.backgroundColor = "#333";
        bookBtn.style.backgroundColor = "#333";
        starBtn.style.backgroundColor = "#333";
        palBtn.style.backgroundColor = "#333";
        carBtn.style.backgroundColor = "#333";
        refBtn.style.backgroundColor = "#333";
        settingsBtn.style.backgroundColor = "#333";
        reportBtn.style.backgroundColor = "#333";
        profileBtn.style.color = "#fff";
        homeIcon.style.filter = "invert(1)";
        bookBtn.style.color = "#fff";
        starBtn.style.color = "#fff";
        palBtn.style.color = "#fff";
        carBtn.style.color = "#fff";
        refBtn.style.color = "#fff";
        settingsBtn.style.color = "#fff";
        reportBtn.style.color = "#fff";
        main.style.backgroundColor = "#565656";
        container.style.backgroundColor = "red";

      }
    }
  }
</script>
</body>
</html>