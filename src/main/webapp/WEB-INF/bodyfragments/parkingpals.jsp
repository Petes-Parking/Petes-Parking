<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Parking Pals</title>
  <style>
    <%@include file="/WEB-INF/css/main-page.css"%>
    body {
      background-color: #333333;
      color: #fff;
      font-family: Verdana, Helvetica, sans-serif;
    }
    h1 {
      text-align: center;
      color: black;
    }

    h4 {
      color: black;
    }

    .container {
      margin-left: 1%;
      margin-top: 3%;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      display: inline-block;
    }

    input {
      padding: 8px;
      width: 95%;
      margin-bottom: 15px;
      border-width: thin;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
      align-self: auto
    }

    .add-friend-button {
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      padding: 7px 8px 5px;
      margin-bottom: -10px;
      cursor: pointer;
    }

    .add-friend-button:hover {
      border-radius: 15px;
      transition: .3s;
      background-color: #555;
    }

    .view-friend-button {
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      padding: 8px 10px 6px;
      cursor: pointer;
      margin-bottom: -10px;
    }

    .view-friend-button:hover {
      border-radius: 15px;
      transition: .3s;
      background-color: #555;
    }

    .x-button {
      width: 30px;
      height: 30px;
      background-color: #333;
      border-radius: 5px;
      margin-bottom: -10px;
    }

    .x-button:hover {
      border-radius: 10px;
      transition: .3s;
      background-color: #555;
    }

    .btn-success:hover {
      border-radius: 15px;
      transition: .3s;
    }

    .btn-danger:hover {
      border-radius: 15px;
      transition: .3s;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th {
      background-color: #A4A4A4;
      color: #000;
      text-align: left;
      padding: 8px;
      font-size: 1.1rem;
    }

    td {
      border-bottom: 1px solid #000;
      padding: 8px;
      font-size: 1rem;
      color: #000
    }

    tr:hover {
      background-color: #CEB888;
    }

    .pals-area {
      width: 75%;
      height: 89%;
      border-radius: 10px;
      float: right;
      background-color: #CEB888;
      margin-top: 1%;
      margin-right: 2%;
    }
  </style>
</head>
<body>
<div class="sidebar">
  <div class="profile-area">
    <img class="profile-pic" src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture">
    <a href="${pageContext.request.contextPath}/profile">
      <button id="profileBtn" class="profile-button">${user.firstName} ${user.lastName}</button>
    </a>
    <a href="${pageContext.request.contextPath}/main">
      <button id="homeBtn" class="home-button"><img id="homeIcon" style="width: 80%; height: 70%;" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png"></button>
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
<div class="pals-area">
  <div class="container" style="float: left; margin-left: 3%; width: 55%; height: auto">
    <h4>Add new Parking Pals:</h4>
    <input type="text" id="search" placeholder="Search for a name here...">
    <div style="overflow-y: auto; height: auto; max-height: 240px;">
      <table class="add-parking-pals-table">
        <thead style="position: sticky; position: -webkit-sticky; top: 0px;">
        <tr>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">Email</th>
          <th scope="col"></th> <!-- Column for the "Send friend request" button -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="li" varStatus="u">
          <c:if test="${li.email != user.email && li.userRole != 'Admin'}">
            <c:set var="isFriend" value="false" />
            <c:forEach items="${friends}" var="friend">
              <c:if test="${li.email == friend.senderEmail || li.email == friend.recipientEmail}">
                <c:set var="isFriend" value="true" />
              </c:if>
            </c:forEach>
            <c:if test="${not isFriend}">
              <tr>
                <td>${li.firstName}</td>
                <td>${li.lastName}</td>
                <td>${li.email}</td>
                <td>
                  <form method="post" action="${pageContext.request.contextPath}/friendInvite">
                    <input type="hidden" class="form-control" name="email" value="${li.email}"/>
                    <c:set var="isPending" value="false" />
                    <c:forEach items="${outgoingRequests}" var="out">
                      <c:if test="${li.email == out.recipientEmail}">
                        <c:set var="isPending" value="true" />
                      </c:if>
                    </c:forEach>
                    <c:if test="${not isPending}">
                      <button type="submit" class="add-friend-button" name="submit">
                        Send request
                      </button>
                    </c:if>
                    <c:if test="${isPending}">
                      <button type="button" class="add-friend-button" style="background-color: dimgray" name="pending" disabled>
                        Request pending
                      </button>
                    </c:if>
                  </form>
                </td>
              </tr>
            </c:if>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
  <div class="container" style="float: right; margin-right: 3%; width: 36%; text-align: center">
    <h4>Incoming Friend Requests:</h4>
    <form method="post" action="${pageContext.request.contextPath}/friendInviteResponse">
      <label class="form-label">Select Friend Request</label>
      <select id="request-select" name="email" class="form-select">
        <c:forEach items="${requests}" var="request">
          <option name="option" value="${request.senderEmail}">${request.senderFirstName} ${request.senderLastName}</option>
        </c:forEach>
      </select><br>
      <button id="accept-btn" class="btn btn-success" type="submit" name="action" value="accept" style="margin-right: 10px">Accept</button>
      <button id="reject-btn" class="btn btn-danger" type="submit" name="action" value="reject">Reject</button>
    </form>
  </div>
  <div class="container" style="float: left; margin-left: 3%; width: 55%; height: auto">
    <h4>My current Parking Pals:</h4>
    <div style="overflow-y: auto; height: auto; max-height: 200px">
      <table>
        <thead style="position: sticky; position: -webkit-sticky; top: 0px;">
        <tr>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">Email</th>
          <th scope="col"></th> <!-- Column for the "Send friend request" button -->
          <th scope="col"></th> <!-- Column for the "X" button -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friends}" var="friend">
          <c:if test="${friend.senderEmail != user.email}">
            <tr>
              <td>${friend.senderFirstName}</td>
              <td>${friend.senderLastName}</td>
              <td>${friend.senderEmail}</td>
              <td>
                <form method="post" action="${pageContext.request.contextPath}/friendpage">
                  <button type="submit" class="view-friend-button" name="email" value="${friend.senderEmail}">
                    View profile
                  </button>
                </form>
              </td>
              <td>
                <form method="post" action="${pageContext.request.contextPath}/removeFriend">
                  <button type="submit" class="x-button" name="email" value="${friend.senderEmail}">
                    <img style="width: 100%; height: 50%; -webkit-filter: invert(1); filter: invert(1);" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/x-icon.png">
                  </button>
                  <input type="hidden" class="form-control" name="requestSentBy" value="friend"/>
                </form>
              </td>
            </tr>
          </c:if>
          <c:if test="${friend.senderEmail == user.email}">
            <tr>
              <td>${friend.recipientFirstName}</td>
              <td>${friend.recipientLastName}</td>
              <td>${friend.recipientEmail}</td>
              <td>
                <form method="post" action="${pageContext.request.contextPath}/friendpage">
                  <button type="submit" class="view-friend-button" name="email" value="${friend.recipientEmail}">
                    View profile
                  </button>
                </form>
              </td>
              <td>
                <form method="post" action="${pageContext.request.contextPath}/removeFriend">
                  <button type="submit" class="x-button" name="email" value="${friend.recipientEmail}">
                    <img style="width: 100%; height: 50%; -webkit-filter: invert(1); filter: invert(1);" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/x-icon.png">
                  </button>
                  <input type="hidden" class="form-control" name="requestSentBy" value="user"/>
                </form>
              </td>
            </tr>
          </c:if>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
