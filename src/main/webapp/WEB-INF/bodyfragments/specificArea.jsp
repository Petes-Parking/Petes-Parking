<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Parking Area</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #373A36;
    }
    #container {
      width: 50%;
      margin: auto;
      margin-top: 50px;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(7, 3, 3, 0.82);
      text-align: center;
      min-height: 600px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      background-color: #CEB888;
    }
    .input-label, .input-field {
      margin-bottom: 20px;
    }
    .input-label {
      color: white;
      font-size: 18px;
      font-weight: bold;
    }
    .input-field {
      background-color: goldenrod;
      border: none;
      color: white;
      font-size: 18px;
      padding: 10px;
      width: 100%;
    }
    .input-button {
      background-color: #333;
      color: white;
      border: none;
      border-radius: 5px;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
      cursor: pointer;
      padding: 10px;
    }
  </style>
</head>
<body>
<div id="container">
  <h1>Edit Parking Area</h1>
  <form action="${pageContext.request.contextPath}/admin/updateparking" method="POST">
    <div class="form-group">
      <label for="parkingName" class="input-label">Parking Name</label>
      <input type="text" name="parkingName" id="parkingName" class="input-field" value="${parkingArea.parkingName}" required>
    </div>
    <div class="form-group">
      <label for="parkingAddress" class="input-label">Parking Address</label>
      <input type="text" name="parkingAddress" id="parkingAddress" class="input-field" value="${parkingArea.address}" required>
    </div>
    <input type="hidden" name="parkingId" value="${parkingArea.id}">
    <input type="submit" value="Save Changes" class="input-button">
  </form>
  <a href="${pageContext.request.contextPath}/admin/editparking">
    <button type="button" class="btn btn-primary">Back</button>
  </a>
</div>
</body>
</html>