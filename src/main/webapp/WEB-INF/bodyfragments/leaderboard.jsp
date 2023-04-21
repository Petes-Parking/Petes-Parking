<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Leaderboard</title>
  <!-- Add Bootstrap CSS -->
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #CEB888;
      color: black;
    }
    .btn {
      background-color: #333;
      color: white;
    }
    .container {
      background-color: white;
      border-radius: 10px;
      padding: 10px;
      margin-top: 3%;

    }
    .back-btn {
      position: absolute;
      top: 10px;
      left: 10px;
    }
    th {
      background-color: #333;
      color: white;
    }
  </style>
</head>
<body>

<div class="container">
  <h1 class="text-center" style="margin-top: 1%; margin-bottom: 1%">Leaderboard</h1>
  <button class="btn btn-secondary back-btn" onclick="window.location.href='${pageContext.request.contextPath}/main'">&larr; Back</button>
  <hr/>
  <c:set var="ENTRIES_PER_PAGE" value="10" />
  <c:set var="currentPage" value="${param.page ne null ? param.page : 1}" />
  <c:set var="startIndex" value="${(currentPage - 1) * ENTRIES_PER_PAGE}" />
  <c:set var="endIndex" value="${fn:length(leaderboardList)}" />
  <c:set var="globalCounter" value="${startIndex + 1}"/>
  <c:if test="${startIndex + ENTRIES_PER_PAGE > fn:length(leaderboardList)}">
    <c:set var="endIndex" value="${fn:length(leaderboardList)}" />
  </c:if>

  <c:if test="${startIndex + ENTRIES_PER_PAGE <= fn:length(leaderboardList)}">
    <c:set var="endIndex" value="${startIndex + ENTRIES_PER_PAGE}" />
  </c:if>


  <table class="table table-striped" style="margin-bottom: 3%; width: 95%; margin-left: 2.5%">
    <thead>
    <tr>
      <th>Rank</th>
      <th>User Name</th>
      <th>Points</th>
      <th>Level</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through the users and display the list -->
    <c:forEach items="${leaderboardList}" var="user" varStatus="status" begin="${startIndex}" end="${endIndex - 1}">
      <tr>
        <td>${status.index + 1 + startIndex}</td>
        <td>${user.firstName} ${user.lastName}</td>
        <td>${user.points}</td>
        <td>${user.level}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

  <div class="d-flex justify-content-between">
    <button class="btn btn-primary" style="width: 100px;" onclick="navigate('${currentPage - 1}')" <c:if test="${currentPage == 1}">disabled</c:if>>
      <i class="fas fa-arrow-left"></i> Back
    </button>
    <button class="btn btn-primary" style="width: 100px;" onclick="navigate('${currentPage + 1}')" <c:if test="${endIndex == fn:length(leaderboardList)}">disabled</c:if>>
      Next <i class="fas fa-arrow-right"></i>
    </button>

  </div>
</div>

<script>
  function navigate(page) {
    window.location.href = `${pageContext.request.contextPath}/leaderboard?page=`+page;

  }

</script>

<script>

  const body = document.querySelector('body');
  const container = document.querySelector('.container');
  const table = document.querySelector('table');
  const header = document.querySelector('h1');

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

    } else if (mode === "dark") {
      body.style.backgroundColor = "#333";
      container.style.backgroundColor = "#565656";
      table.style.backgroundColor = "white";
      header.style.color = "white";
    }
  }
</script>
</body>
</html>
