
<!doctype html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Pete's Parking</title>
    <link rel="stylesheet" type="text/css" href="/css/login-box.css">

    <style type="text/css">
        body {
            background-image: url(../../resources/image/purduebackground.jpeg);
        }
    </style>
</head>


<body>
<div class="login">
    <div class="logo">
        <img src="../../resources/image/Petes-Parking-logo.png" width="200" height="200" alt=""/>
    </div>
    <div class="fields">
        <form action="/action_page.php">

            <input class="text-field" type="text" placeholder="Email" onClick="this.select();" id="fname" name="fname"><br><br>
            <input class="text-field" type="password" placeholder="Password" onClick="this.select();" id="lname" name="lname"><br><br>
            <input class = "login-button" type="" value="Login">
        </form>
        <p id="forgot">Forgot Password?</p>
        <p float="left"> Don't have an account?</p>


    </div>
    <input class = "create-button" type="button" value="Create Account">


</div>

</body>
</html>
