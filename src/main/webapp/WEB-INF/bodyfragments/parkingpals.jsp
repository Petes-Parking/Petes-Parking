<%--
  Created by IntelliJ IDEA.
  User: aakashjariwala
  Date: 2/28/23
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Parking Pals</title>
  <style>
    body {
      background-color: #CEB888;
      color: #fff;
      font-family: Arial, Helvetica, sans-serif;
    }
    h1 {
      text-align: center;
      color: black;
    }

    h3 {
      color: black;
    }

    .container {
      width: 70%;
      margin: auto;
      margin-top: 50px;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

    input {
      padding: 10px;
      width: 95%;
      margin-bottom: 20px;
      border-width: thin;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
      align-self: auto
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th {
      background-color: #A4A4A4;
      color: #000;
      text-align: left;
      padding: 10px;
      font-size: 1.1rem;
    }

    td {
      border-bottom: 1px solid #000;
      padding: 10px;
      font-size: 1rem;
      color: #000
    }

    tr:hover {
      background-color: #CEB888;
    }

    .even {
      background-color: #DDDDDD;
    }

    .odd {
      background-color: #fff;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>My Parking Pals</h1>
  <input type="text" id="search" placeholder="Search a friend's name here...">
  <h3>My current Parking Pals:</h3>
  <table>
    <thead>
    <tr>
      <th>Name</th>
      <th>Email</th>
      <th>Vehicle</th>
    </tr>
    </thead>
    <tbody>
    <tr class="odd">
      <td>Purdue Pete</td>
      <td>pete@purdue.com</td>
      <td>Boilermaker Express</td>
    </tr>
    <tr class="even">
      <td>Daddy Daniels</td>
      <td>mitch@purdue.com</td>
      <td>Gold Lambo</td>
    </tr>
    </tbody>
  </table>
</div>
</body>
</html>