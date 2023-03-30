<?php

class UserList
{

    public function addUser(User $user)
    {

        $con = new MySQLi("localhost", "root", "masterkey", "users");
        if ($con->connect_errno) {
        } else {
            $sql = "INSERT INTO users(uusername, upassword, umale, ubirthdate,urating, uimagetype, uimagesize, uimage) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            $stmt = $con->prepare($sql);
            if ($con->errno) {
                trigger_error($con->error, E_USER_WARNING);
            } else {
                $ret = false;
                if (isset($user)) {
                    $user->validate();
                    if (!$user->getError("username"))
                        if ($this->getUser($user->getUserName()) != null)
                            $user->setError("username", "Benutzername bereits vergeben");
                    if ($user->getErrors() == null) {
                        $stmt->bind_param("ssisdsib", $username, $password, $male, $birthDate, $rating, $imageType, $imageSize, $null);
                        $username = $user->getUsername();
                        $password = password_hash($user->getPassword(), PASSWORD_DEFAULT);
                        $male = $user->getMale();
                        $birthDate = $user->getBirthDate();
                        $rating = $user->getRating();
                        $imageType = $user->getImageType();
                        $imageSize = $user->getImageSize();
                        $null = null;
                        $stmt->send_long_data(7, $user->getImage());
                        $stmt->execute();
                        if ($con->errno) {
                            if ($con->errno == 1062)
                                $user->setError(
                                    "username", "Benutzername bereits vergeben");
                            else
                                trigger_error($con->error, E_USER_WARNING);
                        }
                        $stmt->close();
                        $ret = true;
                    }
                    $con->close();
                }
            }

        }
        return $ret;
    }

    public function updateUser($oldUsername, User $user)
    {
        $ret = false;
        if (isset($oldUsername) && $this->getUser($oldUsername) != null && isset($user)) {
            $user->validate();
            if ($user->getErrors() == null && $oldUsername != "admin") {
                if ($oldUsername != $user->getUserName()) {
                    if ($this->getUser($user->getUserName())) {
                        $user->setError("username", "Benutzername bereits vergeben");
                    } else {
                        $this->deleteUser($oldUsername);
                        $this->addUser($user);
                        $ret = true;
                    }
                } else {
                    $this->deleteUser($oldUsername);
                    $this->addUser($user);
                    $ret = true;
                }
            } else {
                $ret = false;
                if ($user->getUsername() != "admin")
                    $user->setError("username", "Admin darf nicht Benutzername ändern");
                else {
                    $con = new MySQLi("localhost", "root", "masterkey", "users");
                    if (!$con->connect_errno) {
                        $sql = "DELETE FROM users WHERE uusername = 'admin';";
                        $stmt = $con->prepare($sql);
                        if ($con->errno) {
                            trigger_error($con->error, E_USER_WARNING);
                        } else {
                            $stmt->execute();
                            $stmt->store_result();
                            $stmt->close();
                            $ret = true;
                        }
                    }
                    $con->close();
                    $ret = true;
                }
                $this->addUser($user);
            }
        }
        return $ret;
    }

    public function getUsers()
    {

        $con = new MySQLi("localhost", "root", "masterkey", "users");
        if (!$con->connect_errno) {
            $sql = "SELECT uusername, upassword, umale, ubirthdate, urating, uimagetype, uimagesize, uimage FROM users";
            $stmt = $con->prepare($sql);
            $stmt->execute();
            $stmt->store_result();
            $stmt->bind_result($username, $password, $male, $birthDate,
                $rating, $imageType, $imageSize, $image);
            if ($con->errno) {
                trigger_error($con->error, E_USER_WARNING);
            } else {
                while ($row = $stmt->fetch()) {
                    $user = new ValidableUser($username, null, null, $male,
                        $birthDate, $rating, $imageType, $imageSize, $image);
                    $users[] = $user;
                }
                $stmt->close();
                $con->close();
                if (isset($users))
                    return count($users) == 0 ? false : $users;
            }
        }
    }

