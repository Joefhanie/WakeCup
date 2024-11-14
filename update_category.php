<?php
// Database connection parameters
$dbHost = 'localhost';
$dbUsername = 'root';
$dbPassword = 'admin';
$dbName = 'wakecup';

// Connect to the database
$conn = new mysqli($dbHost, $dbUsername, $dbPassword, $dbName);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: ". $conn->connect_error);
}

$id = $_POST['id']; // Retrieve the ID from the POST data
$name = $_POST['name'];
$description = $_POST['description'];
$status = $_POST['status'];

// Prepare and execute the update statement
$stmt = $conn->prepare("UPDATE list_of_categories SET name=?, description=?, status=? WHERE id=?");
$stmt->bind_param("sssi", $name, $description, $status, $id);

$stmt->execute();
error_log(print_r($_POST, true));

echo "Record updated successfully";
$stmt->close();
$conn->close();
