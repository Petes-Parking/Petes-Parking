<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Forgot Password</title>
  <style>
    body {
      background-color: #CEB888;
      font-family: Arial, Helvetica, sans-serif;
      color: #333;
    }
    .container {
      width: 40%;
      margin: auto;
      margin-top: 50px;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

    input, button {
      margin: 0 auto;
      display: block;
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
      width: 80%;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    }

    button {
      padding: 10px;
      background-color: #333;
      color: #fff;
      width: 30%;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
    }

    button:hover {
      background-color: #555;
    }

    /* Popup styling */
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
  </style>
</head>
<body>
<div class="container">
  <h1>Forgot Password?</h1>
  <form action="" method="post" onsubmit="event.preventDefault(); showPopup();">
    <label for="email">Please enter the email associated with your Pete's Parking account below to reset your password.</label><br>
    <input type="email" id="email" name="email" placeholder="Enter email here" required><br>
    <button type="submit" id="submit">Submit</button>
  </form>
</div>
<!-- Popup HTML -->
<div id="popup" class="popup">
  <br><br><p>Verification email sent!</p><br>
  <button id="close-button" type="button">Close</button>
</div>
<script>
  const submitButton = document.getElementById("submit");
  const closeButton = document.getElementById("close-button");

  submitButton.addEventListener("click", () => {
    // Show the popup
    popup.style.display = "block";
  });

  closeButton.addEventListener("click", () => {
    // Hide the popup
    popup.style.display = "none";
    window.location.href = "${pageContext.request.contextPath}/resetpassword";
  });
</script>
</body>
</html>