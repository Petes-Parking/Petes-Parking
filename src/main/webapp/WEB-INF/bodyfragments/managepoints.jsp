<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Student Points</title>
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

        .table-striped tbody tr.selected {
            background-color: cadetblue;
        }
        .selected {
            background-color: cornflowerblue;
        }

        #buttons {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        .circle-btn {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            border: none;
            color: white;
            font-size: 24px;
            margin-right: 10px;
            margin-bottom: 20px;
        }
        #add-btn {
            background-color: green;
        }
        #deduct-btn {
            background-color: red;
        }

        #points, #points-to-add, #points-to-change {
            margin-bottom: 20px;
        }
        label, input[type="number"], input[type="submit"], button {
            margin-right: 10px;
        }

        .input-button, .circle-btn {
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }
        .input-button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<div id="container">
    <h1>Edit - Points </h1>
    <div id="buttons">
        <button class="circle-btn" id="add-btn">+</button>
        <button class="circle-btn" id="deduct-btn">-</button>
    </div>
    <div id="points">
        <h2>Custom points to be changed:</h2>
        <form action="${pageContext.request.contextPath}/admin/updatePoints" method="post" id="points-to-add">
            <label for="points-input">Points</label>
            <input type="number" id="points-input" name="points" required>
            <input class="input-button" type="submit" value="Add">
            <input class="input-button" type="submit" value="Deduct">
        </form>
    </div>
    <div id="points-to-change">
        <h2>Edit points in increments:</h2>
        <label for="number-select">Points:</label>
        <select class="dropdown" id="number-select">
            <option value="5">5</option>
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option>
            <option value="25">25</option>
            <option value="30">30</option>
            <option value="35">35</option>
            <option value="40">40</option>
            <option value="45">45</option>
            <option value="50">50</option>
        </select>
        <button class="input-button" id="save-btn">Add</button>
        <%-- button next to custom points in increments of 5--%>

        <button class="input-button" id="save-btn">Deduct</button>
        <%-- deduct button next to custom points in increments of 5--%>

    </div>
    <div class="row">
        <div class="col-md-12">
            <h3>Users List:</h3>
            <form action="" method="POST" id="userForm">
                <input type="hidden" name="selectedUserId" id="selectedUserId">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Points</th>
                        <th>User Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Loop through the users and display the list -->
                    <c:forEach items="${adminUserList}" var="user" varStatus="status">
                        <tr class="user-row" data-user-id="${user.id}">
                            <td>${user.id}</td>
                            <td>${user.firstName} ${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.points}</td>
                            <td>${user.userRole}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<script>
    // Add click event listeners for user rows
    // Add click event listeners for user rows
    $('.user-row').on('click', function() {
        $('.user-row').removeClass('selected');
        $(this).addClass('selected');
        let userId = $(this).data('user-id');
        $('#selectedUserId').val(userId);
        console.log("Clicked User ID:", userId);
    });

    $('#add-btn').on('click', function() {
        let points = 1;
        let userId = $('#selectedUserId').val();
        if (userId) {
            $.post("${pageContext.request.contextPath}/admin/updatePoints", {userId: userId, points: points, action: 'add'}, function() {
                location.reload();
            });
        }
    });

    $('#deduct-btn').on('click', function() {
        let points = 1;
        let userId = $('#selectedUserId').val();
        if (userId) {
            $.post("${pageContext.request.contextPath}/admin/updatePoints", {userId: userId, points: points, action: 'deduct'}, function() {
                location.reload();
            });
        }
    });

    $('#points-to-add input[type="submit"]').on('click', function(event) {
        event.preventDefault();
        let points = $('#points-input').val();
        let userId = $('#selectedUserId').val();
        let action = $(this).val().toLowerCase();

        if (userId && points) {
            $.post("${pageContext.request.contextPath}/admin/updatePoints", {userId: userId, points: points, action: action}, function() {
                location.reload();
            });
        }
    });

    $('#save-btn').on('click', function() {
        let points = $('#number-select').val();
        let userId = $('#selectedUserId').val();
        let action = $(this).val().toLowerCase();

        if (userId && points) {
            $.post("${pageContext.request.contextPath}/admin/updatePoints", {userId: userId, points: points, action: action}, function() {
                location.reload();
            });
        }
    });

</script>
