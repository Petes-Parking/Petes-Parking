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
        svg {
            width: 100px;
            height: 100px;
        }
        #circle1, #circle2, #circle3 {
            transform-origin: center;
            transform-box: fill-box;
            animation: circleAnimation 2s infinite;
        }
        #circle1 {
            animation-delay: 0s;
        }
        #circle2 {
            animation-delay: 0.3s;
        }
        #circle3 {
            animation-delay: 0.6s;
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

        @keyframes circleAnimation {
            0% {
                transform: scale(0.5);
                opacity: 0.3;
            }
            50% {
                transform: scale(1);
                opacity: 1;
            }
            100% {
                transform: scale(0.5);
                opacity: 0.3;
            }
        }
    </style>
</head>
<body>
<div class="splash-container">
    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/4b67a199ff3d8a5cecb315b72bdcf245aedcf515/src/main/webapp/resources/image/Petes-Parking-logo.svg" width="400" height="400" alt=""/>

    <h1>Welcome to Pete's Parking!</h1>
    <svg width="100" height="100" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <div class="loader" style="margin-left: 300px"></div>

<%--        <circle cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="red">--%>


<%--        </circle>--%>
    </svg>
    <object type="image/svg+xml" data="../../resources/image/Petes-Parking-logo.svg" width="400" height="400"></object>

</div>

<script>
    setTimeout(function() {
        window.location.href = "home"; // Redirect to the home page after a set time.
    }, 3000); // 3000 milliseconds (3 seconds)
</script>
</body>
</html>
