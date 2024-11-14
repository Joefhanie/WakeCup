<?php
// Database connection parameters
$dbHost = 'localhost';
$dbUsername = 'root';
$dbPassword = 'admin';
$dbName = 'wakecup';

$conn = new mysqli($dbHost, $dbUsername, $dbPassword, $dbName);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: ". $conn->connect_error);
}

$currentDateTime = date('Y-m-d');

// Prepare and bind
$stmt = $conn->prepare("INSERT INTO list_of_categories (`date_created`, `name`, `description`, `status`) VALUES (?,?,?,?)");
$stmt->bind_param("ssss", $currentDateTime, $_POST['name'], $_POST['description'], $_POST['status']);

// Execute the prepared statement
$stmt->execute();

echo "New record created successfully";
error_log(print_r($_POST, true));

$stmt->close();
$conn->close();
