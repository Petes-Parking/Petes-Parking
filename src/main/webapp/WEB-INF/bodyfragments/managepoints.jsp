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
    <h1>Student Name - Points </h1>
    <div id="buttons">
        <button class="circle-btn" id="add-btn">+</button>
        <button class="circle-btn" id="deduct-btn">-</button>
    </div>
    <div id="points">
        <h2>Points to be added:</h2>
        <form id="points-to-add">
            <label for="points-input">THIS</label>
            <input type="number" id="points-input" name="points" required>
            <input class="input-button" type="submit" value="Add">
        </form>
    </div>
    <div id="points-to-change">
        <h2>Points to be changed:</h2>
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
        <button class="input-button" id="save-btn">Save Changes</button>
    </div>
</div>
```