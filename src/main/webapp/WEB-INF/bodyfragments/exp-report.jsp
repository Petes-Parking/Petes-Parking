<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Expiration Report</title>
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

        p2 {
            font-family: Helvetica, sans-serif;
            text-align: left;
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
            margin-top: 6px;
            margin-bottom: 6px;
            border: solid 1px #000000;
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
            width: 80%;
            margin-bottom: 5px;
        }

        button:hover {
            background-color: #555;
        }

        select {
            padding: 10px;
            margin-top: 6px;
            margin-bottom: 6px;
            border: solid 1px #000000;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .desc {
            width: 80%;
        }

        .image {
            width: 50%;
            margin-bottom: 20px;
        }

        .confirmed-popup {
            display: none;
            position: fixed;
            width: 350px;
            height: 200px;
            left: 43%;
            top: 20%;
            background: #CEB888;
            z-index: 20;
            border-radius: 30px;
        }

        #confirmed-popup:after {
            position: fixed;
            content: "";
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            background: rgba(0,0,0,0.5);
            z-index: -2;
            border-radius: 30px;
        }

        #confirmed-popup:before {
            position: absolute;
            content: "";
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            background: #FFF;
            z-index: -1;
            border-radius: 30px;
        }

        .close-button {
            margin-top: 20%;
            margin-left: 10%;
            align-content: center;
        }

        p3 {
            font-family: Helvetica, sans-serif;
            text-align: center;
            font-size: large;
        }

        .text {
            margin-left: 30%;
            margin-top: 10%;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Expiration Report</h1>
    <p>Please enter information about a car that is still in a spot after its timer has expired. We will review the report as soon as possible and reward points for correct reports.</p>
    <form method="post" action="${pageContext.request.contextPath}/submitExpReport" style="display: inline;">
        <p2>License Plate:</p2>
        <input class="license" type="text" id="licensePlate" name="licensePlate" placeholder="License Plate" required><br>
        <p2>Parking Lot:</p2>
        <select name="parkingLot" id=lot" placeholder="Parking Lot" required>
            <option value="corec">COREC Parking Lot</option>
            <option value="mccutcheon">McCutcheon Dr. Parking Garage</option>
            <option value="grant">Grant St. Parking Garage</option>
            <option value="univ">University St. Parking Garage</option>
            <option value="rossade">Ross-Ade Stadium Parking Lot</option>
            <option value="northwestern">Northwestern St. Parking Garage</option>
        </select><br>
        <p2>Description:</p2>
        <input class="desc" type="text" id="description" name="description" placeholder="Enter description here." required><br>
            <p2>Image:</p2>
            <input class="image" type="file" id="imageURL" name="imageURL" accept="image/*"><br>
    <button type="file" id="image-input" style="display: none;" onchange="handleImageSelect(event)"/><br>
    <button type="submit" class="save-button", id="submit">Submit</button>
    <a href="${pageContext.request.contextPath}/main">
        <button type="button">Cancel</button>
    </a>
    </form>
</div>

<div class="confirmed-popup" id="confirmed-popup">
    <div class="text">
        <p3>Report Submitted.</p3>
    </div>
    <button class="close-button", href="#", id="close-button">Close</button>
</div>
<script>
    const saveButton = document.getElementById("save-button");
    const closeButton = document.getElementById("close-button");
    const popup = document.getElementById("confirmed-popup");

    saveButton.addEventListener("click", () => {
        popup.style.display = "block";
    });

    closeButton.addEventListener("click", () => {
        popup.style.display = "none";
        document.location.href = "${pageContext.request.contextPath}/main";
    });
</script>
</body>
</html>