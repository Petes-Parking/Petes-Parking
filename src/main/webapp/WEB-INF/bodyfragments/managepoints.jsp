<!DOCTYPE html>
<html>
<head>
    <title>Manage Student Points</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        #container {
            width: 50%;
            margin: auto;
            margin-top: 50px;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(7, 3, 3, 0.82);
            text-align: center;
            height: 1200px;
            display: inline-block;
            vertical-align: top;
            background-color: #CEB888;

        }

        #buttons {
            display: inline-block;
        }
        #add-btn, #deduct-btn { /*red and green circle buttons*/
            width: 50px;
            height: 50px;
            border-radius: 50%;
            border: none;
            color: white;
            font-size: 24px;
            margin-right: 10px;
        }
        #add-btn {
            background-color: green;
        }
        #deduct-btn {
            background-color: red;
        }
        #points {
            margin-top: 20px;
            align-items: center;
            text-align: center;
        }
        #points-to-add {
            margin-top: 20px;
            align-items: center;
            text-align: center;

        }
        #points-to-add label {
            margin-right: 10px;
            align-items: center;
            text-align: center;
        }
        #points-to-add input[type="number"] { /*text input*/
            width: 100px;
            font-size: 16px;
            padding: 5px;
            margin-right: 10px;
            align-items: center;
            text-align: center;

        }
        #points-to-add input[type="submit"] { /* first add button*/
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;
            align-items: center;
            text-align: center;
        }
        button1:hover{
            background-color: #555;
        }
        #points-to-change {
            margin-top: 20px;
            align-items: center;
            text-align: center;
        }
        #points-to-change label {
            margin-right: 10px;
            align-items: center;
            text-align: center;
        }
        #points-to-change select {
            font-size: 16px;
            padding: 5px;
            margin-right: 10px;
            align-items: center;
            text-align: center;
        }
        #points-to-change #add-btn, #points-to-change #deduct-btn {
            margin-right: 10px;
            align-items: center;
            text-align: center;

        }
        #save-btn {
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }

        button{
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }

        #addButton{
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;

        }
        #deductButton {
            padding: 10px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }
        body{
            background: #373A36;
        }

        h1{
            text-align: center;
        }
    </style>
</head>
<body>
<div id="container">
    <h1>Student Name - Points </h1>
    <div id="buttons">
        <button id="add-btn">+</button>
        <button id="deduct-btn">-</button>
    </div>
    <div id="points">
        <h2>Points to be added:</h2>
        <form id="points-to-add">
            <label for="points-input">THIS</label>
            <input type="number" id="points-input" name="points" required>
            <input type="submit" value="Add">
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
        <button id="save-btn">Save Changes</button>
    </div>
    <div class="row">
        <button1 id="addButton">Add</button1>
        <button1 id="deductButton">Deduct</button1>
    </div>
</div>

