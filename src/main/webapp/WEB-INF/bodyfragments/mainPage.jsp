<%--
  Created by IntelliJ IDEA.
  User: alecs
  Date: 3/2/2023
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">jo
    <title>Pete's Parking</title>
    <style>
        <%@include file="/WEB-INF/css/main-page.css"%>
        <%@include file="/WEB-INF/css/map-pan.css"%>
    </style>
    <link href="main-page.css" rel="stylesheet" type="text/css">
    <link href="map-pan.css" rel="stylesheet" type="text/css">

</head>

<body style="background-color: #373A36;">

<div class="sidebar">
    <div class="buttons" width="465" height="920">
        <div class="profile-area" width="150" height="175">
            <div class="profile-bar"></div>
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/profile-pic.png" width="75" height="75" alt="" style="border-radius: 425px; margin-top: 15px; margin-left: 15px;"/>
            <a href="${pageContext.request.contextPath}/profile">
                <button class="profile-button">Purdue Pete</button>
            </a>

            <button class="home-button"><img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png" alt="" width="50" height="50"></button>
            <div class="profile-bar"></div>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" width="100" height="50" alt="" style="border-radius: 425px; z-index: 0; display: inline-block; margin-bottom: 45px;"/>
        <div class="dropdown">
            <button class="dropbtn1">Book Reservation</button>
            <div class="dropdown-content1">
                <a href="#">Link 1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>
            </div>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px;"/>
        <div class="dropdown">
            <button class="dropbtn2">View Favorite Parkings</button>
            <div class="dropdown-content2">
                <a href="#">Link 4</a>
                <a href="#">Link 5</a>
                <a href="#">Link 6</a>
            </div>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px;"/>
        <div class="dropdown">
            <button class="dropbtn3">Parking Pals</button>
            <div class="dropdown-content2">
                <a href="#">Link 7</a>
                <a href="#">Link 8</a>
                <a href="#">Link 9</a>
            </div>
        </div>
        <!--
                    <img src="StarIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0;"/>
                    <div class="dropdown">
                        <button class="dropbtn4">Option 4</button>
                        <div class="dropdown-content2">
                            <a href="#">Link 10</a>
                            <a href="#">Link 11</a>
                            <a href="#">Link 12</a>
                        </div>
                    </div>
        -->
        <footer>
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-top: 510px;"/>
            <button class="settings-button">Settings</button>
        </footer>
    </div>
</div>
<div class="zoom_outer" width="1400" align="right" height="1230">
    <div id="zoom" width="1400" height="1230" align="right">
        <button class="corecbutton"></button>
        <button class="nwbutton"></button>
        <button class="univbutton"></button>
        <button class="rossbutton"></button>
        <button class="mccutchbutton"></button>
        <button class="grantbutton"></button>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/map.png" width="1400" height="1230" align="right" alt="zoom">
    </div>
</div>
<script>
    var scale = 1,
        panning = false,
        pointX = 0,
        pointY = 0,
        start = { x: 0, y: 0 },
        zoom = document.getElementById("zoom");
    function setTransform() {
        zoom.style.transform = "translate(" + pointX + "px, " + pointY + "px) scale(" + scale + ")";
    }
    zoom.onmousedown = function (e) {
        e.preventDefault();
        start = { x: e.clientX - pointX, y: e.clientY - pointY };
        panning = true;
    }
    zoom.onmouseup = function (e) {
        panning = false;
    }
    zoom.onmousemove = function (e) {
        e.preventDefault();
        if (!panning) {
            return;
        }
        pointX = (e.clientX - start.x);
        pointY = (e.clientY - start.y);
        setTransform();
    }
    zoom.onwheel = function (e) {
        e.preventDefault();
        var xs = (e.clientX - pointX) / scale,
            ys = (e.clientY - pointY) / scale,
            delta = (e.wheelDelta ? e.wheelDelta : -e.deltaY);
        if (scale >= 1 && scale < 3) {
            (delta > 0) ? (scale *= 1.2) : (scale /= 1.2);
        }
        if (scale > 3) {
            scale /= 1.2;
        }
        if (scale <= 1) {
            scale *= 1.2;
        }
        pointX = e.clientX - xs * scale;
        pointY = e.clientY - ys * scale;
        setTransform();
    }
</script>
</body>
</html>
