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
      margin: auto;
      margin-top: 20px;
      padding: 30px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      display: inline-block;
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
<h1 style="text-align: center; margin-top: 50px">Parking Pals</h1>
<div class="container" style="float: left; margin-left: 20px; width: 55%">
  <h3>Add new Parking Pals:</h3>
  <input type="text" id="search" placeholder="Search for a name here...">
  <table>
    <thead>
    <tr>
      <th>Name</th>
      <th>Email</th>
      <th>Vehicle</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
</div>
<div class="container" style="float: right; margin-right: 20px; width: 30%; text-align: center">
  <h3>Incoming Friend Requests:</h3>
</div>
<div class="container" style="float: left; margin-left: 20px; width: 55%">
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
      <td>Mitch Daniels</td>
      <td>mitch@purdue.com</td>
      <td>Gold Lambo</td>
    </tr>
    </tbody>
  </table>
</div>
<div class="container" style="float: right; margin-right: 20px; width: 30%; text-align: center">
  <h3>Outgoing Friend Requests:</h3>
</div>
<script>
  const searchInput = document.getElementById('search');
  const tableRows = document.querySelectorAll('tbody tr');

  searchInput.addEventListener('input', () => {
    const searchValue = searchInput.value.toLowerCase();
    tableRows.forEach(row => {
      const name = row.querySelector('td:first-child').textContent.toLowerCase();
      if (name.includes(searchValue)) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    });
  });
</script>
</body>
</html>
