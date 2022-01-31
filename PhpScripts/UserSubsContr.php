<?php
require 'init.php';

$userID=$_POST["user_id"];

$sql="SELECT start_date,subscription_type.type FROM subscriptions inner join
subscription_type ON subscriptions.`type`=subscription_type.idSubscriptions
where subscriptions.idClient='$userID'";
$result=mysqli_query($conn,$sql);
$response=array();
if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	if($row["type"]=="22go")
	{
		$expirationDate=date("Y-m-d",strtotime($row["start_date"]. ' + 2 day'));
	}
	else{
		if($row["type"]=="72go"){
			$expirationDate=date("Y-m-d",strtotime($row["start_date"]. ' + 7 day'));
		}
		else{
			$expirationDate=date("Y-m-d",strtotime($row["start_date"]. ' + 30 day'));
		}
	}
    array_push($response,array("subStatus"=>"isSubscribed","expirationDate"=>$expirationDate,"type"=>$row["type"]));
    
}
else {
	array_push($response,array("subStatus"=>"notSubscribed"));
}
$sql="SELECT idCar,departure_location from agreements where idClient='$userID' and deliver_location is NULL";
$result=mysqli_query($conn,$sql);
if(mysqli_num_rows($result)>0)
{
	array_push($response,array("agreeStatus"=>"carRented"));
	$row=mysqli_fetch_assoc($result);
	$sql="select name from locations where idLocation='$row[departure_location]'";
	$res=mysqli_query($conn,$sql);
	$loc=mysqli_fetch_assoc($res);
    array_push($response,array("departure"=>$loc["name"]));
	
	$sql="SELECT VIN,Production_Year,Price_per_km,Battery_level FROM cars where idCar='$row[idCar]'";
	$result=mysqli_query($conn,$sql);
	$secondFetch=mysqli_fetch_assoc($result);
	array_push($response,array("VIN"=>$secondFetch["VIN"],"Year"=>$secondFetch["Production_Year"],"Price"=>$secondFetch["Price_per_km"],"Battery"=>$secondFetch["Battery_level"]));
	$sql="SELECT models.name as model,manufacturers.name as manufacturer FROM models
    inner join manufacturers on models.idModel=(SELECT Model FROM cars where idCar='$row[idCar]') and manufacturers.idManufacturer=models.manufacturer_id";
    $result=mysqli_query($conn,$sql);
	$secondFetch=mysqli_fetch_assoc($result);
    array_push($response,array("model"=>$secondFetch["model"],"manufacturer"=>$secondFetch["manufacturer"]));

}
else{
	array_push($response,array("agreeStatus"=>"nocarRented"));
}
echo json_encode($response);
mysqli_close($conn);
?>