<?php
require 'init.php';
$VIN=$_POST["VIN"];
$CLIENT=$_POST["idClient"];


$sql="SELECT * from cars where VIN='$VIN'";
$result=mysqli_query($conn,$sql);
$row=mysqli_fetch_assoc($result);
$today = date("20y/m/d");  
echo $today;
$sql="INSERT INTO `cardb`.`agreements` (`idClient`, `idCar`, `departure_location`, `departure_date`) VALUES ('$CLIENT', '$row[idCar]', '$row[Location]', '$today')";
$result=mysqli_query($conn,$sql);
$sql="UPDATE `cardb`.`cars` SET `Location` = null WHERE (`VIN` = '$VIN')";
$result=mysqli_query($conn,$sql);
?>