<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Purdue Parking Referral</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      color: black;
    }

    h1 {
      color: goldenrod;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th {
      background-color: goldenrod;
      color: black;
    }

    td, th {
      border: 1px solid black;
      padding: 8px;
      text-align: left;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    .generate-btn {
      background-color: goldenrod;
      border: none;
      color: black;
      padding: 10px 20px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 10px 2px;
      cursor: pointer;
    }
  </style>
</head>
<body>
<h1>Pete's Parking Referrals</h1>
<h2>Your Referral Codes</h2>
<table>
  <thead>
  <tr>
    <th>Referral Code</th>
    <th>Used By</th>
  </tr>
  </thead>
  <tbody>
  <!-- Replace with dynamic data -->
  <c:forEach items="${referralCodes}" var="code">
    <tr>
      <td>${code.code}</td>
<%--      <td>${code.usedBy}</td>--%>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h2>Generate a Referral Code</h2>
<p>If you don't have a referral code, click the button below to generate one:</p>
<button class="generate-btn" onclick="generateCode()">Generate Code</button>
<p id="generatedCode"></p>

<script>
  function generateCode() {
    // Replace with your server-side code to generate a referral code
    var referralCode = "PU-PARK-12345";
    document.getElementById("generatedCode").innerHTML = "Your new referral code: " + referralCode;
  }
</script>
</body>
</html>
