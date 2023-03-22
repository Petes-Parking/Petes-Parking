<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      background-color: white;
      color: goldenrod;
    }
    .btn {
      background-color: goldenrod;
      color: black;
    }
    .table-striped tbody tr:nth-of-type(odd) {
      background-color: goldenrod;
    }
    .back-btn {
      position: absolute;
      top: 10px;
      left: 10px;
    }
  </style>
</head>
<body>

<div class="container">
  <h1 class="text-center">Leaderboard</h1>
  <button class="btn btn-secondary back-btn" onclick="window.location.href='/main'">&larr; Back</button>
  <hr/>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Rank</th>
      <th>User Name</th>
      <th>Points</th>
    </tr>
    </thead>
    <tbody>
    <!-- Loop through the users and display the list -->
    <c:forEach items="${leaderboardList}" var="user" varStatus="status">
      <tr>
        <td>${status.index + 1 + offset}</td>
        <td>${user.firstName} ${user.lastName}</td>
        <td>${user.points}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <div class="d-flex justify-content-between">
    <button class="btn btn-primary" onclick="navigate('${page - 1}')">
      <i class="fas fa-arrow-left"></i> Back
    </button>
    <button class="btn btn-primary" onclick="navigate('${page + 1}')">
      Next <i class="fas fa-arrow-right"></i>
    </button>
  </div>
</div>

<!-- Add Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Add Font Awesome for arrow icons -->
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

<script>
  function navigate(page) {
    window.location.href = `/leaderboard?page=${page}`;
  }
</script>
</body>
</html>
