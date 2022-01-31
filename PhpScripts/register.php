<?php

require 'init.php';

$last_name= $_POST["Last_name"];
$first_name= $_POST["First_name"];
$address=$_POST["Address"];
$dled=$_POST["Dled"];
$password=$_POST["Password"];
$unique_info=array(
"email"=> $_POST["Email"],
"user_name"=> $_POST["User_name"],
"pic"=>$_POST["Pic"]
);

$response= array();

function _checkUniqueness($db_item,$conn)
{
	$sql = "select * from clients where username like '$db_item'";

$result=mysqli_query($conn,$sql);
$response= array();

    if(mysqli_num_rows($result)>0)
    {
		$code = "reg_failed";
		$message= "This username already exists.";
		array_push($response,array("code"=>$code,"message"=>$message));
		echo json_encode($response);
	
		mysqli_close($conn);
    }

}

foreach($unique_info as $field)
{
	
	_checkUniqueness($field,$conn);
	
}

 $sql = "INSERT INTO `cardb`.`clients` (`PIC`, `Last_name`, `First_name`, `Address`, `Driver_License_exp_date`, `username`, `password`, `email`) VALUES('$unique_info[pic]', '$last_name', '$first_name', '$address', '$dled', '$unique_info[user_name]', '$password', '$unique_info[email]')";
 $result=mysqli_query($conn,$sql);
 $code= "reg_success";
 $message="Registration successful!";
 array_push($response,array("code"=>$code,"message"=>$message));
 echo json_encode($response);
 mysqli_close($conn);
?>