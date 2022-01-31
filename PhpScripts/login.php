<?php 
require 'init.php';

$username=$_POST["Username"];
$password=$_POST["Password"];

$sql = "select * from clients where username='$username' and password='$password'";
$result= mysqli_query($conn,$sql);

$response=array();

if(mysqli_num_rows($result)>0)
{
 $row=mysqli_fetch_assoc($result);
 $code="logged_in";
 array_push($response,array("code"=>$code,"user_id"=>$row["idClient"],"first_name"=>$row["First_name"],"last_name"=>$row["Last_name"]));
 echo json_encode($response);
 mysqli_close($conn);
}
else {

$code= "login_failed";
 array_push($response,array("code"=>$code));
 echo json_encode($response);
 mysqli_close($conn);
}
?>