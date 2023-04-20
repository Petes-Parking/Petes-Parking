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
      margin-top: 20px;
      padding: 10px;
      padding-bottom: 20px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      text-align: center;
    }

    p {
      float: left;
      padding-left: 10px;
    }

    h2 {
      text-align: center;
      margin-bottom: 20px;
      font-weight: bold;
    }

    h4 {
      text-align: center;
      font-weight: bold;
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
      padding: 8px;
      width: 18%;
      background-color: #333;
      color: #fff;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
      align-self: center;
    }

    button:hover {
      background-color: #414141;
      transition: 0.3s;
      border-radius: 15px;
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
      align-items: center;
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
      margin-top: 15%;
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
    <button id="return" href="/main" style="float: left; font-size: small; width: 8%; margin-top: 1%; margin-left: 1%">Back</button><br><br>
  </a>

  <h2>Settings</h2>
  <h4>Color Preferences</h4>

  <label for="lightmode" style="display: inline-block;">Light mode
    <input type="radio" id="lightmode" name="preference" value="light">
  </label><br>
  <label for="darkmode" style="display: inline-block;">Dark mode
    <input type="radio" id="darkmode" name="preference" value="dark">
  </label><br>
  <button id="theme-save-button" type="submit" style="margin-bottom: 2%">Save Theme</button><br>

  <h4 style="padding-top: 1px;">Email Notification Preferences</h4>
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
        <input type="number" id="timerInput" name="timerInput" style="text-align:left; width: 15%; margin-left: 15px" min="1" value="${theTimer}" ${expirationPref ? '' : 'disabled'}>
      </div>
      <button id="save-email" type="button" onclick="show('confirmed-popup')">Save Changes</button><br>
    </div>

    <div class="popup" id="confirmed-popup">
      <div class="text">
        <p3>Settings Saved.</p3>
      </div>
      <button class="submit" type="submit" id="submit" onclick="hide('confirmed-popup')">Close</button>
    </div>
  </form>

</div>
<script>
  const themeSaveButton = document.getElementById('theme-save-button');
  const container = document.querySelector('.container');
  const body = document.querySelector('body');

  // Check if themePreference is already set in localStorage
  if (!localStorage.getItem('themePreference')) {
    // Set default value if themePreference is not set
    localStorage.setItem('themePreference', "light");
  }

  themeSaveButton.addEventListener("click", () => {
    const selectedMode = document.querySelector('input[name="preference"]:checked').value;
    localStorage.setItem('themePreference', selectedMode);
    alert('Theme preference saved!');
    updateTheme(selectedMode);
  });

  // Get the user's theme preference
  const savedMode = localStorage.getItem('themePreference');

  // Set the radio button based on the current mode
  if (savedMode === "light") {
    updateTheme("light");
    document.getElementById("lightmode").checked = true;
  } else if (savedMode === "dark") {
    updateTheme("dark");
    document.getElementById("darkmode").checked = true;
  }

  function updateTheme(mode) {
    if (mode === "light") {
      container.style.backgroundColor = "#fff";
      container.style.color = "#333";
      body.style.backgroundColor = "#CEB888";
    } else if (mode === "dark") {
      container.style.backgroundColor = "#565656";
      container.style.color = "#fff";
      body.style.backgroundColor = "#333";
    }
  }

  function toggleInputField() {
    var timerInput = document.getElementById("timerInput");
    if (timerInput.disabled) {
      timerInput.disabled = false;
    } else {
      timerInput.disabled = true;
    }
  }

  $ = function(id) {
    return document.getElementById(id);
  }

  var show = function(id) {
    $(id).style.display ='block';
  }
  var hide = function(id) {
    $(id).style.display ='none';
  }

</script>