<!doctype html>
<html>
<head>
    <title>Profile Page</title>
    <script>
        function showPopup() {
            var answer = confirm("Are you sure you want to delete your profile picture?");
            if (answer) {
                alert("You clicked Yes!");
            } else {
                alert("You clicked No.");
            }
        }
    </script>
    <link href="../css/profile-page.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        p1 { /*john purdue*/
            font-size: 40px;
            font-family: Calibri;
        }
        p2 { /*Username*/
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
        #deletePic:hover {
            transition: .3s;
            background-color: #B89954;
            opacity: 0.5;

        }
        .trash{
            margin-left: 10px;
            margin-top: 100px;
        }
        .box1{
            margin-left: 50px;
        }
    </style>
</head>

<body>
<div class = "box1" >
    <div class="profile_pic">
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/nowTransparent.png" width="150" height="150" alt=""/>
        <img class = "trash"  src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/trash.png" width="15px" height="15px" alt=""  id="deletePic" onclick="showPopup()"/>
        <p1>John Purdue</p1>
    </div>
    <div class="user_info">

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
    <button class="delete-button">Delete Account</button>
    <button class="save-button">Save Changes</button>
</div>
</body>
</html>