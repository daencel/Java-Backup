<?php

require_once 'inc/classes/class.UserList.php';
require_once 'inc/classes/class.ValidableUser.php';

const INACTIVITY_SECONDS = 10 * 60;

session_start();

require_once 'scripts/scr.auth.php';

if (isset($_SESSION["lastactivity"]) && (time() - $_SESSION["lastactivity"]) > INACTIVITY_SECONDS) {
    session_destroy();
    session_start();
} else {
    session_regenerate_id(true);
    $_SESSION["lastactivity"] = time();
}
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Benutzerverwaltung Daniel Lechner</title>
    <link rel="stylesheet" type="text/css" href="inc/css/usermanagement.css"/>
</head>
<body>
<a href="index.php?id=1">Benutzerliste</a>
<a href="index.php?id=2">Neuer Benutzer</a>


<?php
if (isset($_SESSION["loginUsername"])) {
    echo "<a href=\"index.php?id=101\">Logout</a><p style='display: inline'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sie sind eingeloggt als Benutzer <b>" . $_SESSION["loginUsername"] . "</b></p>";
} else
    echo "<a href=\"index.php?id=100\">Login</a><p style='display: inline'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sie sind nicht eingeloggt</p>";
if (!isset($_SESSION["userList"])) {
    $_SESSION["userList"] = new UserList();
    $userList = $_SESSION["userList"];
    $userList->addUser(new ValidableUser("admin", "admin", "admin", true, "2000-01-01", 5, null, null, null));
}
$userList = $_SESSION["userList"];
if (!isset($_SESSION["user"]))
    $_SESSION["user"] = new ValidableUser();
$user = $_SESSION["user"];
if (!isset($_GET["id"]) || $_GET["id"] == "1") {
    require_once 'scripts/scr.userList.php';
} elseif ($_GET["id"] == "2") {
    $user = new ValidableUser();
    $_SESSION["user"] = $user;
    require_once 'scripts/scr.userForm.php';
} elseif ($_GET["id"] == "20") {
    $user->setUsername(filter_input(INPUT_POST, "username",
        FILTER_SANITIZE_STRING));
    $user->setPassword(filter_input(INPUT_POST, "password",
        FILTER_SANITIZE_STRING));
    $user->setPasswordRepeat(filter_input(INPUT_POST,
        "passwordRepeat", FILTER_SANITIZE_STRING));
    $user->setMale(filter_input(INPUT_POST, "gender",
        FILTER_VALIDATE_BOOLEAN));
    $user->setBirthDate(filter_input(INPUT_POST, "birthday",
        FILTER_SANITIZE_STRING));
    $user->setRating(filter_input(INPUT_POST, "rating",
        FILTER_VALIDATE_FLOAT));
    $user->setImageFromSuperglobal($_FILES["image"]);
    $user->validate();
    if ($user->getErrors() != null || !$userList->addUser($user))
        require_once 'scripts/scr.userForm.php';
    else
        require_once 'scripts/scr.userList.php';

} elseif ($_GET["id"] == "3") {
    if (!isset($_GET["username"]) || strlen($_GET["username"]) == 0) {
        require_once 'scripts/scr.UserList.php';
    } else {
        $username = filter_input(INPUT_GET, "username", FILTER_SANITIZE_STRING);
        $user = $userList->getUser($username);
        if (!$user)
            require_once 'scripts/scr.UserList.php';
        else {
            $_SESSION["user"] = $user;
            $_SESSION["oldUsername"] = $user->getUsername();
            require_once 'scripts/scr.UserForm.php';
        }
    }
} elseif ($_GET["id"] == "30") {
    if (isset($_POST["deleteImage"])) {
        $user->setUsername(filter_input(INPUT_POST, "username",
            FILTER_SANITIZE_STRING));
        $user->setPassword(filter_input(INPUT_POST, "password",
            FILTER_SANITIZE_STRING));
        $user->setPasswordRepeat(filter_input(INPUT_POST,
            "passwordRepeat", FILTER_SANITIZE_STRING));
        $user->setMale(filter_input(INPUT_POST, "gender",
            FILTER_VALIDATE_BOOLEAN));
        $user->setBirthDate(filter_input(INPUT_POST, "birthday",
            FILTER_SANITIZE_STRING));
        $user->setRating(filter_input(INPUT_POST, "rating",
            FILTER_VALIDATE_FLOAT));
        $user->deleteImage();

        require_once 'scripts/scr.UserForm.php';
    } else {
        $user->setUsername(filter_input(INPUT_POST, "username",
            FILTER_SANITIZE_STRING));
        $user->setPassword(filter_input(INPUT_POST, "password",
            FILTER_SANITIZE_STRING));
        $user->setPasswordRepeat(filter_input(INPUT_POST,
            "passwordRepeat", FILTER_SANITIZE_STRING));
        $user->setMale(filter_input(INPUT_POST, "gender",
            FILTER_VALIDATE_BOOLEAN));
        $user->setBirthDate(filter_input(INPUT_POST, "birthday",
            FILTER_SANITIZE_STRING));
        $user->setRating(filter_input(INPUT_POST, "rating",
            FILTER_VALIDATE_FLOAT));
        $user->setImageFromSuperglobal($_FILES["image"]);
        $user->validate();
        if ($user->getErrors() != null || !$userList->updateUser($_SESSION["oldUsername"], $user))
            require_once 'scripts/scr.userForm.php';
        else
            require_once 'scripts/scr.userList.php';
    }
} elseif ($_GET["id"] = "4") {
    if (isset($_GET["username"]) && strlen($_GET["username"]) > 0) {
        $username = filter_input(
            INPUT_GET, "username", FILTER_SANITIZE_STRING);
        if ($userList->deleteUser($username)) {
            unset($_SESSION["user"]);
            unset($user);
        }
    }
    require_once 'scripts/scr.userList.php';

}
?>
</body>
</html>