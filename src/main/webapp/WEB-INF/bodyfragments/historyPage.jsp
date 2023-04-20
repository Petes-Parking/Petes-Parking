<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>History Page</title>
  <style>
    .customButton {
      background-color: #d5c39a;
      border: none;
      color: #373A36;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 18px;
      margin: 4px 2px;
      cursor: pointer;
      border-radius: 12px;
      transition-duration: 0.4s;
    }

    .customButton:hover {
      background-color: #45a049;
      color: #373A36;
      font-size: larger;
    }
  </style>
  <script>
    function generateLists() {
      let historyList = document.getElementById("historyList").getAttribute("data-histories");
      historyList = historyList.substring(1, historyList.length-1);
      let histList = historyList.split(", ");
      if (histList.at(0) === "") {
        let a = document.createElement("a");
        a.setAttribute("href", "${pageContext.request.contextPath}/parkinglist");
        let button = document.createElement("button");
        button.appendChild(document.createTextNode("Go to Booking"));
        button.className = "bookButton";
        a.appendChild(button);
        document.getElementById("bookShortcut").appendChild(a);
      } else {
        for (let i = 0; i < histList.length; i++) {
          let button = document.createElement("button");
          let buttonName = document.createTextNode(histList.at(i));
          button.appendChild(buttonName);
          button.className = "historyButton customButton";
          document.getElementById("historyList").appendChild(button);
        }
      }
      let favoriteList = document.getElementById("favoritesList").getAttribute("data-favorites");
      favoriteList = favoriteList.substring(1, favoriteList.length-1);
      let favList = favoriteList.split(", ");
      if (favList.at(0) === "") {
      } else {
        for (let i = 0; i < favList.length; i++) {
          let button = document.createElement("button");
          let buttonName = document.createTextNode(favList.at(i));
          button.appendChild(buttonName);
          button.className = "favoritesButton customButton";
          let a = document.createElement("a");
          if (favList.at(i) === "Corec Parking Lot") {
            a.setAttribute("href", "${pageContext.request.contextPath}/booking?id=1");
          }
          if (favList.at(i) === "McCutcheon Drive Parking Garage") {
            a.setAttribute("href", "${pageContext.request.contextPath}/booking?id=2");
          }
          if (favList.at(i) === "University St. Parking Garage") {
            a.setAttribute("href", "${pageContext.request.contextPath}/booking?id=3");
          }
          if (favList.at(i) === "Ross-Ade Stadium Parking Lot") {
            a.setAttribute("href", "${pageContext.request.contextPath}/booking?id=4");
          }
          if (favList.at(i) === "Northwestern St. Parking Garage") {
            a.setAttribute("href", "${pageContext.request.contextPath}/booking?id=5");
          }
          if (favList.at(i) === "Grant St. Parking Garage") {
            a.setAttribute("href", "${pageContext.request.contextPath}/booking?id=6");
          }
          a.appendChild(button);
          document.getElementById("favoritesList").appendChild(a);
        }
      }
    }
  </script>
  <link href="main-page.css" rel="stylesheet" type="text/css">
  <link href="../css/history-page.css" rel="stylesheet" type="text/css">
  <style>
    <%@include file="/WEB-INF/css/main-page.css"%>
    <%@include file="/WEB-INF/css/history-page.css"%>
  </style>

</head>

<body style="background-color: #373A36;">

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

    <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" style="width: 24%; margin-left: 6%"/>
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

<div class="box">
  <body onload="generateLists()"></body>
  <div class="history-area">
    <h1>Parking History</h1>
    <div id="historyList" data-histories="${histories}" style="overflow-y: scroll; height: 80%; margin-left: 1%;">
    </div>
  </div>

  <div class="favorites-area">
    <h1>Favorite Parking Areas</h1>
    <div id="favoritesList" data-favorites="${favorites}" style="overflow-y: scroll; height: 80%; margin-left: 1%;">
    </div>
  </div>

  <div id="bookShortcut">
  </div>
</div>

</body>
</html>