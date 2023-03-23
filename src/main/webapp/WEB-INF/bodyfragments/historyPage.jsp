<!doctype html>
<html>
<head>
  <title>History Page</title>
  <script>
    function generateLists() {
      let historyList = document.getElementById("historyList").getAttribute("data-histories");
      console.log("The stuff: ", historyList)

      historyList = historyList.substring(1, historyList.length-1);
      console.log("New stuff: ", historyList);
      let histList = historyList.split(", ");
      for (let i = 0; i < histList.length; i++) {
        let button = document.createElement("button");
        let buttonName = document.createTextNode(histList.at(i));
        button.appendChild(buttonName);
        button.className = "historyButton";
        document.getElementById("historyList").appendChild(button);
      }
      favoriteList = request.getParameter("favorites");
        let favList = favoriteList;
        for (let i = 0; i < favList.length; i++) {
          let button = document.createElement("button");
          let buttonName = document.createTextNode(favList.at(i));
          button.appendChild(buttonName);
          button.className = "favoritesButton";
          document.getElementById("favoritesList").appendChild(button);
        }
      }


    function showPopup1() {
      confirm("Are you sure you want to delete your account?");
    }
    function changeImage() {
      var input = document.getElementById("image-input");
      input.click();
    }
    function handleImageSelect(e) {
      var reader = new FileReader();
      reader.onload = function(event) {
        document.getElementById("clickable-image").src = event.target.result;
      }
      reader.readAsDataURL(e.target.files[0]);
    }
    function deleteImage() {
      var answer = confirm("Do you want to delete your profile picture?");
      if (answer) {
        document.getElementById("clickable-image").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/nowTransparent.png";
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
      <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/profile-pic.png" width="75" height="75" alt="" style="border-radius: 425px; margin-top: 15px; margin-left: 15px;"/>
      <a href="${pageContext.request.contextPath}/profile">
        <button class="profile-button">Purdue Pete</button>
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
  <div class="history-area">
    <h1>Parking History</h1>
    <div id="historyList" data-histories="${histories}" style="overflow-y: scroll; height: 900px; margin-left: 10px;">
      <body onload="generateLists()"></body>
    </div>
  </div>
  <div class="favorites-area">
    <h1>Favorite Parking Areas</h1>
    <div id="favoritesList" style="overflow-y: scroll; height: 900px; margin-left: 10px;">
    </div>
  </div>
</div>

</body>
</html>