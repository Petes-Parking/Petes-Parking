<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Parking Pals</title>
  <style>
    body {
      background-color: #CEB888;
      color: #fff;
      font-family: Arial, Helvetica, sans-serif;
    }
    h1 {
      text-align: center;
      color: black;
    }

    h3 {
      color: black;
    }

    .container {
      margin: auto;
      margin-top: 20px;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      display: inline-block;
    }

    input {
      padding: 10px;
      width: 95%;
      margin-bottom: 20px;
      border-width: thin;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
      align-self: auto
    }

    .add-friend-button {
      background-color: #CEB888;
      color: #fff;
      border: none;
      border-radius: 5px;
      padding: 5px 10px;
      cursor: pointer;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th {
      background-color: #A4A4A4;
      color: #000;
      text-align: left;
      padding: 10px;
      font-size: 1.1rem;
    }

    td {
      border-bottom: 1px solid #000;
      padding: 10px;
      font-size: 1rem;
      color: #000
    }

    tr:hover {
      background-color: #CEB888;
    }
  </style>
</head>
<body>
<h1 style="text-align: center; margin-top: 50px">Parking Pals</h1>
<div class="container" style="float: left; margin-left: 20px; width: 55%">
  <h3>Add new Parking Pals:</h3>
  <input type="text" id="search" placeholder="Search for a name here...">
  <table class="add-parking-pals-table">
    <thead>
    <tr>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>
      <th scope="col"></th> <!-- Column for the "Add friend" button -->
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
                <input type="hidden" class="form-control" name="email" value="${li.email}" />
                <button type="submit" class="add-friend-button" name="submit">
                  Send friend request
                </button>
              </form>
            </td>
          </tr>
        </c:if>
      </c:if>
    </c:forEach>
    </tbody>
  </table>
</div>
<div class="container" style="float: right; margin-right: 20px; width: 40%; text-align: center">
  <h3>Incoming Friend Requests:</h3>
  <form method="post" action="${pageContext.request.contextPath}/friendInviteResponse">
    <label class="form-label">Select Friend Request</label>
    <select name="email" class="form-select">
      <c:forEach items="${requests}" var="request">
          <option name="option" value="${request.senderEmail}">${request.senderEmail}</option>
      </c:forEach>
    </select><br>
    <button class="btn btn-success" type="submit" name="action" value="accept">Accept</button>
    <button class="btn btn-danger" type="submit" name="action" value="reject">Reject</button>
  </form>
</div>
<div class="container" style="float: left; margin-left: 20px; width: 55%">
  <h3>My current Parking Pals:</h3>
  <table>
    <thead>
    <tr>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${friends}" var="friend">
      <c:if test="${friend.senderEmail != user.email}">
      <tr>
        <td>${friend.senderFirstName}</td>
        <td>${friend.senderLastName}</td>
        <td>${friend.senderEmail}</td>
      </tr>
      </c:if>
      <c:if test="${friend.senderEmail == user.email}">
      <tr>
        <td>${friend.recipientFirstName}</td>
        <td>${friend.recipientLastName}</td>
        <td>${friend.recipientEmail}</td>
      </tr>
      </c:if>
    </c:forEach>
    </tbody>
  </table>
</div>
<script>
  const searchInput = document.getElementById('search');
  const tableRows = document.querySelectorAll('table.add-parking-pals-table > tbody tr');

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
</script>
</body>
</html>
