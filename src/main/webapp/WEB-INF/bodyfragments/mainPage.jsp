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
    <meta charset="UTF-8">
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
    <div class="buttons">
        <div class="profile-area">
            <div class="profile-bar"></div>
            <img src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture" style="max-width: 91px; height: auto;">
            <a href="${pageContext.request.contextPath}/profile">
                <button class="profile-button">${user.firstName} ${user.lastName}</button>
            </a>

            <a href="${pageContext.request.contextPath}/main">
                <button class="home-button"><img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png" alt="" width="50" height="50"></button>
            </a>
            <div class="profile-bar"></div>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" width="100" height="50" alt="" style="border-radius: 425px; z-index: 0; margin-bottom: 45px; display: inline-block; vertical-align: text-bottom; position: relative;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/parkinglist">
                <button class="bookBtn">Book Reservation</button>
            </a>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; vertical-align: text-bottom; position: relative; margin-bottom: 30px;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/history">
                <button class="favoriteBtn">View Favorite Parkings</button>
            </a>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; display: inline-block; vertical-align: text-bottom; position: relative;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/parkingpals">
                <button class="palsBtn">Parking Pals</button>
            </a>
        </div>

        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 30px; display: inline-block; vertical-align: text-bottom; position: relative;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/carpool">
            <button class="favoriteBtn">Carpools</button>
            </a>
        </div>

        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/referrals.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/referrals">

                <button class="favoriteBtn">My Referrals</button>
            </a>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 15px; display: inline-block; position: relative;"/>
        <a href="${pageContext.request.contextPath}/settings">
            <button class="settings-button">Settings</button>
        </a>

        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; display: inline-block; vertical-align: text-bottom; position: relative;"/>
        <div class="dropdown">
            <button class="reportbtn">File a Report</button>
            </a>
            <div class="dropdown-content1">
                <a href="${pageContext.request.contextPath}/exp-report">Expiration Report</a>
                <a href="${pageContext.request.contextPath}/poorpark-report">Poor Parking Report</a>
            </div>
        </div>
    </div>
</div>


<div class="zoom_outer" width="1400" align="right" height="1220">
    <div id="zoom" width="1400" height="1220" align="right">
        <button class="corecbutton", data-occupancy="${corecOccupancy}", href="#", onclick="show('corec-popup')">CRL</button>
        <button class="nwbutton", data-occupancy="${northwesternOccupancy}", href="#", onclick="show('nw-popup')">NWG</button>
        <button class="univbutton", data-occupancy="${universityOccupancy}", href="#", onclick="show('univ-popup')">USG</button>
        <button class="rossbutton", data-occupancy="${rossadeOccupancy}", href="#", onclick="show('rossade-popup')">RAL</button>
        <button class="mccutchbutton", data-occupancy="${mccutcheonOccupancy}" ,href="#", onclick="show('mccutch-popup')">MCG</button>
        <button class="grantbutton", data-occupancy="${grantOccupancy}", href="#", onclick="show('grant-popup')">GSG</button>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/map.PNG" align="right" alt="zoom" style="height: 100%; width: 100%;">
    </div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="corec-popup" id="corec-popup" data-fav="${favorites}">
    <ol></ol><div class="inside">
    <div class="parkingLot">
        <div class="lotImage">
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/corec-lot.png" width="350" height="350" alt=""/>
        </div>
        <h2 style="text-align: center">COREC Parking Lot</h2>
        <p style="text-align: center"> France A. Cordova Recreational Sports Center
            355 N Martin Jischke Dr, West Lafayette, IN 47906</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">ABC Permits Required</p>
            </div>
            <div class="hours">
                <div class="hours_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Permits Required 8am-5pm</p>
            </div>
            <div class="spots">
                <div class="spots_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
                </div>
                <p style="padding-top: 10px">100 Total Spots</p>
            </div>
        </div>
    </div>

    <c:if test="${sessionScope.user.userRole == 'Student'}">

        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="reserve-button">Reserve a Spot Here</button>
        </a>
    </c:if>
    <body onload="checkFavs()"></body>
    <form method = "post" action="${pageContext.request.contextPath}/addFavorite" style="display: inline-block;">
        <input type="hidden" name="area" value="Corec Parking Lot">
        <button class="favBtn" onclick="favoriteUniv()" type="submit">Favorite this area!</button>
    </form>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" id="corecStar" alt="" width="50" height="50" style="display: inline-block; float: right; margin-right: 15px; margin-top: 35px; vertical-align: top;">
    <button class="close", href="#", onclick="hide('corec-popup')">Close</button>
