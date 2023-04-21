<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pete's Parking</title>
    <style>
        <%@include file="/WEB-INF/css/map-pan.css"%>
        <%@include file="/WEB-INF/css/main-page.css"%>
    </style>
</head>
<body>
<div class="sidebar">
    <div class="profile-area">
        <img class="profile-pic" src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture">
        <a href="${pageContext.request.contextPath}/profile">
            <button id="profileBtn" class="profile-button">${user.firstName} ${user.lastName}</button>
        </a>
        <a href="${pageContext.request.contextPath}/main">
            <button id="homeBtn" class="home-button"><img id="homeIcon" style="width: 62%; height: 65%;" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png"></button>
        </a>
    </div>

    <div class="button-area">
        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" style="width: 32%; margin-left: 2%">
        <a href="${pageContext.request.contextPath}/parkinglist">
            <button id="bookBtn" class="sidebarPageButtons">Book Reservation</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" style="width: 24%; margin-left: 6%"/>
        <a href="${pageContext.request.contextPath}/history">
            <button id="starBtn" class="sidebarPageButtons">View Favorite Parkings</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" style="width: 23%; margin-left: 6%"/>
        <a href="${pageContext.request.contextPath}/parkingpals">
            <button id="palBtn" class="sidebarPageButtons">Parking Pals</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" style="width: 22%; margin-left: 7%"/>
        <a href="${pageContext.request.contextPath}/carpool">
            <button id="carBtn" class="sidebarPageButtons">Carpools</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/referrals.png" style="width: 23%; margin-left: 6%"/>
        <a href="${pageContext.request.contextPath}/referrals">
            <button id="refBtn" class="sidebarPageButtons">My Referrals</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" style="width: 23%; margin-left: 7%"/>
        <a href="${pageContext.request.contextPath}/settings">
            <button id="settingsBtn" class="sidebarPageButtons">Settings</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" style="width: 26%; margin-left: 6%; margin-right: 4%"/>
        <div class="dropdown">
            <button id="reportBtn" style="width: 100%; position: relative; padding: 13px 30px; margin-top: 6%; background-color: #D6C49B; border-radius: 5px;">File a Report</button>
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
        <h2 style="text-align: center">${corecName}</h2>
        <p style="text-align: center"> ${corecAddress}</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">A, B, and C Permits Required</p>
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
        <h2 style="text-align: center">${mccutcheonName}</h2>
        <p style="text-align: center"> ${mccutcheonAddress}</p>
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
        <h2 style="text-align: center">${rossadeName}</h2>
        <p style="text-align: center"> ${rossadeAddress}</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">A, B, and C Permits Required</p>
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
        <h2 style="text-align: center">${northwesternName}</h2>
        <p style="text-align: center"> ${northwesternAddress}</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">A and C Garage Permits Required</p>
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
        <h2 style="text-align: center">${universityName}</h2>
        <p style="text-align: center"> ${universityAddress}</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">A and C Garage Permits Required</p>
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
        <h2 style="text-align: center">${grantName}</h2>
        <p style="text-align: center"> ${grantAddress}</p>
        <div class="details">
            <div class="permits">
                <div class="permit_pic">
                    <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/permit-image.png" height="50" alt=""/>
                </div>
                <p style="padding-top: 15px">No Permits Required</p>
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
    const sidebar = document.querySelector('.sidebar');
    const profileBtn = document.getElementById('profileBtn');
    const homeBtn = document.getElementById('homeBtn');
    const homeIcon = document.getElementById('homeIcon');
    const bookBtn = document.getElementById('bookBtn');
    const starBtn = document.getElementById('starBtn');
    const palBtn = document.getElementById('palBtn');
    const carBtn = document.getElementById('carBtn');
    const refBtn = document.getElementById('refBtn');
    const settingsBtn = document.getElementById('settingsBtn');
    const reportBtn = document.getElementById('reportBtn');

    // Check if themePreference is already set in localStorage
    if (!localStorage.getItem('themePreference')) {
        // Set default value if themePreference is not set
        localStorage.setItem('themePreference', "light");
    }

    // Get the user's theme preference
    const savedMode = localStorage.getItem('themePreference');

    // Set the radio button based on the current mode
    if (savedMode === "light") {
        updateTheme("light");
    } else if (savedMode === "dark") {
        updateTheme("dark");
    }

    function updateTheme(mode) {
        if (mode === "light") {
            sidebar.style.backgroundColor = "#CEB888";
        } else if (mode === "dark") {
            sidebar.style.backgroundColor = "#565656";
            profileBtn.style.backgroundColor = "#333";
            homeBtn.style.backgroundColor = "#333";
            bookBtn.style.backgroundColor = "#333";
            starBtn.style.backgroundColor = "#333";
            palBtn.style.backgroundColor = "#333";
            carBtn.style.backgroundColor = "#333";
            refBtn.style.backgroundColor = "#333";
            settingsBtn.style.backgroundColor = "#333";
            reportBtn.style.backgroundColor = "#333";
            profileBtn.style.color = "#fff";
            homeIcon.style.filter = "invert(1)";
            bookBtn.style.color = "#fff";
            starBtn.style.color = "#fff";
            palBtn.style.color = "#fff";
            carBtn.style.color = "#fff";
            refBtn.style.color = "#fff";
            settingsBtn.style.color = "#fff";
            reportBtn.style.color = "#fff";
        }
    }

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
            console.log("Test:", occupancy);

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
  