<html>
<head>
    <title>Cookiemanager - Action</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu Cookiemanager">
</head>
</html>

<?php
if (!isset($_POST["cookieName"]) ||
    !isset($_POST["cookieWert"]) || !isset($_POST["cookieTime"])) {
    ?>
    <h1>Interner Fehler</h1>
    <p>Die Werte konnten nicht gefunden werden </p>
    <p><a href="Cookiemanager.php">Startseite</a>
    <?php
} else {
    if ($_POST["cookieName"] == "" || $_POST["cookieWert"] == "" || $_POST["cookieTime"] == "") {
        echo "<h1>Neues Cookie: Fehler</h1>";
        if ($_POST["cookieName"] == "")
            echo "<p>Name des Cookie wurde nicht gesetzt</p>";
        if ($_POST["cookieWert"] == "")
            echo "<p>Wert des Cookie wurde nicht gesetzt</p>";
        if ($_POST["cookieTime"] == "")
            echo "<p>Lebensdauer des Cookie wurde nicht gesetzt</p>";
        echo "<a href=\"javascript:window.history.back();\">zurück</a>";
    } else {
        if (intval($_POST["cookieTime"]) != 0)
            setcookie($_POST["cookieName"], $_POST["cookieWert"], (intval($_POST["cookieTime"])) + time());
        else
            setcookie($_POST["cookieName"], $_POST["cookieWert"]);
        $_POST["cookieName"] = "";
        $_POST["cookieTime"] = "0";
        $_POST["cookieWert"] = "";
        header("Location:Cookiemanager.php");
    }
}
?>