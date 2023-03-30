<?php
require_once 'inc/classes/class.UserList.php';
require_once 'inc/classes/class.ValidableUser.php';
const IDS_LOGIN_NOT_NECESSARY = ["1"];
if (isset($_GET["id"])) {
    if ($_GET["id"] == 101) {
        session_destroy();
        header("Location:index.php");
        exit();
    } else {
        if (array_search($_GET["id"], IDS_LOGIN_NOT_NECESSARY, true) === false && !isset($_SESSION["loginUsername"])) {
            if (!isset($_SESSION["requested_id"])) {
                foreach ($_GET as $key => $value)
                    $_SESSION["requested_" . $key] = $value;
                header("Location:login.php");
            } else {
                $username = filter_input(INPUT_POST, "loginUsername", FILTER_SANITIZE_STRING);
                $password = filter_input(INPUT_POST, "loginPassword", FILTER_SANITIZE_STRING);
                if (!UserList::authUser($username, $password)) {
                    $_SESSION["loginError"] = true;
                    header('Location:login.php');
                } else {
                    $_SESSION["loginUsername"] = $username;
                    unset($_SESSION["loginError"]);
                    if ($_SESSION["requested_id"] == "100")
                        unset($_SESSION["requested_id"]);
                    $params = "";
                    foreach ($_SESSION as $key => $value)
                        if (strpos($key, "requested_") === 0) {
                            if (!empty($params))
                                $params .= "&";
                            else
                                $params = "?";
                            $params .= substr($key, 10) . "=" . $value;
                            unset($_SESSION[$key]);
                        }
                    header("Location:index.php" . $params);
                }
            }
            exit();
        }
    }
}
