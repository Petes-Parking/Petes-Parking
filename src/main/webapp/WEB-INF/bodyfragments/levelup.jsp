<%--
  Created by IntelliJ IDEA.
  User: maxfuligni
  Date: 3/25/23
  Time: 1:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Level Up Popup</title>
    <style>
        /* Popup background overlay */
        #overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: none;
        }

        /* Popup container */
        #popup {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            width: 80%;
            max-width: 400px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            display: none;
        }

        /* Popup header */
        #popup h2 {
            margin: 0;
            font-size: 24px;
            font-weight: bold;
            text-align: center;
            color: #333;
        }

        /* Popup content */
        #popup p {
            margin: 10px 0;
            font-size: 18px;
            text-align: center;
            color: #666;
        }

        /* Close button */
        #close-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #333;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 50%;
            width: 30px;
            height: 30px;
            line-height: 30px;
            text-align: center;
            cursor: pointer;
        }
    </style>
</head>
<body>
<<div id="overlay"></div>
<div id="popup">
    <button id="close-btn">&times;</button>
    <h2>Congratulations!</h2>
    <p>You've reached level ${level}!</p>
    <p>You need ${pointsToNext} points for the next level.</p>
</div>

<script>
    function showPopup() {
        document.getElementById('overlay').style.display = 'block';
        document.getElementById('popup').style.display = 'block';
    }

    document.getElementById('close-btn').addEventListener('click', function() {
        document.getElementById('overlay').style.display = 'none';
        document.getElementById('popup').style.display = 'none';
        window.location.href = "${pageContext.request.contextPath}/profile";
    });
</script>

<script>
    // Execute when the page is loaded
    document.addEventListener('DOMContentLoaded', function() {
        // Check if the user has leveled up
        <% if (Boolean.TRUE.equals(request.getAttribute("levelUp"))) { %>
        showPopup();
        <% } %>
    });
</script>

</body>
</html>
