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

            <a href="${pageContext.request.contextPath}/main">
                <button class="home-button"><img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png" alt="" width="50" height="50"></button>
            </a>
            <div class="profile-bar"></div>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" width="100" height="50" alt="" style="border-radius: 425px; z-index: 0; display: inline-block; margin-bottom: 45px; vertical-align: top;"/>
        <div class="dropdown">
            <button class="bookBtn">Book Reservation</button>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
        <div class="dropdown">
            <button class="favoriteBtn">View Favorite Parkings</button>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; vertical-align: top;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/parkingpals">
                <button class="palsBtn">Parking Pals</button>
            </a>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; vertical-align: top;"/>
        <div class="dropdown">
                <button class="reportbtn">File a Report</button>
            </a>
            <div class="dropdown-content1">
                <a href="#">Link 7</a>
                <a href="#">Link 8</a>
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
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 15px; vertical-align: bottom;"/>
        <a href="${pageContext.request.contextPath}/settings">
            <button class="settings-button">Settings</button>
        </a>
    </div>
</div>
<div class="zoom_outer" width="1400" align="right" height="1230">
    <div id="zoom" width="1400" height="1230" align="right">
        <button class="corecbutton", href="#", onclick="show('corec-popup')">CRL</button>
        <button class="nwbutton", href="#", onclick="show('nw-popup')">NWG</button>
        <button class="univbutton", href="#", onclick="show('univ-popup')">USG</button>
        <button class="rossbutton", href="#", onclick="show('rossade-popup')">RAL</button>
        <button class="mccutchbutton", href="#", onclick="show('mccutch-popup')">MCG</button>
        <button class="grantbutton", href="#", onclick="show('grant-popup')">GSG</button>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/map.PNG" width="1400" height="1230" align="right" alt="zoom">
    </div>
</div>

<div class="corec-popup" id="corec-popup">
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

        <button class="reserve-button">
            <a class="reserve-button"
               href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a>
        </button>
    </c:if>
    <button class="close", href="#", onclick="hide('corec-popup')">Close</button>
</div>
</div>

<div class="mccutch-popup" id="mccutch-popup">
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

        <button class="reserve-button">
            <a class="reserve-button"
               href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a>
        </button>
    </c:if>
    <button class="close", href="#", onclick="hide('mccutch-popup')">Close</button>
</div>
</div>

<div class="rossade-popup" id="rossade-popup">
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

        <button class="reserve-button">
            <a class="reserve-button"
               href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a>
        </button>
    </c:if>
    <button class="close", href="#", onclick="hide('rossade-popup')">Close</button>
</div>
</div>

<div class="nw-popup" id="nw-popup">
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

        <button class="reserve-button">
            <a class="reserve-button"
               href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a>
        </button>
    </c:if>
    <button class="close", href="#", onclick="hide('nw-popup')">Close</button>
</div>
</div>

<div class="univ-popup" id="univ-popup">
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

        <button class="reserve-button">
            <a class="reserve-button"
               href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a>
        </button>
    </c:if>
    <button class="close", href="#", onclick="hide('univ-popup')">Close</button>
</div>
</div>

<div class="grant-popup" id="grant-popup">
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

        <button class="reserve-button">
            <a class="reserve-button"
               href="${pageContext.request.contextPath}/parkinglist">Reserve a Spot Here</a>
        </button>
    </c:if>
    <button class="close", href="#", onclick="hide('grant-popup')">Close</button>
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

    $ = function(id) {
        return document.getElementById(id);
    }

    var show = function(id) {
        $(id).style.display ='block';
    }
    var hide = function(id) {
        $(id).style.display ='none';
    }

</script>
</body>
</html>
  