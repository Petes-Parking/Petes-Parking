<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit User: ${user.firstName} ${user.lastName}</title>
  <style>
    body {
      background-color: white;
      font-family: Arial, sans-serif;
    }

    h1 {
      color: goldenrod;
    }

    label {
      color: black;
      display: inline-block;
      width: 120px;
      text-align: right;
      margin-bottom: 10px;
    }

    input[type="text"], input[type="password"], select {
      width: 200px;
    }

    .form-group {
      margin-bottom: 15px;
    }

    .container {
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    .submit-button {
      background-color: goldenrod;
      color: black;
      border: none;
      cursor: pointer;
      padding: 10px 20px;
      margin-left: 120px;
      margin-top: 15px;
    }

    .submit-button:hover {
      background-color: black;
      color: goldenrod;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Edit User</h1>
  <form action="${pageContext.request.contextPath}/user/update" method="POST">
    <input type="hidden" name="id" value="${user.id}">

    <div class="form-group">
      <label for="firstName">First Name:</label>
      <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
    </div>

    <div class="form-group">
      <label for="lastName">Last Name:</label>
      <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" id="email" name="email" value="${user.email}" required>
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" value="${user.password}" required>
    </div>

    <div class="form-group">
      <label for="gender">Gender:</label>
      <select id="gender" name="gender" required>
        <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
        <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
        <option value="Other" ${user.gender == 'Other' ? 'selected' : ''}>Other</option>
      </select>
    </div>

    <input type="submit" value="Save Changes" class="submit-button">
  </form>
</div>
</body>
</html>
