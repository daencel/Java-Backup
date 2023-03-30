<form method="get" action="loginformAction.php">
    <label>Benutzername:</label>
    <input type="text" name="username">
    <label>Passwort:</label>
    <input type="password" name="password">
    <input type="submit" name="submit" value="Login">
</form>

<?php
if ($_GET["username"]=="sepp" && $_GET["password"]=="verycomplex")
    header("Location:index.php");
else {
    echo "<p>Benutzername und/oder Passwort nicht korrekt</p>";
    echo "<p><a href='loginform.php'>Zurück</a></p>";
}
?>