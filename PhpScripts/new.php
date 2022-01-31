<?php 
require 'init.php';
$subscription_type=$_POST["S_type"];
$id_client=$_POST["Id_client"];
$date=$_POST["Date"];

$sql="INSERT INTO `cardb`.`subscriptions` (`idClient`, `start_date`, `type`) VALUES ('$id_client', '$date', '$subscription_type')";
$result=mysqli_query($conn,$sql);
$sql="select idSubscription from subscriptions where idClient='$id_client'";

$sql="UPDATE `cardb`.`clients` SET `Subscription` = '$result' WHERE (`idClient` = '$id_client')";
?>