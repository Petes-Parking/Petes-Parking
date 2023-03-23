<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Expiration Report</title>
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

        .form-group {
            margin-bottom: 15px;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .submit-button {
            background-color: goldenrod;
            color: black;
            border: none;
            cursor: pointer;
            padding: 10px 20px;
            margin-left: 120px;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Expiration Report</h1>
    <p>Report Date: ${report.reportDate}</p>
    <p>Reporter Email: ${report.reporterEmail}</p>
    <p>Parking Lot: ${report.parkingLot}</p>
    <p>License Plate: ${report.licensePlate}</p>
    <p>Description: ${report.description}</p>
    <p>Image: ${report.imageURL}</p>
</div>
</body>
</html>