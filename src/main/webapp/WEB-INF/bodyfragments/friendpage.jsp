<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Friend Profile</title>
    <style>
        body {
            background-color: #CEB888;
            color: #000;
            font-family: Arial, Helvetica, sans-serif;
        }

        h1 {
            text-align: center;
            color: black;
            margin-top: 0;
            padding-top: 40px;
        }

        h2 {
            color: black;
            margin-top: 20px;
            text-align: center;
        }

        .container {
            margin: auto;
            width: 50%;
            margin-top: 40px;
            padding: 50px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        .info {
            font-size: 1.2rem;
            margin: 15px 0;
        }

        .btn {
            display: block;
            width: 30%;
            padding: 10px;
            text-align: center;
            background-color: #CEB888;
            color: #fff;
            font-family: Arial, Helvetica, sans-serif;
            text-decoration: none;
            border-radius: 5px;
            margin: 20px auto;
        }

        .btn:hover {
            background-color: #fff;
            color: #CEB888;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>${myFriend.firstName} ${myFriend.lastName}'s Profile</h1>
<div class="container">
    <h2>Personal Information</h2>
    <div class="info">
        <strong>Name:</strong> ${myFriend.firstName} ${myFriend.lastName}
    </div>
    <div class="info">
        <strong>Email:</strong> ${myFriend.email}
    </div>
    <div class="info">
        <strong>Phone Number:</strong> ${myFriend.phoneNumber}
    </div>
    <div class="info">
        <strong>Birthday:</strong> ${myFriend.dob}
    </div>
    <div class="info">
        <strong>Gender:</strong> ${myFriend.gender}
    </div>
    <div class="info">
        <strong>Points:</strong> ${myFriend.points}
    </div>
    <div class="info">
        <strong>Level:</strong> ${myFriend.level}
    </div>
    <a href="/PeteParkingMgt/parkingpals" class="btn">Return to Parking Pals</a>
</div>
</body>
</html>