</div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="mccutch-popup" id="mccutch-popup" data-fav="${favorites}">
    <ol></ol><div class="inside">
    <div class="parkingLot">
        <div class="lotImage">
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/mccutcheon-lot.png" width="350" height="350" alt=""/>
        </div>
        <h2 style="text-align: center">McCutcheon Dr. Parking Garage</h2>
        <p style="text-align: center"> 400 McCutcheon Dr West Lafayette IN 47906</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Residence Hall Permits Required</p>
            </div>
            <div class="hours">
                <div class="hours_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Permits Required 8am-5pm</p>
            </div>
            <div class="spots">
                <div class="spots_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
                </div>
                <p style="padding-top: 10px">500 Total Spots</p>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user.userRole == 'Student'}">

        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="reserve-button">Reserve a Spot Here</button>
        </a>
    </c:if>
    <form method = "post" action="${pageContext.request.contextPath}/addFavorite" style="display: inline-block;">
        <input type="hidden" name="area" value="McCutcheon Drive Parking Garage">
        <button class="favBtn" onclick="favoriteUniv()" type="submit">Favorite this area!</button>
    </form>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" id="mccutchStar" alt="" width="50" height="50" style="display: inline-block; float: right; margin-right: 15px; margin-top: 35px; vertical-align: top;">
    <button class="close", href="#", onclick="hide('mccutch-popup')">Close</button>
</div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="rossade-popup" id="rossade-popup" data-fav="${favorites}">
    <ol></ol><div class="inside">
    <div class="parkingLot">
        <div class="lotImage">
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/rossade-lot.png" width="350" height="350" alt=""/>
        </div>
        <h2 style="text-align: center">Ross-Ade Stadium Parking Lot</h2>
        <p style="text-align: center"> 850 Steven Beering Drive, West Lafayette, Indiana 47906</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">ABC Permits Required</p>
            </div>
            <div class="hours">
                <div class="hours_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Permits Required 8am-5pm</p>
            </div>
            <div class="spots">
                <div class="spots_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
                </div>
                <p style="padding-top: 10px">300 Total Spots</p>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user.userRole == 'Student'}">

        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="reserve-button">Reserve a Spot Here</button>
        </a>
    </c:if>
    <form method = "post" action="${pageContext.request.contextPath}/addFavorite" style="display: inline-block;">
        <input type="hidden" name="area" value="Ross-Ade Stadium Parking Lot">
        <button class="favBtn" onclick="favoriteUniv()" type="submit">Favorite this area!</button>
    </form>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" id="rossadeStar" alt="" width="50" height="50" style="display: inline-block; float: right; margin-right: 15px; margin-top: 35px; vertical-align: top;">
    <button class="close", href="#", onclick="hide('rossade-popup')">Close</button>
</div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="nw-popup" id="nw-popup" data-fav="${favorites}">
    <ol></ol><div class="inside">
    <div class="parkingLot">
        <div class="lotImage">
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/nw-lot.png" width="350" height="350" alt=""/>
        </div>
        <h2 style="text-align: center">Northwestern Ave. Parking Garage</h2>
        <p style="text-align: center"> 504 Northwestern Ave, West Lafayette, IN 47906</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">AB Permits Required</p>
            </div>
            <div class="hours">
                <div class="hours_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Permits Required 8am-5pm</p>
            </div>
            <div class="spots">
                <div class="spots_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
                </div>
                <p style="padding-top: 10px">750 Total Spots</p>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user.userRole == 'Student'}">

        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="reserve-button">Reserve a Spot Here</button>
        </a>
    </c:if>
    <form method = "post" action="${pageContext.request.contextPath}/addFavorite" style="display: inline-block;">
        <input type="hidden" name="area" value="Northwestern St. Parking Garage">
        <button class="favBtn" onclick="favoriteUniv()" type="submit">Favorite this area!</button>
    </form>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" id="nwStar" alt="" width="50" height="50" style="display: inline-block; float: right; margin-right: 15px; margin-top: 35px; vertical-align: top;">
    <button class="close", href="#", onclick="hide('nw-popup')">Close</button>
