<?php
require_once '../inc/classes/class.ValidableUser.php';
require_once '../inc/classes/class.UserList.php';
session_start();

if (isset($_GET["username"][0])) {
    $username = $_GET["username"];
    $userList = $_SESSION["userList"];
    $user = $userList->getUser($username);
    header("content-type: image/jpeg");
    echo $user->getImage();
}