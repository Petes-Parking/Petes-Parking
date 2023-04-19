<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Review Poor Parking Report</title>
  <style>
    body {
      background-color: #F9F9F9;
    }

    h1 {
      color: #333;
      font-size: 36px;
      text-align: center;
    }

    label {
      color: #555;
      font-size: 16px;
      display: inline-block;
      width: 120px;
      text-align: right;
      margin-bottom: 10px;
    }

    .container {
      display: flex;
      flex-direction: column;
      align-items: center;
      background-color: goldenrod;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      text-align: center;
      padding: 30px;
      width: 60%;
      margin: 50px auto;
    }

    p {
      font-size: 20px;
      margin: 10px 0;
      color: #333;
      font-weight: bold;
    }

    button {
      background-color: #F9A602;
      border: none;
      color: white;
      padding: 10px 30px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 10px;
      cursor: pointer;
      border-radius: 5px;
      transition: all 0.3s ease;
    }

    button:hover {
      background-color: #F5CDA7;
      color: #333;
    }

    button[type="submit"] {
      background-color: #333333;
    }

    button[type="submit"]:hover {
      background-color: #A9A9A9;
    }

    @media screen and (max-width: 768px) {
      .container {
        width: 90%;
      }
    }

    .text {
      align-content: center;
    }

    label {
      color: #555555;
    }
  </style>
</head>
<body>
<h1>Poor Parking Report</h1>
<div class="container">
  <form class="container2" method="post" id="poorParkDismissForm" action="${pageContext.request.contextPath}/admin/deletePoorParkReport/${report.id}">
   <input type="hidden" id="reportedEmail" value="${report.reporterEmail}">
    <p><label>Report Date:</label> ${report.reportDate}</p>
    <p><label>Reporter Email:</label> ${report.reporterEmail}</p>
    <p><label>Parking Lot:</label> ${report.parkingLot}</p>
    <p><label>License Plate:</label> ${report.licensePlate}</p>
    <p><label>Description:</label> ${report.description}</p>
    <img src="data:image/jpeg;base64,${reportImage}" alt="Report Image" style="max-width: 500px; height: auto; padding-bottom: 15px;"><br>
    <button type="submit">Dismiss Report</button>
  </form>
<<<<<<< HEAD
      <form action="${pageContext.request.contextPath}/bookinglist" method="get">
    <button type="submit">Fine/Ticket</button>
  </form>
=======
    <form action="${pageContext.request.contextPath}/admin/awardPoints" method="post">
        <input type="hidden" name="reportedEmail" value="${report.reporterEmail}">

        <button type="submit" style="float: left;">Award Points</button>
    </form>
    <form action="${pageContext.request.contextPath}/admin/deductPoints" method="post">
        <input type="hidden" name="reportedEmail" value="${report.reporterEmail}">
        <button type="submit" >Deduct Points</button>
    </form>
>>>>>>> 3dda81a9c1d86c771bd5d7246e29f533e029edeb
  <form action="${pageContext.request.contextPath}/admin/backToPoorParkReport" method="post">
    <button type="submit">Back</button>
  </form>
    <button class="btn btn-warning" id="remove-priv">Remove reporting privileges</button>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
  // Add click event listeners for buttons
  $('#remove-priv').on('click', function() {
    console.log("here! clicked remove button")
    let selectedEmail = $('#reportedEmail').val();

    window.location.href = `${pageContext.request.contextPath}/admin/removeReportPriv/` + selectedEmail;
  });
</script>
</body>
</html>