    public function authUser($uusername, $upassword)
    {

        $ret = false;
        $con = new MySQLi("localhost", "root", "masterkey", "users");
        if (isset($uusername) && isset($upassword) && !$con->connect_errno) {
            $sql = "SELECT uusername, upassword, umale, ubirthdate, urating, uimagetype, uimagesize, uimage FROM users";
            $stmt = $con->prepare($sql);
            if ($con->errno) {
                trigger_error($con->error, E_USER_WARNING);
            } else {
                $stmt->bind_param("s", $username);
                $stmt->execute();
                $stmt->store_result();
                $stmt->bind_result($username, $password, $male, $birthDate,
                    $rating, $imageType, $imageSize, $image);
                while ($stmt->fetch()) {
                    $user = new ValidableUser($username, $password, null, $male,
                        $birthDate, $rating, $imageType, $imageSize, $image);
                    if ($uusername == $user->getUsername() && password_verify($upassword, $user->getPassword()))
                        $ret = true;
                }
                $stmt->close();
            }
            $con->close();
        }
        return $ret;
    }

    public function getUser($username)
    {
        $ret = null;
        $con = new MySQLi("localhost", "root", "masterkey", "users");
        if (isset($username) && !$con->connect_errno) {
            $sql = "SELECT uusername, upassword, umale, ubirthdate, urating,uimagetype, uimagesize, uimage FROM users WHERE uusername = ?;";
            $stmt = $con->prepare($sql);
            if ($con->errno) {
                trigger_error($con->error, E_USER_WARNING);
            } else {
                $stmt->bind_param("s", $username);
                $stmt->execute();
                $stmt->store_result();
                $stmt->bind_result($username, $password, $male, $birthDate,
                    $rating, $imageType, $imageSize, $image);
                if ($stmt->fetch()) {
                    $user = new ValidableUser($username, null, null, $male,
                        $birthDate, $rating, $imageType, $imageSize, $image);
                    $ret = $user;
                }
                $stmt->close();
            }
            $con->close();
        }
        return $ret;
    }

    public function deleteUser($username)
    {
        $ret = false;
        if (isset($username) && $this->getUser($username) != null) {
            if ($username == "admin") {
                echo '<script language="javascript">';
                echo 'alert("Admin kann nicht gelöscht werden!")';
                echo '</script>';
                return $ret;
            }
            $con = new MySQLi("localhost", "root", "masterkey", "users");
            if (!$con->connect_errno) {
                $sql = "DELETE FROM users WHERE uusername = ?;";
                $stmt = $con->prepare($sql);
                if ($con->errno) {
                    trigger_error($con->error, E_USER_WARNING);
                } else {
                    $stmt->bind_param("s", $username);
                    $stmt->execute();
                    $stmt->store_result();
                    $stmt->close();
                    $ret = true;
                }
            }
            $con->close();
        }
        if (isset($_SESSION["loginUsername"]) && $username == $_SESSION["loginUsername"])
            header("Location: index.php?id=101");
        return $ret;
    }

    public function getImage($username)
    {
        $ret = false;
        if (isset($username) && $this->getUser($username) != null) {
            $con = new MySQLi("localhost", "root", "masterkey", "users");
            if (!$con->connect_errno) {
                $sql = "SELECT uimage FROM users WHERE uusername = ?;";
                $stmt = $con->prepare($sql);
                if ($con->errno) {
                    trigger_error($con->error, E_USER_WARNING);
                } else {
                    $stmt->bind_param("s", $username);
                    $stmt->execute();
                    $stmt->store_result();
                    $stmt->bind_result($username, $password, $male, $birthDate,
                        $rating, $imageType, $imageSize, $image);
                    if ($stmt->fetch()) {
                        $user = new ValidableUser($username, null, null, $male,
                            $birthDate, $rating, $imageType, $imageSize, $image);
                        $ret = $user;
                    }
                    $stmt->close();
                }
                $con->close();
            }
        }
        return $ret;
    }
}