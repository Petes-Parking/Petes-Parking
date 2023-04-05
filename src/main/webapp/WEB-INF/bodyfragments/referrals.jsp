<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Purdue Parking Referral</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">

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

    .code-btn {
      background-color: goldenrod;
      border: none;
      color: black;
      padding: 8px 16px;
      text-align: center;
      text-decoration: none;
      font-size: 14px;
      cursor: pointer;
    }

    .code-btn:hover{
      background-color: darkgoldenrod;
    }

    .generate-btn:hover{
      background-color: darkgoldenrod;
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
<c:if test="${not empty message}">
  <div class="alert alert-success" role="alert">
      ${message}
  </div>
</c:if>

<c:if test="${not empty error}">
  <div class="alert alert-danger">
    <ul>
      <c:forEach items="${error}" var="error">
        <li style="font-size: 20px; color: red">${error}</li>
      </c:forEach>
    </ul>
  </div>
</c:if>
<h1>Pete's Parking Referrals</h1>
<h2>Your Used Referral Code</h2>
<c:choose>
  <c:when test="${not empty creator}">
    <p id="usedReferralCode">Student who referred you: ${creator} </p>
    <p>Points gained: 20</p>
  </c:when>
  <c:otherwise>
    <p id="usedReferralCode">You have not used any referral code.</p>
  </c:otherwise>
</c:choose>

<h2>Your Referral Codes</h2>
<table>
  <thead>
  <tr>
    <th>Referral Code <span style="font-size: smaller;font-weight: lighter; font-style: italic;">(Click to copy)</span></th>
    <th>Used By</th>
  </tr>
  </thead>
  <tbody>
  <!-- Replace with dynamic data -->
  <c:forEach items="${referralCodes}" var="code">
    <tr>

      <td>
        <button class="code-btn" onclick="copyCodeToClipboard(this)" data-code="${code.code}">
          ${code.code}
      </button></td>
<%--      <td>${code.usedBy}</td>--%>
      <c:if test="${code.uses == 0}">
        <td>None</td>
      </c:if>
    </tr>
  </c:forEach>
  </tbody>
</table>

<h2>Generate a Referral Code</h2>
<p>If you don't have a referral code, click the button below to generate one:</p>
<a href="${pageContext.request.contextPath}/generateReferralCode">
<button class="generate-btn">Generate Code</button>
</a>
<p id="generatedCode"></p>

<a href="${pageContext.request.contextPath}/main">
  <button class="btn btn-secondary back-btn">Back</button>
</a>

<script>


  function copyCodeToClipboard(element) {
    var code = element.getAttribute('data-code');
    var textarea = document.createElement('textarea');
    textarea.value = code;
    document.body.appendChild(textarea);
    textarea.select();
    document.execCommand('copy');
    document.body.removeChild(textarea);

    alert('Referral code copied: ' + code);
  }

</script>
</body>
</html>
