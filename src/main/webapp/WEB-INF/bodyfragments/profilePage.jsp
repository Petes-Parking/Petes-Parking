<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Profile Page</title>
    <style>
        <%@include file="/WEB-INF/css/main-page.css"%>
        <%@include file="/WEB-INF/css/profile-page.css"%>
    </style>
</head>
<body>
<div class="sidebar">
    <div class="profile-area">
        <img class="profile-pic" src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture">
        <a href="${pageContext.request.contextPath}/profile">
            <button class="profile-button">${user.firstName} ${user.lastName}</button>
        </a>
        <a href="${pageContext.request.contextPath}/main">
            <button class="home-button"><img style="width: 80%; height: 70%;" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/HomeIcon.png"></button>
        </a>
    </div>

    <div class="button-area">
        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" style="width: 32%; margin-left: 2%">
        <a href="${pageContext.request.contextPath}/parkinglist">
            <button class="sidebarPageButtons">Book Reservation</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" style="width: 24%; margin-left: 6%"/>
        <a href="${pageContext.request.contextPath}/history">
            <button class="sidebarPageButtons">View Favorite Parkings</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" style="width: 23%; margin-left: 6%"/>
        <a href="${pageContext.request.contextPath}/parkingpals">
            <button class="sidebarPageButtons">Parking Pals</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" style="width: 22%; margin-left: 7%"/>
        <a href="${pageContext.request.contextPath}/carpool">
            <button class="sidebarPageButtons">Carpools</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/referrals.png" style="width: 23%; margin-left: 6%"/>
        <a href="${pageContext.request.contextPath}/referrals">
            <button class="sidebarPageButtons">My Referrals</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" style="width: 23%; margin-left: 7%"/>
        <a href="${pageContext.request.contextPath}/settings">
            <button class="sidebarPageButtons">Settings</button>
        </a>

        <img class="sidebarPageIcons" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" style="width: 26%; margin-left: 6%; margin-right: 4%"/>
        <div class="dropdown">
            <button style="width: 100%; position: relative; padding: 13px 30px; margin-top: 6%; background-color: #D6C49B; border-radius: 5px;">File a Report</button>
            <div class="dropdown-content1">
                <a href="${pageContext.request.contextPath}/exp-report">Expiration Report</a>
                <a href="${pageContext.request.contextPath}/poorpark-report">Poor Parking Report</a>
            </div>
        </div>
    </div>
</div>

