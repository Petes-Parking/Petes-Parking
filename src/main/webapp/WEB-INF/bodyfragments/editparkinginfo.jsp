```<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Parking Information</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #CEB888;
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
      background-color: #fff;
    }

    .button{
      background-color: #CEB888;
      color: #fff;
      border: none;
      border-radius: 5px;
      padding: 5px 10px;
      cursor: pointer;
    }

    .button:hover{
      background-color: goldenrod;
      color: black;
    }

    .table-striped tbody tr:nth-of-type(odd) {
      background-color: white;
    }

    .table-striped tbody tr:nth-of-type(even) {
      background-color: darkgoldenrod;
    }
  </style>
</head>
<body>
<div id="container">
  <h1 id="header-line">Edit Parking Information</h1>
  <div class="row">
    <div class="col-md-12">
      <form action="" method="POST" id="userForm">
        <input type="hidden" name="selectedUserId" id="selectedUserId">
        <body onload="generateLists()"></body>
        <div class="list-area">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Parking Area</th>
              <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${parkingAreas}" var="area" varStatus="status">
              <tr>
                <td>${area}</td>
                <td>
                  <a  href="${pageContext.request.contextPath}/admin/parkingArea=${area}" class="button" >Edit</a>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </form>
      <a href="${pageContext.request.contextPath}/adminview">
        <button class="button" >Back</button>
      </a>
    </div>
  </div>
</div>
</body>
</html>