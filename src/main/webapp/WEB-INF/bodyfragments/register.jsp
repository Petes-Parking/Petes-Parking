<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 2/27/23
  Time: 11:17 PM


  Just a warning, I have used inline CSS for the entire document. Linking it with <link> proves
  to be ineffective, as it essentially only links with the link reference in layout.jsp
  When figured out, I will refactor this, but for now, this is what it has to be.
  I do not think there are any practical issues from doing this besides for readability, but
  in terms of technical correctness, it is all there. - Max
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <title>register-page</title>
  <link href="../../resources/css/register-box.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
  <%@include file="/WEB-INF/css/register-box.css"%>
  body {
    background-image: url(https://photos.smugmug.com/photos/i-Z5PSS6Q/0/L/i-Z5PSS6Q-L.jpg);
    background-size: cover;
    background-repeat: no-repeat;


  }
</style>

<body>
<div id = "register-box">

  <img id = "logo" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/Petes-Parking-logo.png"  alt=""/>
  <div id ="register-input-box">
    <h2>Register</h2>
    <form action="/action_page.php">
      <label for="email">Enter valid email</label> <br>
      <input class="text-field" id = "email" type="email" placeholder="Email" onClick="this.select();" id="fname" name="fname"><br><br>
      <input class="text-field" type="password" placeholder="Enter Password" onClick="this.select();"  name="lname"><br><br>
      <input class="text-field" type="password" placeholder="Enter Password Again" onClick="this.select();"  name="lname"><br><br>
      <label for="start">Enter Birthday</label>
      <br>
      <input type="date" id="start" name="trip-start"> <br> <br>
      <input id = "register-button" type="submit" value="Register">
      <input  id = "go-back-button" type="submit" value="Back to Login">



    </form>
  </div>

</div>

</body>
</html>
