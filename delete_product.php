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

// Get the category ID from the POST data
$id = $_POST['id'];

// SQL query to delete the category
$sql = "DELETE FROM list_of_products WHERE id =?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id); // Bind the category ID parameter
$stmt->execute();

// Close the statement and connection
$stmt->close();
$conn->close();

// Redirect back to the products page
header("Location: /products.html"); // Adjust the URL according to your project structure
exit;
