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

$currentDateTime = date('Y-m-d');

// Prepare and bind
$stmt = $conn->prepare("INSERT INTO list_of_products (`date_created`, `category`, `name`, `price`, `status`) VALUES (?,?,?,?,?)");
$stmt->bind_param("sssss", $currentDateTime, $_POST['productCategory'], $_POST['name'], $_POST['price'], $_POST['status']);

// Execute the prepared statement
$stmt->execute();

echo "New record created successfully";
error_log(print_r($_POST, true));

$stmt->close();
$conn->close();
