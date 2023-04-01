<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 4/1/23
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Splash Screen</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f5f5f5;
        }
        .splash-container {
            text-align: center;
        }
        h1 {
            font-size: 48px;
            margin-bottom: 20px;
        }
        .loader {
            border: 5px solid #f3f3f3;
            border-top: 5px solid #3498db;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            animation: spin 2s linear infinite;
        }
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body>
<div class="splash-container">
    <h1>Welcome to Pete's Parking</h1>
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/Petes-Parking-logo.png" width="200" height="200" alt=""/>
    <div class="loader" style="margin-left: 290px"></div>
</div>

<script>
    setTimeout(function() {
        window.location.href = "home"; // Redirect to the home page after a set time.
    }, 3000); // 3000 milliseconds (3 seconds)
</script>
</body>
</html>

