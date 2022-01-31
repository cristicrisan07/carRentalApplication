<?php
require 'init.php';
$idUser=$_POST["idClient"];
$deliverLocation=$_POST["location"];
$VIN=$_POST["vin"];
$today = date("20y/m/d"); 
$sql="select * from locations where Name like '$deliverLocation'";
$result=mysqli_query($conn,$sql);
if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$nm=$row["idLocation"];
	$sql="UPDATE `cardb`.`cars` SET `Location` = '$nm' WHERE (`VIN` = '$VIN')";
    $res=mysqli_query($conn,$sql);
	$sql="UPDATE `cardb`.`agreements` SET `deliver_location` = '$nm', `deliver_date` = '$today' WHERE (`idClient` = '$idUser')";
    $res=mysqli_query($conn,$sql);
	echo "Delivered!";
}
else {
	echo "No such address!";
	
}
mysqli_close($conn);
?>