<script>
  const searchInput = document.getElementById('search');
  const tableRows = document.querySelectorAll('table.add-parking-pals-table > tbody tr');

  const addButton = document.querySelectorAll('.add-friend-button');
  const requestSelect = document.getElementById('request-select');
  const acceptBtn = document.getElementById('accept-btn');
  const rejectBtn = document.getElementById('reject-btn');
  const xBtn = document.querySelectorAll('.x-button');

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
  const palArea = document.querySelector('.pals-area');

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
      palArea.style.backgroundColor = "#565656";
    }
  }

  searchInput.addEventListener('input', () => {
    const searchValue = searchInput.value.toLowerCase();
    tableRows.forEach(row => {
      const first = row.querySelector('td:first-child').textContent.toLowerCase();
      const last = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
      const full = first + " " + last;
      if (full.includes(searchValue)) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    });
  });

  addButton.forEach(button => {
    button.addEventListener('click', () => {
      alert('Friend request sent!');
    });
  });

  xBtn.forEach(button => {
    button.addEventListener('click', () => {
      const response = confirm("Are you sure you want to remove friend?");
      if (response) {
        alert('Friend removed.');
      } else {
        alert("Action cancelled.");
      }
    });
  });

  acceptBtn.addEventListener('click', function(event) {
    if (requestSelect.value === '') {
      event.preventDefault();
      alert('You have no friend requests to accept or reject!');
    } else {
      alert('Friend added!');
      window.location.replace("/${pageContext.request.contextPath}/parkingpals");
    }
  });

  rejectBtn.addEventListener('click', function(event) {
    if (requestSelect.value === '') {
      event.preventDefault();
      alert('You have no friend requests to accept or reject!');
    } else {
      alert('Friend request removed.');
      window.location.replace("/PeteParkingMgt/parkingpals");
    }
  });

</script>
</body>
</html>
