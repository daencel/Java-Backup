<?php
require_once 'class.User.php';
require_once 'class.DateUtility.php';

class ValidableUser extends User
{

    function validate()
    {
        if (!isset($this->username) || strlen($this->username) == 0)
            $this->errors["username"] = "Der Benutzername muss eingegeben werden";
        else
            unset($this->errors["username"]);

        if (isset($this->password) || isset($this->passwordRepeat)) {
            unset($this->errors["password"]);
            unset($this->errors["passwordRepeat"]);

            if (strlen($this->password) == 0)
                $this->errors["password"] = "Das erste Passwort muss eingegeben werden";
            else
                unset($this->errors["password"]);

            if ($this->password != $this->passwordRepeat)
                $this->errors["passwordRepeat"] = "Das erste und zweite Passwort sind nicht identisch";
            else
                unset($this->errors["passwordRepeat"]);
        } else {
            $this->errors["password"] = "Das erste und zweite Passwort müssen eingegeben werden";
            $this->errors["passwordRepeat"] = "Das erste und zweite Passwort müssen eingegeben werden";
        }

        if (!isset($this->rating) || $this->rating == false || $this->rating < 1 || $this->rating > 5)
            $this->errors["rating"] = "Die Bewertung muss eingegeben werden";
        elseif ($this->rating < 1 || $this->rating > 5) {
            $this->errors["rating"] = "Die Bewertung muss zwischen 1 und 5 liegen";
        } else
            unset($this->errors["rating"]);

        if (!isset($this->birthDate) || $this->birthDate == null) {
            $this->errors["birthday"] = "Das Geburtsdatum muss eingegeben werden";
        } else {
            $date = new DateUtility($this->birthDate);
            if ($date->validate() == false)
                $this->errors["birthday"] = "Das Geburtsdatum darf nicht in der Zukunft liegen";
            else
                unset($this->errors["birthday"]);
        }

        if (isset($_POST["gender"]) && $_POST["gender"] == "Weiblich")
            $this->setMale(false);
        else
            $this->setMale(true);

        if (isset($this->image) && $this->image != null) {
            if ($this->imageSize > User::MAX_IMAGE_SIZE)
                $this->errors["image"] = "Das Profilbild ist zu groß";
            else {
                unset($this->errors["image"]);
                if ($this->imageType != "image/png" && $this->imageType != "image/jpeg")
                    $this->errors["image"] = "Das Profilbild darf nur im JPEG und PNG Format gespeichert werden";
                else
                    unset($this->errors["image"]);
            }
        } else
            unset($this->errors["image"]);

    }

    function getError($input)
    {

        switch ($input) {
            case "username":
                if (isset($this->errors["username"]))
                    return $this->errors["username"];
                else
                    return "";
                break;
            case "password":
                if (isset($this->errors["password"]))
                    return $this->errors["password"];
                else
                    return "";
                break;
            case "passwordRepeat":
                if (isset($this->errors["passwordRepeat"]))
                    return $this->errors["passwordRepeat"];
                else
                    return "";
                break;
            case "gender":
                if (isset($this->errors["gender"]))
                    return $this->errors["gender"];
                else
                    return "";
                break;
            case "birthday":
                if (isset($this->errors["birthday"]))
                    return $this->errors["birthday"];
                else
                    return "";
                break;
            case "rating":
                if (isset($this->errors["rating"]))
                    return $this->errors["rating"];
                else
                    return "";
                break;
            case "image":
                if (isset($this->errors["image"]))
                    return $this->errors["image"];
                else
                    return "";
                break;
        }

    }

    function unsetError($errorType)
    {
        unset($this->errors[$errorType]);
    }

    function setError($errorType, $error)
    {
        $this->errors[$errorType] = $error;
    }

    function getErrors()
    {
        return $this->errors;
    }
}