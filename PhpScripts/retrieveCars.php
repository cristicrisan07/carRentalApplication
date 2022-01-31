<?php
require 'init.php';
$location=$_POST["Location"];
$sql="select * from locations where Name like '$location'";
$result=mysqli_query($conn,$sql);
if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);
	$nm=$row["idLocation"];
	$sql="select * from cars where Location like '$nm'";
	$result=mysqli_query($conn,$sql);
	$response=array();
      if(mysqli_num_rows($result)>0)
	  {
		  while($row=mysqli_fetch_assoc($result))
		  {
			 $sql="SELECT VIN,Production_Year,Price_per_km,Battery_level FROM cars where idCar='$row[idCar]'";
	$res=mysqli_query($conn,$sql);
	$secondFetch=mysqli_fetch_assoc($res);
	$sql="SELECT models.name as model,manufacturers.name as manufacturer FROM models
    inner join manufacturers on models.idModel=(SELECT Model FROM cars where idCar='$row[idCar]') and manufacturers.idManufacturer=models.manufacturer_id";
    $res=mysqli_query($conn,$sql);
	$thirdFetch=mysqli_fetch_assoc($res);
    array_push($response,array("model"=>$thirdFetch["model"],"manufacturer"=>$thirdFetch["manufacturer"],"VIN"=>$secondFetch["VIN"],"Year"=>$secondFetch["Production_Year"],"Price"=>$secondFetch["Price_per_km"],"Battery"=>$secondFetch["Battery_level"]));
		  }
		  echo json_encode($response);
		  mysqli_close($conn);
	  }
	  else {
		  echo "There are no cars available at this location";
	  }

}
else {
	echo "No such address!";
	mysqli_close($conn);
}
?>