<div class="box">
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach items="${error}" var="error">
                    <li style="font-size: 20px; color: red">${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach items="${success}" var="error">
                    <li style="font-size: 20px; color: limegreen">${success}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/addProfilePic" enctype="multipart/form-data" style="display: inline;">
        <div class="profile-header">
            <img class="profile-page-pic" src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture">
            <img class="trash" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/trash.png" id="trash-image" onclick="deleteImage()"/>
            <h1 class="name-header">${user.firstName} ${user.lastName}</h1>
            <a href="${pageContext.request.contextPath}/logout">
                <button1 class="logout-button" type="button"> Log Out</button1>
            </a><br>
            <button class="edit-picture-button" id="edit-button" onclick="show('edit-popup')">Edit Profile Pic</button>
        </div>

        <div class="edit-popup" id="edit-popup">
            <label for="imageData">Select file for profile picture:</label>
            <input class="image" type="file" id="imageData" name="imageData" accept="image/*">
            <br>
            <button type="file" id="image-input" style="display: none;" onchange="handleImageSelect(event)"></button>
            <br>
            <button type="submit" class="save-button" id="save-button" onclick="hide('edit-popup')">Save Profile Pic</button>
            <button1 type="button" class="close-button" id="close-button" onclick="hide('edit-popup')">Close</button1>
        </div>
    </form>

    <div class="user-info">
        <p>Username: ${user.firstName} ${user.lastName}</p>
        <p>Password: ${user.password}</p>
        <p>Email: ${user.email}</p>

        <p>Points: ${user.points}</p>
        <p>Level: ${user.level}</p>
        <p>Points Needed To Level Up: ${nextLevelPoints}</p>
    </div>

    <form method="post" action="${pageContext.request.contextPath}/addVehicle">
        <div class="car-1">
            <h5>Car 1 Information</h5>
            <p>Make: ${vehicle.make1}</p>
            <input class="text-field" type="make1" placeholder="Enter make" onClick="this.select();" id="make1" name="make1">
            <p>Model: ${vehicle.model1}</p>
            <input class="text-field" type="model1" placeholder="Enter model" onClick="this.select();" id="model1" name="model1">
            <p>Color: ${vehicle.color1}</p>
            <input class="text-field" type="color1" placeholder="Enter color" onClick="this.select();" id="color1" name="color1">
            <p>License Plate Number: ${vehicle.license1}</p>
            <input class="text-field" type="lic1" placeholder="Enter license number" onClick="this.select();" id="license1" name="license1">
        </div>

        <div class="car-2">
            <h5>Car 2 Information</h5>
            <p>Make: ${vehicle.make2}</p>
            <input class="text-field" type="make2" placeholder="Enter make" onClick="this.select();" id="make2" name="make2">
            <p>Model: ${vehicle.model2}</p>
            <input class="text-field" type="model2" placeholder="Enter model" onClick="this.select();" id="model2" name="model2">
            <p>Color: ${vehicle.color2}</p>
            <input class="text-field" type="color2" placeholder="Enter color" onClick="this.select();" id="color2" name="color2">
            <p>License Plate Number: ${vehicle.license2}</p>
            <input class="text-field" type="lic2" placeholder="Enter license number" onClick="this.select();" id="license2" name="license2">
        </div>
        <a href="${pageContext.request.contextPath}/addVehicle">
            <button class="save-button" type="submit">Save Changes</button>
        </a>
        <button class="delete-button" type="button" onclick="showPopup1()">
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Delete Account</a>
        </button>
        <body onload="checkPermits()"></body>
        <h4 style="margin-left: 3%; margin-top: 3%;">Select the permits you own:</h4>
        <input type="checkbox" id="permitA" name="permitA" data-permits="${vehicle.permitA}" style="margin-left: 10%;">
        <label for="permitA" style="display: inline-block; margin-left: 1%;"> A Permit</label><br>
        <input type="checkbox" id="permitB" name="permitB" data-permits="${vehicle.permitB}" style="margin-left: 10%;">
        <label for="permitB" style="display: inline-block; margin-left: 1%;"> B Permit</label><br>
        <input type="checkbox" id="permitC" name="permitC" data-permits="${vehicle.permitC}" style="margin-left: 10%;">
        <label for="permitC" style="display: inline-block; margin-left: 1%;"> C Permit</label><br>
        <input type="checkbox" id="permitD" name="permitD" data-permits="${vehicle.permitD}" style="margin-left: 10%; display: inline-block;">
        <label for="permitD" style="display: inline-block; margin-left: 1%;"> Disability Permit</label><br>
    </form>
</div>

<script>
    function checkPermits() {
        let permA = document.getElementById("permitA").getAttribute("data-permits");
        let permB = document.getElementById("permitB").getAttribute("data-permits");
        let permC = document.getElementById("permitC").getAttribute("data-permits");
        let permD = document.getElementById("permitD").getAttribute("data-permits");
            if (permA.includes("on")) {
                document.getElementById("permitA").checked = true;
            } if (permB.includes("on")) {
                document.getElementById("permitB").checked = true;
            } if (permC.includes("on")) {
                document.getElementById("permitC").checked = true;
            } if (permD.includes("on")) {
                document.getElementById("permitD").checked = true;
            }
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

    function showPopup1() {
        confirm("Are you sure you want to delete your account?");
    }
    function changeImage() {
        var input = document.getElementById("image-input");
        input.click();
    }
    function handleImageSelect(e) {
        var reader = new FileReader();
        reader.onload = function(event) {
            document.getElementById("clickable-image").src = event.target.result;
        }
        reader.readAsDataURL(e.target.files[0]);
    }
    function deleteImage() {
        var answer = confirm("Do you want to delete your profile picture?");
        if (answer) {
            document.getElementById("clickable-image").src = "https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/nowTransparent.png";
        }
    }
</script>
</body>
</html>
