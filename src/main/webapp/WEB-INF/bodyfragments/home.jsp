<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pete's Parking</title>
    <style type="text/css">
        <%@include file="/WEB-INF/css/login-box.css"%>

        body {
            background-image: url(https://photos.smugmug.com/photos/i-Z5PSS6Q/0/L/i-Z5PSS6Q-L.jpg);
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>

<%--    <style><%@include file="/WEB-INF/view/style/style.css"%></style>--%>
    <link href="/resources/css/login-box.css" rel="stylesheet" type="text/css">
</head>


<body>
<div class="login">

    <div class="logo">
        <img src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/Petes-Parking-logo.png" width="200" height="200" alt=""/>
        <input class = "help-button" type="button" value="Help ">
    </div>
    <div class="fields">
        <%@include file="businessMessage.jsp" %>

        <form method="post" action="${pageContext.request.contextPath}/auth" >

            <input class="text-field" type="email" placeholder="Email" id="fname" name="fname"><br><br>
            <input class="text-field" type="password" placeholder="Password"  id="lname" name="lname"><br><br>
            <input class = "login-button" type="button" value="Login">
        </form>
        <a href="${pageContext.request.contextPath}/forgotpassword">
            <p id="forgot" href="/forgotpassword">Forgot Password?</p>
        </a>
        <p float="left"> Don't have an account?</p>


    </div>
    <a href="${pageContext.request.contextPath}/signup">
        <input  class = "create-button" type="button" value="Create Account" href="/signup">
    </a>


</div>

</body>
</html>
