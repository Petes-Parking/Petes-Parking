<%--
  Created by IntelliJ IDEA.
  User: aakashjariwala
  Date: 2/28/23
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Settings</title>
  <style>

    body {
      background-color: #CEB888;
      font-family: Arial, Helvetica, sans-serif;
      color: #333;
    }

    .container {
      width: 70%;
      margin: auto;
      margin-top: 50px;
      padding: 10px;
      padding-bottom: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      text-align: center;
    }

    p {
      float: left;
      padding-left: 10px;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
      font-weight: bold;
    }

    h3 {
      text-align: center;
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
      width: 30%;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
    }

    button:hover {
      background-color: #414141;
    }

    .popup {
      position: fixed;
      width: 20%;
      height: 20%;
      top: 30%;
      left: 50%;
      transform: translate(-50%, -50%);
      padding: 10px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      z-index: 9999;
      display: none;
      text-align: center;
    }

    .popup p {
      margin: 0;
      padding: 5px;
    }

    .popup button {
      padding: 5px 10px;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
    }

    .popup button:hover {
      background-color: #555;
    }

    .switch {
      position: relative;
      display: inline-block;
      width: 60px;
      height: 34px;
      vertical-align: middle;
    }

    .switch input {
      opacity: 0;
      width: 0;
      height: 0;
    }

    .slider {
      position: absolute;
      cursor: pointer;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background-color: #ccc;
      -webkit-transition: .4s;
      transition: .4s;
    }

    .slider:before {
      position: absolute;
      content: "";
      height: 26px;
      width: 26px;
      left: 4px;
      bottom: 4px;
      background-color: white;
      -webkit-transition: .4s;
      transition: .4s;
    }

    input:checked + .slider {
      background-color: #333;
    }

    input:focus + .slider {
      box-shadow: 0 0 1px #333;
    }

    input:checked + .slider:before {
      -webkit-transform: translateX(26px);
      -ms-transform: translateX(26px);
      transform: translateX(26px);
    }

    /* Rounded sliders */
    .slider.round {
      border-radius: 34px;
    }

    .slider.round:before {
      border-radius: 50%;
    }

    p2 {
      text-align: center;
    }

    p3 {
      font-size: larger;
    }

    .prefs {
      vertical-align: center;
      padding-left: 25px;
      padding-top: 20px;
    }

    .pref-item {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
    }

    .pref-item p {
      margin: 0;
      margin-left: 10px;
      text-align: left;
    }

    .time {
      text-align: left;
      padding-left: 83px;
    }

    .time label {
      font-size: 16px;
    }
  </style>

</head>
<body>
<div class="container">
  <a href="${pageContext.request.contextPath}/main">
    <p id="return" href="/main">< Return to main page</p><br><br>
  </a>
  <h1>Settings</h1>
  <s:bind path="mode">
  <h3>Color Preferences</h3>
  <input type="radio" id="lightmode" name="mode" value="light">
  <label for="lightmode">Light mode</label><br>
  <input type="radio" id="darkmode" name="mode" value="dark">
  <label for="darkmode">Dark mode</label><br><br>
  </s:bind>

  <s:bind path="hexColor">
  <label for="color-input">Background Color (Hexadecimal):</label>
  <input type="text" id="color-input" name="color" pattern="^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})?$"><br><br>
  </s:bind>
  <button id="save-button" type="submit">Save</button><br>

  <h3 style="padding-top: 50px;">Email Notification Preferences</h3>
  <p2>Select the notifications you'd like to receive from Pete's Parking.</p2><br>

  <form method="post" action="${pageContext.request.contextPath}/settingsSaveEmail">
    <div class="prefs">
      <div class="pref-item">
        <label class="switch">
          <input type="checkbox" name="palPref" value="true" ${parkingPalPref ? 'checked' : ''}>
          <span class="slider round"></span>
        </label>
        <p><strong>Parking Pals</strong><br>Get notified by email when someone sends you a parking pal request, and when a request you sent is accepted or denied.</p>
      </div>

      <div class="pref-item">
        <label class="switch">
          <input type="checkbox" name="repPref" value="true" ${reportPref ? 'checked' : ''}>
          <span class="slider round"></span>
        </label>
        <p><strong>Reports</strong><br>Get notified by email when someone reports a parking violation associated with your account.</p>
      </div>

      <div class="pref-item">
        <label class="switch">
          <input type="checkbox" name="expPref" onchange="toggleInputField()" value="true" ${expirationPref ? 'checked' : ''}>
          <span class="slider round"></span>
        </label>
        <p><strong>Expiring Timer</strong><br>Get notified by email when your parking timer is about to expire.</p>
        <br>
      </div>
      <div class="time">
        <label for="timerInput">Enter number of minutes left on timer when email notification should be sent:</label>
        <input type="number" id="timerInput" name="timerInput" style="text-align:left; padding-right: 5px" min="1" value="${theTimer}" ${expirationPref ? '' : 'disabled'}>
      </div>
      <button id="save-email" type="submit">Save Changes</button><br>
    </div>
  </form>


</div>
<div id="popup" class="popup">
  <br><br><p>Settings saved!</p><br>
  <button id="close-button" type="button">Close</button>
</div>
<script>
  const saveButton = document.getElementById("save-button");
  const container = document.querySelector(".container");
  const body = document.querySelector("body");
  const popup = document.getElementById("popup");
  const closeButton = document.getElementById("close-button");

  // Get the saved mode from local storage
  let savedMode = localStorage.getItem("mode");

  // Set the initial mode based on the saved mode or default to light mode
  let currentMode = savedMode || "light";

  // Set the radio button based on the current mode
  if (currentMode === "light") {
    document.getElementById("lightmode").checked = true;
  } else {
    document.getElementById("darkmode").checked = true;
  }

  saveButton.addEventListener("click", () => {
    const lightMode = document.getElementById("lightmode");
    const darkMode = document.getElementById("darkmode");
    const colorInput = document.getElementById("color-input");

    if (lightMode.checked) {
      container.style.backgroundColor = "#fff";
      container.style.color = "#333";
      body.style.backgroundColor = "#CEB888";
      lightMode.parentElement.style.color = "#333";
      darkMode.parentElement.style.color = "#333";
      currentMode = "light";
    } else if (darkMode.checked) {
      container.style.backgroundColor = "#565656";
      container.style.color = "#fff";
      body.style.backgroundColor = "#333333";
      lightMode.parentElement.style.color = "#fff";
      darkMode.parentElement.style.color = "#fff";
      currentMode = "dark";
    }

    // Set the background color based on the input value
    if (colorInput.value.match(/^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})?$/)) {
      body.style.backgroundColor = colorInput.value;
    }

    // Save the mode to local storage
    localStorage.setItem("mode", currentMode);
    localStorage.setItem("color", colorInput.value);

    // Show the popup
    popup.style.display = "block";
  });

  closeButton.addEventListener("click", () => {
    // Hide the popup
    popup.style.display = "none";
  });

  function toggleInputField() {
    var timerInput = document.getElementById("timerInput");
    if (timerInput.disabled) {
      timerInput.disabled = false;
    } else {
      timerInput.disabled = true;
    }
  }

</script>