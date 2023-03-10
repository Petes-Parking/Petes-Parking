<!doctype html>
<html>
<head>
    <title>Profile Page</title>
    <script>

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

<body>

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
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/SettingsIcon.png" width="75" height="75" alt="" style="border-radius: 425px; margin-left: 2.4%; z-index: 0; margin-bottom: 15px; vertical-align: bottom;"/>
        <a href="${pageContext.request.contextPath}/settings">
            <button class="settings-button">Settings</button>
        </a>
    </div>
</div>

<div class = "box" >
    <div class="profile_pic">
        <input type="file" id="image-input" style="display: none;" onchange="handleImageSelect(event)">
        <img class = "picture" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/nowTransparent.png" id="clickable-image" width="210" height="210" alt=""/>
        <img class="trash" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/trash.png" width="30px" height="30px" alt=""  id="trash-image" onclick="deleteImage()"/>
        <p1>John Purdue</p1>
        <a href="${pageContext.request.contextPath}/logout">
            <button1 class="logout-button" type="button"> Log Out</button1>
        </a>


    </div>
    <div class="user_info">
        <button1 class = "edit" type="button" onclick="changeImage()">Edit</button1>
        <form action="/action_page.php">
            <div class="userdiv">
                <p2>Username:</p2><br>
                <input class="text-field" type="username" placeholder="Username" onClick="this.select();" id="user" name="user">
            </div>
            <div class="passdiv">
                <p3>Password:</p3><br>
                <input class="text-field" type="password" placeholder="Password" onClick="this.select();" id="pass" name="pass"><br><br>
            </div>
            <div class="passdiv">
                <p4>Email:</p4><br>
                <input class="text-field" type="email" placeholder="Email" onClick="this.select();" id="email" name="email"><br><br>
            </div>
        </form>
    </div>
    <div class="car_1">
        <form action="/action_page.php">
            <p5>Car 1 Information</p5><br><br>
            <div class="make1div">
                <p6>Make:</p6><br>
                <input class="text-field" type="make1" placeholder="Make" onClick="this.select();" id="make1" name="make1">
            </div>
            <div class="model1div">
                <p7>Model:</p7><br>
                <input class="text-field" type="model1" placeholder="Model" onClick="this.select();" id="model1" name="model1"><br><br>
            </div>
            <div class="color1div">
                <p8>Color:</p8><br>
                <input class="text-field" type="color1" placeholder="Color" onClick="this.select();" id="color1" name="color1">
            </div>
            <div class="lic1div">
                <p9>License Plate Number:</p9><br>
                <input class="text-field" type="lic1" placeholder="License Plate Number" onClick="this.select();" id="lic1" name="lic1"><br><br>
            </div>
        </form>
    </div>
    <div class="car_2">
        <form action="/action_page.php">
            <p10>Car 2 Information</p10><br><br>
            <div class="make2div">
                <p11>Make:</p11><br>
                <input class="text-field" type="make2" placeholder="Make" onClick="this.select();" id="make2" name="make2">
            </div>
            <div class="model2div">
                <p12>Model:</p12><br>
                <input class="text-field" type="model2" placeholder="Model" onClick="this.select();" id="model2" name="model2">
            </div>
            <br>
            <div class="color2div">
                <p13>Color:</p13><br>
                <input class="text-field" type="color2" placeholder="Color" onClick="this.select();" id="color2" name="color2">
            </div>
            <div class="lic2div">
                <p14>License Plate Number:</p14><br>
                <input class="text-field" type="lic2" placeholder="License Plate Number" onClick="this.select();" id="lic2" name="lic2"><br><br>
            </div>
        </form>
    </div>

    <a href="${pageContext.request.contextPath}/main">
        <button1 class="save-button" type="button"> Save Changes</button1>
    </a>

    <button1 class="delete-button" type="button" onclick="showPopup1()">
    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Delete Account</a></button1>

</div>


</body>
</html>