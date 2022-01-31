<?php
require 'init.php';
$idUser=$_POST["idClient"];
$option=$_POST["Type"];
$today = date("20y/m/d");  
if($option=="22go")
{
	$type=1;
}
else{
	if($option=="72go"){
		$type=2;
	}
	else{
		$type=3;
	}
}
$sql="INSERT INTO `cardb`.`subscriptions` (`idClient`, `start_date`, `type`) VALUES ('$idUser', '$today', '$type')";
$result=mysqli_query($conn,$sql);
$sql="SELECT idSubscription FROM cardb.subscriptions where idClient='$idUser'";
$result=mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$idSub=$row["idSubscription"];
}
else{
	echo "This user is not subscribed";
	mysqli_close($conn);
}
$sql="UPDATE `cardb`.`clients` SET `Subscription` = '$idSub' WHERE (`idClient` = '$idUser')";
$result=mysqli_query($conn,$sql);
mysqli_close($conn);
?>