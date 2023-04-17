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
      background-color: #f8f9fa;
    }

    .container {
      max-width: 800px;
      margin: auto;
    }

    h1, h2 {
      color: goldenrod;
      text-align: center;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
      margin-bottom: 40px;
    }

    th {
      background-color: goldenrod;
      color: black;
    }

    .code-btn, .generate-btn {
      background-color: goldenrod;
      border: none;
      color: black;
      padding: 8px 16px;
      text-align: center;
      text-decoration: none;
      font-size: 14px;
      cursor: pointer;
      margin: 5px;
    }

    .code-btn:hover, .generate-btn:hover {
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

    .back-btn {
      margin-top: 20px;
    }
  </style>
</head>
<body>
<div class="container">
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
  <p id="usedReferralCode" class="text-center">Student who referred you: ${creator} </p>
  <p class="text-center">Points gained: 20</p>
  </c:when>
  <c:otherwise>
  <p id="usedReferralCode" class="text-center">You have not used any referral code.</p>
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
    <c:if test="${not empty referralCodes}">
      <c:if test="${not empty studentsUsed}">
        <c:forEach items="${studentsUsed}" var="names">
          <tr>
            <td>
              <button class="code-btn" onclick="copyCodeToClipboard(this)" data-code="${referralCodes.code}">
                  ${referralCodes.code}
              </button>
            </td>
            <c:if test="${referralCodes.uses > 0}">
              <td>${names} - <span style="color: #45a049">+20 points</span></td>
            </c:if>
            <c:if test="${referralCodes.uses == 0}">
              <td>None</td>
            </c:if>
          </tr>
        </c:forEach>
      </c:if>
      <c:if test="${empty studentsUsed}">
        <tr>
          <td>
            <button class="code-btn" onclick="copyCodeToClipboard(this)" data-code="${referralCodes.code}">
                ${referralCodes.code}
            </button>
          </td>
          <td>None</td>
        </tr>
      </c:if>
    </c:if>
    </tbody>
  </table>

  <h2>Generate a Referral Code</h2>
  <p class="text-center">If you don't have a referral code, click the button below to generate one:</p>
  <div class="text-center">
    <a href="${pageContext.request.contextPath}/generateReferralCode">
      <button class="generate-btn">Generate Code</button>
    </a>
  </div>
  <p id="generatedCode" class="text-center"></p>

  <div class="text-center">
    <a href="${pageContext.request.contextPath}/main">
      <button class="btn btn-secondary back-btn">Back</button>
    </a>
  </div>

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
</div>
</body>
</html>
