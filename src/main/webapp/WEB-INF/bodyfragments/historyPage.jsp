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

<div id="box" class="box">
  <body onload="generateLists()"></body>
  <div class="history-area">
    <h1 id="hist" style="margin-left: 3%">Parking History</h1>
    <div id="historyList" data-histories="${histories}" style="overflow-y: scroll; height: 80%; margin-left: 1%;">
    </div>
  </div>

  <div class="favorites-area">
    <h1 id="fav" style="margin-left: -5%">Favorite Parking Areas</h1>
    <div id="favoritesList" data-favorites="${favorites}" style="overflow-y: scroll; height: 80%; margin-left: 0%;">
    </div>
  </div>

  <div id="bookShortcut">
  </div>
</div>
<script>
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
  const reportBtn = document.getElementById('reportBtn');
  const box = document.getElementById('box');
  const hist = document.getElementById('hist');
  const fav = document.getElementById('fav');

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
      box.style.backgroundColor = "#565656";
      hist.style.color = "white";
      fav.style.color = "white";
    }
  }
</script>

</body>
</html>