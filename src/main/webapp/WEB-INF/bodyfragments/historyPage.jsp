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
      padding: 10px 24px;
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
  <div class="buttons" width="465" height="920">
    <div class="profile-area" width="150" height="175">
      <div class="profile-bar"></div>
      <img src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture" style="max-width: 91px; height: auto;">
      <a href="${pageContext.request.contextPath}/profile">
        <button class="profile-button">${username}</button>
      </a>

      <a href="${pageContext.request.contextPath}/main">
        <button class="home-button"><img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png" alt="" width="50" height="50"></button>
      </a>
      <div class="profile-bar"></div>
    </div>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" width="100" height="50" alt="" style="border-radius: 425px; z-index: 0; display: inline-block; margin-bottom: 45px; vertical-align: top;"/>
    <div class="dropdown">
      <a href="${pageContext.request.contextPath}/parkinglist">
        <button class="bookBtn">Book Reservation</button>
      </a>
    </div>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
    <div class="dropdown">
      <a href="${pageContext.request.contextPath}/history">
        <button class="favoriteBtn">View Favorite Parkings</button>
      </a>
    </div>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; vertical-align: top;"/>
    <div class="dropdown">
      <a href="${pageContext.request.contextPath}/parkingpals">
        <button class="palsBtn">Parking Pals</button>
      </a>
    </div>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
    <div class="dropdown">
      <a href="${pageContext.request.contextPath}/carpool">

        <button class="favoriteBtn">Carpools</button>
      </a>
    </div>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/referrals.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
    <div class="dropdown">
      <a href="${pageContext.request.contextPath}/referrals">

        <button class="favoriteBtn">My Referrals</button>
      </a>
    </div>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 15px; vertical-align: bottom;"/>
    <a href="${pageContext.request.contextPath}/settings">
      <button class="settings-button">Settings</button>
    </a>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; vertical-align: top;"/>
    <div class="dropdown">
      <button class="reportbtn">File a Report</button>
      </a>
      <div class="dropdown-content1">
        <a href="${pageContext.request.contextPath}/exp-report">Expiration Report</a>
        <a href="${pageContext.request.contextPath}/poorpark-report">Poor Parking Report</a>
      </div>
    </div>
  </div>
</div>

<div class = "box" >
  <body onload="generateLists()"></body>
  <div class="history-area">
    <h1>Parking History</h1>
    <div id="historyList" data-histories="${histories}" style="overflow-y: scroll; height: 900px; margin-left: 10px;">
    </div>
  </div>

  <div class="favorites-area">
    <h1>Favorite Parking Areas</h1>
    <div id="favoritesList" data-favorites="${favorites}" style="overflow-y: scroll; height: 900px; margin-left: 10px;">
    </div>
  </div>

  <div id="bookShortcut">
  </div>

</div>
</body>
</html>