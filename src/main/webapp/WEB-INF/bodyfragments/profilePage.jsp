<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Profile Page</title>
    <script>

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
    <link href="main-page.css" rel="stylesheet" type="text/css">
    <link href="../css/profile-page.css" rel="stylesheet" type="text/css">
    <style>
        <%@include file="/WEB-INF/css/main-page.css"%>
        <%@include file="/WEB-INF/css/profile-page.css"%>
    </style>

</head>

<body style="background-color: #373A36;">

<div class="sidebar">
    <div class="buttons" width="465" height="920">
        <div class="profile-area" width="150" height="175">
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
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/BookIcon.png" width="100" height="50" alt="" style="border-radius: 425px; z-index: 0; display: inline-block; margin-bottom: 45px; vertical-align: top;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/parkinglist">
                <button class="bookBtn">Book Reservation</button>
            </a>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/StarIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/history">
                <button class="favoriteBtn">View Favorite Parkings</button>
            </a>
        </div>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/PalsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; vertical-align: top;"/>
        <div class="dropdown">
            <a href="${pageContext.request.contextPath}/parkingpals">
                <button class="palsBtn">Parking Pals</button>
            </a>
        </div>

        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/carpool.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; display: inline-block; margin-bottom: 30px; vertical-align: top;"/>
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
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 15px; vertical-align: bottom;"/>
        <a href="${pageContext.request.contextPath}/settings">
            <button class="settings-button">Settings</button>
        </a>
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/flagIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 35px; vertical-align: top;"/>
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

<div class = "box" >
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
        <div class="profile_pic">
            <img src="data:image/jpeg;base64,${profilePic}" alt="Profile Picture" style="max-width: 210px; height: auto;">
            <img class="trash" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/trash.png" width="30px" height="30px" alt=""  id="trash-image" onclick="deleteImage()"/>
            <p1>${user.firstName} ${user.lastName}</p1>
            <a href="${pageContext.request.contextPath}/logout">
                <button1 class="logout-button" type="button"> Log Out</button1>
            </a><br>
            <button1 class = "edit2" id="edit-button" onclick="show('edit-popup')">Edit Profile Pic</button1>
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
    <div class="user_info">
        <form action="/action_page.php">
            <div class="userdiv">
                <p2>Username: ${user.firstName} ${user.lastName}</p2><br>
                <input class="text-field" type="hidden" placeholder="${user.password}" onClick="this.select();" id="pass" name="pass"><br><br>

            </div>
            <div class="passdiv">
                <p3>Password: ${user.password}</p3><br>
            </div>
            <div class="passdiv">
                <p4>Email: ${user.email}</p4><br>
            </div>
        </form>
        <div class="passdiv">
            <p4>Points: ${user.points}</p4><br>
        </div>

        <div class="passdiv">
            <p4 style="font-size: larger">Level: ${user.level}</p4><br>
            <p4>Points Needed To Level Up: ${nextLevelPoints}</p4>
        </div>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/addVehicle">
    <div class="car_1">
            <p5>Car 1 Information</p5><br><br>
            <div class="make1div">
                <p6>Make: ${vehicle.make1}</p6><br>
                <input class="text-field" type="make1" placeholder="New make" onClick="this.select();" id="make1" name="make1">
            </div>
            <div class="model1div">
                <p7>Model: ${vehicle.model1}</p7><br>
                <input class="text-field" type="model1" placeholder="New Model" onClick="this.select();" id="model1" name="model1"><br><br>
            </div>
            <div class="color1div">
                <p8>Color: ${vehicle.color1}</p8><br>
                <input class="text-field" type="color1" placeholder="New Color" onClick="this.select();" id="color1" name="color1">
            </div>
            <div class="lic1div">
                <p9>License Plate Number: ${vehicle.license1}</p9><br>
                <input class="text-field" type="lic1" placeholder="New License Plate Number" onClick="this.select();" id="license1" name="license1"><br><br>
            </div>
    </div>
    <div class="car_2">
            <p10>Car 2 Information</p10><br><br>
            <div class="make2div">
                <p11>Make: ${vehicle.make2}</p11><br>
                <input class="text-field" type="make2" placeholder="New Make" onClick="this.select();" id="make2" name="make2">
            </div>
            <div class="model2div">
                <p12>Model: ${vehicle.model2}</p12><br>
                <input class="text-field" type="model2" placeholder="New Model" onClick="this.select();" id="model2" name="model2">
            </div>
            <br>
            <div class="color2div">
                <p13>Color: ${vehicle.color2}</p13><br>
                <input class="text-field" type="color2" placeholder="New Color" onClick="this.select();" id="color2" name="color2">
            </div>
            <div class="lic2div">
                <p14>License Plate Number: ${vehicle.license2}</p14><br>
                <input class="text-field" type="lic2" placeholder="New License Plate Number" onClick="this.select();" id="license2" name="license2"><br><br>
            </div>
    </div>

    <a href="${pageContext.request.contextPath}/addVehicle">
        <button class="save-button" type="submit" color="white"> Save Changes </button>
    </a>
    </form>


    <button1 class="delete-button" type="button" onclick="showPopup1()">
    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Delete Account</a></button1>

</div>


</body>
</html>