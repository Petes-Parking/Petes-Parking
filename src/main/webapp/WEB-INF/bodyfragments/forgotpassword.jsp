<%--
  Created by IntelliJ IDEA.
  User: aakashjariwala
  Date: 2/28/23
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <title>forgot-password</title>
  <style>
    body {
      background-color: #CEB888;
      font-family: Arial, Helvetica, sans-serif;
      color: #333;
    }
    .container {
      width: 50%;
      margin: auto;
      margin-top: 50px;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
    }

    form {
      display: flex;
      flex-direction: column;
    }

    label {
      font-size: 1.1rem;
      margin-bottom: 5px;
    }

    input {
      padding: 10px;
      margin-top: 10px;
      margin-bottom: 15px;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    }

    button {
      padding: 10px;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
    }

    button:hover {
      background-color: #555;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Forgot Password?</h1>
  <form action="" method="post">
    <label for="email">Please enter the email associated with your Pete's Parking account below to reset your password.</label>
    <input type="email" id="email" name="email" placeholder="Enter email here" required>
    <button type="submit">Submit</button>
  </form>
</div>
</body>
</html>
