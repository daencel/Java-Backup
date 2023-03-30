<?php
session_start();
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authentifizierung</title>
    <link rel="stylesheet" type="text/css" href="inc/css/usermanagement.css">
</head>
<body>
<h2>Authentifizierung</h2>
<?php
if (isset($_SESSION["loginError"])) { ?>
    <p class="error">
        Benutzername und Password stimmen nicht überein
    </p>
<?php } ?>
<form method="post" action="index.php?id=100">
    <table>
        <tr>
            <td>
                <label>Benutzername:</label>
            </td>
            <td>
                <input type="text" name="loginUsername">
            </td>
        </tr>
        <tr>
            <td>
                <label>Passwort:</label>
            </td>
            <td>
                <input type="password" name="loginPassword">
            </td>
        </tr>
        <tr>
            <td>
                <a href="index.php">Zurück</a>
            </td>
            <td>
                <input type="submit" name="submit" value="Einloggen">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
