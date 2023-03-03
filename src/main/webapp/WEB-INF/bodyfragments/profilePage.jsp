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
    <link href="../css/profile-page.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        p1 { /*john purdue*/
            font-size: 45px;
            font-family: Calibri;
        }
        p2 { /*Username*/
            margin-top: 50px;
            font-size: 20px;
            font-family: Calibri;
        }
        p3 { /*Password*/
            font-size: 20px;
            font-family: Calibri;
        }
        p4 { /*Email*/
            font-size: 20px;
            font-family: Calibri;
        }
        p5 { /*Car 1*/
            font-size: 20px;
            font-family: Calibri;
            font-weight: bold;
        }
        p6 { /*Make 1*/
            font-size: 20px;
            font-family: Calibri;
        }
        p7 { /*Model 1*/
            font-size: 20px;
            font-family: Calibri;
        }
        p8 { /*Color 1*/
            font-size: 20px;
            font-family: Calibri;
        }
        p9 { /*License 1*/
            font-size: 20px;
            font-family: Calibri;
        }
        p10 { /*Car 2*/
            font-size: 20px;
            font-family: Calibri;
            font-weight: bold;
        }
        p11 { /*Make 2*/
            font-size: 20px;
            font-family: Calibri;
        }
        p12 { /*Model 2*/
            font-size: 20px;
            font-family: Calibri;
        }
        p13 { /*Color 2*/
            font-size: 20px;
            font-family: Calibri;
        }
        p14 { /*Lic 2*/
            font-size: 20px;
            font-family: Calibri;
        }
        body {
            background: #CEB888;
        }

        button{
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }

        button:hover{
            background-color: #555;
        }
        .trash{
            margin-top: 200px;
        }
        #trash-image:hover{
            background-color: #555;
        }
        .edit{
            margin-top: -30px;
        }
        .logout-button{
            float: right;
        }
        .Save-button{
            float: right;
        }

        .box {
            width: 50%;
            margin-left: 50px;
            margin: auto;
            margin-top: 50px;
            padding: 30px;
            background-color: #EFECEC;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

    </style>
</head>

<body>

<div class = "box" >
    <div class="profile_pic">
        <input type="file" id="image-input" style="display: none;" onchange="handleImageSelect(event)">
        <img class = "picture" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/nowTransparent.png" id="clickable-image" width="210" height="210" alt=""/>
        <img class="trash" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/trash.png" width="30px" height="30px" alt=""  id="trash-image" onclick="deleteImage()"/>
        <p1>John Purdue</p1>
        <a href="${pageContext.request.contextPath}/logout">
            <button class="logout-button" type="button"> Log Out</button>
        </a>


    </div>
    <div class="user_info">
        <button class = "edit" type="button" onclick="changeImage()">Edit</button>
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
        <button class="Save-button" type="button"> Save Changes</button>
    </a>

    <button class="delete-button" type="button" onclick="showPopup1()">Delete Account</button>

</div>


</body>
</html>