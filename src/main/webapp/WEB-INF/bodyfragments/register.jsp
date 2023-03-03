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
<%@page import="java.text.DateFormat"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@page import="java.util.Date"%>




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

    <c:if test="${not empty error}">
    <div class="alert alert-danger">
        <ul>
            <c:forEach items="${error}" var="error">
                <li style="font-size: 15px; color: red">${error}</li>
            </c:forEach>
        </ul>
    </div>
    </c:if>
  <img id = "logo" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/Petes-Parking-logo.png"  alt=""/>
  <div id ="register-input-box">
    <h2>Register</h2>
    <form method = "post" action="${pageContext.request.contextPath}/addUser">
        <s:bind path="email">

        <label for="email">Enter valid email</label> <br>
        <sf:input class="text-field" id = "email" type="email" placeholder="Email"  name="email" path="email"/><br><br>

        <label for="pword">Enter new password</label> <br>
        </s:bind>
        <s:bind path="password">
        <sf:input class="text-field" id="pword" type="password" placeholder="Password"  name="password"  path="password"/><br><br>
        <label for="password2">Enter password again</label><br>
        </s:bind>

        <s:bind path="password2">
        <sf:input class="text-field" id="password2" type="password" placeholder="Password"  path="password2" name="password2"/><br><br>
        </s:bind>
        <div style="float: right; margin-top: -175px; margin-right: 200px">
        <s:bind path="firstName">
            <label for="firstName">First name: </label><br>
          <sf:input class="text-field" id="firstName" type="text" placeholder="First name" name="firstName"  path="firstName"/><br><br>
          </s:bind>

            <s:bind path="lastName">
                <label for="firstName">Last name: </label><br>
                <sf:input class="text-field" id="lastName" type="text" placeholder="Last name"  name="lastName" path="lastName"/><br><br>
            </s:bind>

            <s:bind path="phoneNumber">
              <label for="pnum">Phone number: </label><br>
                <sf:input class="text-field" id="pnum" type="tel" placeholder="(###)-###-####"  name="phoneNumber" path="phoneNumber"/><br><br>
            </s:bind>

            <div>
                <label for="userRole" class="form-label select-label">Select User Role</label>

                <select id="userRole" name = "userRole">
                    <option value="1" disabled>Select Role</option>
                    <option value="Student">User</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>


            <br>
            <s:bind path="dob">
          </div>
            <label for="start">Enter Birthday</label>
            <br>
         <font color="red" style="font-size: 15px"><sf:errors path="${status.expression}" /></font>

        <sf:input style="margin-left: 250px" type="date" id="start" name="dob" path="dob"/> <br> <br>
        </s:bind>

          <div style="margin-left: 150px" id="gender-radio">

        <p>Gender: </p>
        <s:bind path="gender">
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio"  id="femaleGender"
                   value="Female" checked name="gender"/>
            <label class="form-check-label" for="femaleGender">Female</label>
          </div>

          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio"  id="maleGender"
                   value="Male" checked name="gender"/>
            <label class="form-check-label" for="maleGender">Male</label>
          </div>

          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" id="otherGender"
                   value="Other" checked name="gender" />
            <label class="form-check-label" for="otherGender">Other</label>
          </div>
        </s:bind>
      </div>


        <input style="margin-left: 250px" id = "register-button" type="submit" value="Register">
      <a href="/PeteParkingMgt/">
           <input  style="margin-left: 125px" id = "go-back-button" type="button" value="Back to Login">
      </a>


    </form>
  </div>

</div>

</body>
</html>
