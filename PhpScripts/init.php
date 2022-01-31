<?php 
$host = "34.107.20.90";
$db_user="appuser";
$db_password="Testmydb1";
$db_name="cardb";

global $conn;
$conn=mysqli_connect($host,$db_user,$db_password,$db_name);
?>