</div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="univ-popup" id="univ-popup" data-fav="${favorites}">
    <ol></ol><div class="inside">
    <div class="parkingLot">
        <div class="lotImage">
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/univ-lot.png" width="350" height="350" alt=""/>
        </div>
        <h2 style="text-align: center">University St. Parking Garage</h2>
        <p style="text-align: center"> 201 N University St, West Lafayette, IN 47906</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">AB Permits Required</p>
            </div>
            <div class="hours">
                <div class="hours_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Permits Required 8am-5pm</p>
            </div>
            <div class="spots">
                <div class="spots_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
                </div>
                <p style="padding-top: 10px">500 Total Spots</p>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user.userRole == 'Student'}">

        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="reserve-button">Reserve a Spot Here</button>
        </a>
    </c:if>
    <form method = "post" action="${pageContext.request.contextPath}/addFavorite" style="display: inline-block;">
        <input type="hidden" name="area" value="University St. Parking Garage">
        <button class="favBtn" onclick="favoriteUniv()" type="submit">Favorite this area!</button>
    </form>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" id="univStar" alt="" width="50" height="50" style="display: inline-block; float: right; margin-right: 15px; margin-top: 35px; vertical-align: top;">
    <button class="close", href="#", onclick="hide('univ-popup')">Close</button>
</div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<div class="grant-popup" id="grant-popup" data-fav="${favorites}">
    <ol></ol><div class="inside">
    <div class="parkingLot">
        <div class="lotImage">
            <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/grant-lot.png" width="350" height="350" alt=""/>
        </div>
        <h2 style="text-align: center">Grant St. Parking Garage</h2>
        <p style="text-align: center"> 120 Grant St, West Lafayette, IN 47906</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">AB Permits Required</p>
            </div>
            <div class="hours">
                <div class="hours_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/hours-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">Permits Required 8am-5pm</p>
            </div>
            <div class="spots">
                <div class="spots_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/car-image.png" height="40" alt=""/>
                </div>
                <p style="padding-top: 10px">500 Total Spots</p>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.user.userRole == 'Student'}">
        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="reserve-button">Reserve a Spot Here</button>
        </a>
    </c:if>
    <form method = "post" action="${pageContext.request.contextPath}/addFavorite" style="display: inline-block;">
        <input type="hidden" name="area" value="Grant St. Parking Garage">
        <button class="favBtn" onclick="favoriteUniv()" type="submit">Favorite this area!</button>
    </form>

    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" id="grantStar" alt="" width="50" height="50" style="display: inline-block; float: right; margin-right: 15px; margin-top: 35px; vertical-align: top;">
    <button class="close", href="#", onclick="hide('grant-popup')">Close</button>
