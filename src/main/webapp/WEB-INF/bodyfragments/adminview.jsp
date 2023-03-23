<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin View</title>
    <!-- Add Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: white;
            color: goldenrod;
        }
        .btn {
            background-color: goldenrod;
            color: black;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: goldenrod;
        }

        .widget {
            height: 150px;
            border: 1px solid goldenrod;
            transition: background-color 0.3s ease;
            cursor: pointer;
        }

        .widget:hover {
            background-color: goldenrod;
            color: black;
        }

        .bg-oldgoldenrod .widget:hover {
            background-color: black;
            color: goldenrod;
        }

        .widget a {
            display: block;
            height: 100%;
            width: 100%;
            padding: 0 1rem;
            transition: color 0.3s ease;
        }

        .widget:hover a {
            color: black;
        }

        .bg-oldgoldenrod .widget:hover a {
            color: goldenrod;
        }

    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center">Admin View</h1>

    <hr/>
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
                ${message}
        </div>
    </c:if>

    <!-- Add your widgets section here -->

    <!-- Widget section -->
    <div class="row mb-4">
        <div class="col-md-12">
            <h3>Widgets:</h3>
        </div>
        <div class="col-md-3">
            <div class="widget rounded bg-black text-center text-uppercase py-4">
                <a href="/admin/widget1" class="text-decoration-none text-reset">Widget 1</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget rounded bg-oldgoldenrod text-center text-uppercase py-4">
                <a href="/admin/widget2" class="text-decoration-none text-reset">Widget 2</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget rounded bg-black text-center text-uppercase py-4">
                <a href="/admin/widget3" class="text-decoration-none text-reset">Widget 3</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget rounded bg-oldgoldenrod text-center text-uppercase py-4">
                <a href="/admin/widget4" class="text-decoration-none text-reset">Widget 4</a>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-12">
            <h3>Users List:</h3>
            <form action="" method="POST" id="userForm">
                <input type="hidden" name="selectedUserId" id="selectedUserId">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>User ID</th>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Loop through the users and display the list -->
                    <c:forEach items="${adminUserList}" var="user" varStatus="status">
                        <tr class="user-row" data-user-id="${user.id}">
                            <td>${status.index + 1}</td>
                            <td>${user.id}</td>
                            <td>${user.firstName} ${user.lastName}</td>
                            <td>${user.email}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>


    <div class="row">
        <div class="col-md-12">
            <button class="btn btn-primary" id="editUserBtn" disabled>Edit User</button>
            <button class="btn btn-danger" id="deleteUserBtn" disabled>Delete User</button>
            <button class="btn btn-warning" id="givePermissionBtn" disabled>Give Permission</button>
            <button class="btn btn-secondary float-right" id="logoutBtn">Logout</button>
        </div>
    </div>
</div>

<!-- Add Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // Add click event listeners for user rows
    $('.user-row').on('click', function() {
        $('.user-row').removeClass('selected');
        $(this).addClass('selected');
        $('#selectedUserId').val($(this).data('user-id'));
        $('#editUserBtn').prop('disabled', false);
        $('#deleteUserBtn').prop('disabled', false);
        $('#givePermissionBtn').prop('disabled', false);
    });

    // Add click event listeners for buttons
    $('#editUserBtn').on('click', function() {
        let selectedUserId = $('#selectedUserId').val();
        window.location.href = `${pageContext.request.contextPath}/admin/edit/` + selectedUserId;
    });


    $('#deleteUserBtn').on('click', function() {
        let selectedUserId = $('#selectedUserId').val();
        if (confirm('Are you sure you want to delete this user?')) {
            $('#userForm').attr('action', `${pageContext.request.contextPath}/admin/deleteUser`);
            $('#userForm').submit();
        }
    });

    $('#givePermissionBtn').on('click', function() {
        let selectedUserId = $('#selectedUserId').val();
        $('#userForm').attr('action', `${pageContext.request.contextPath}/admin/givePermission`);
        $('#userForm').submit();
    });

    $('#logoutBtn').on('click', function() {
        window.location.href = '${pageContext.request.contextPath}/logout';
    });
</script>
</body>
</html>






