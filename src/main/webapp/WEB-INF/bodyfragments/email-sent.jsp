<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Need Help?</title>
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
      text-align: center;
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
      color: white;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
      width:100%;
    }

    button:hover {
      background-color: #555;
    }



  </style>
</head>
<body>
<div class="container">
  <img  class = "logo" src="https://raw.githubusercontent.com/Petes-Parking/Petes-Parking/master/src/main/webapp/resources/image/Petes-Parking-logo.png" width="250" height="250" alt="" />
  <h1>Email Sent Successfully!</h1>

  <a href="${pageContext.request.contextPath}/main">
    <button type="button" >Back to Main Page</button>
  </a>
</div>
</body>
</html>