</div>
</div>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////////////////--%>


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

    $ = function(id) {
        return document.getElementById(id);
    }

    var show = function(id) {
        $(id).style.display ='block';
    }
    var hide = function(id) {
        $(id).style.display ='none';
    }

    function checkFavs() {
        let favList = document.getElementById("corec-popup").getAttribute("data-fav");
        favList = favList.substring(1, favList.length-1);
        let favorites = favList;
        if (favList.includes(",")) {
            favorites = favList.split(", ");
        }

        if (favorites.includes("Corec Parking Lot")) {
            document.getElementById("corecStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        }
        if (favorites.includes("McCutcheon Drive Parking Garage")) {
            document.getElementById("mccutchStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        }
        if (favorites.includes("Ross-Ade Stadium Parking Lot")) {
            document.getElementById("rossadeStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        }
        if (favorites.includes("Northwestern St. Parking Garage")) {
            document.getElementById("nwStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        }
        if (favorites.includes("University St. Parking Garage")) {
            document.getElementById("univStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        }
        if (favorites.includes("Grant St. Parking Garage")) {
            document.getElementById("grantStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        }
    }
    function favoriteCorec() {
        if (document.getElementById("corecStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png") {
            document.getElementById("corecStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";

        } else if (document.getElementById("corecStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png") {
            document.getElementById("corecStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png";
        }
    }
    function favoriteMcCutch() {
        if (document.getElementById("mccutchStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png") {
            document.getElementById("mccutchStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        } else if (document.getElementById("mccutchStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png") {
            document.getElementById("mccutchStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png";
        }
    }
    function favoriteRossAde() {
        if (document.getElementById("rossadeStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png") {
            document.getElementById("rossadeStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        } else if (document.getElementById("rossadeStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png") {
            document.getElementById("rossadeStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png";
        }
    }
    function favoriteNW() {
        if (document.getElementById("nwStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png") {
            document.getElementById("nwStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        } else if (document.getElementById("nwStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png") {
            document.getElementById("nwStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png";
        }
    }
    function favoriteUniv() {
        if (document.getElementById("univStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png") {
            document.getElementById("univStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        } else if (document.getElementById("univStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png") {
            document.getElementById("univStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png";
        }
    }
    function favoriteGrant() {
        if (document.getElementById("grantStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png") {
            document.getElementById("grantStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png";
        } else if (document.getElementById("grantStar").src === "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/FilledStarIcon.png") {
            document.getElementById("grantStar").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png";
        }
    }
</script>



<script>
    <%--var corecOccupancy = ${corecOccupancy};--%>
    <%--var nwOccupancy = ${northwesternOccupancy};--%>
    <%--var rosseAdeOccupancy = ${rosseadeOccupancy};--%>
    <%--var mccutcheonOccupancy = ${mccutcheonOccupancy};--%>
    <%--var grantOccupancy = ${grantOccupancy};--%>
    <%--var universityOccupancy = ${universityOccupancy};--%>


    // function calculateColor(occupancy) {
    //     var offset = 10; // You can adjust this value to control the brightness of the colors
    //     var red = Math.round(occupancy * (255 - offset) + offset);
    //     var green = Math.round((1 - occupancy) * (255 - offset) + offset);
    //     var color = "#" + red.toString(16).padStart(2, "0") + green.toString(16).padStart(2, "0") + "00";
    //     return color;
    // }

    function calculateColor(occupancy) {
        var baseOffset = 55; // Controls the minimum brightness of the colors
        var colorRange = 160; // Controls the range of the colors

        var red = Math.round(occupancy * colorRange + baseOffset);
        var green = Math.round((1 - occupancy) * colorRange + baseOffset);

        var color = "#" + red.toString(16).padStart(2, "0") + green.toString(16).padStart(2, "0") + "00";
        return color;
    }

    function calculateHoverColor(color) {
        var darkenFactor = 0.8; // Adjust this value to control the darkness of the hover color
        var r = parseInt(color.substring(1, 3), 16);
        var g = parseInt(color.substring(3, 5), 16);

        var newR = Math.floor(r * darkenFactor).toString(16).padStart(2, "0");
        var newG = Math.floor(g * darkenFactor).toString(16).padStart(2, "0");

        return "#" + newR + newG + "00";
    }


    function applyColors() {
        document.querySelectorAll('button').forEach((button) => {
            const occupancy = parseFloat(button.dataset.occupancy);
            const color = calculateColor(occupancy);
            const hoverColor = calculateHoverColor(color);
            button.style.backgroundColor = color;
            button.addEventListener('mouseenter', () => {
                button.style.backgroundColor = hoverColor;
            });

            button.addEventListener('mouseleave', () => {
                button.style.backgroundColor = color;
            });        });
    }




    // Call the applyColors function when the DOM content is loaded
    document.addEventListener("DOMContentLoaded", applyColors);

</script>
</body>
</html>
  