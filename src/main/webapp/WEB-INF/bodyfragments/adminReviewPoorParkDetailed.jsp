<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Review Poor Parking Report</title>
  <style>
    body {
      background-color: white;
      font-family: Arial, sans-serif;
    }

    h1 {
      color: goldenrod;
    }

    label {
      color: black;
      display: inline-block;
      width: 120px;
      text-align: right;
      margin-bottom: 10px;
    }

    input[type="text"], input[type="password"], select {
      width: 200px;
    }

    .container {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
  </style>
</head>
<body>
<div class="container">
  <form class="container" method="post" id="poorParkDismissForm" action="${pageContext.request.contextPath}/admin/deletePoorParkReport/${report.id}">
    <h1>Poor Parking Report</h1>
    <p name="reportDate">Report Date: ${report.reportDate}</p>
    <p name="reporterEmail">Reporter Email: ${report.reporterEmail}</p>
    <p name="parkingLot">Parking Lot: ${report.parkingLot}</p>
    <p name="licensePlate">License Plate: ${report.licensePlate}</p>
    <p name="description">Description: ${report.description}</p>
    <p name="imageURL">Image: ${report.imageURL}</p>
    <button class="dismiss" type="submit">Dismiss Report</button>
  </form>
</div>
</body>
</html>