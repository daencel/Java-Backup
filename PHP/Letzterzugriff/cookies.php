<html>
<head>
    <title>Cookieuebung</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu Cookies">
</head>
</html>

<?php

if (isset($_COOKIE["LETZTERZUGRIFF1"]))
    echo "<h1>Ihr letzter Zugriff erfolgte " . $_COOKIE["LETZTERZUGRIFF1"] . "</h1>";
else
    echo "<h1>Zum ersten Mal auf der Seite!</h1>";

$date = new DateTime();
setcookie("LETZTERZUGRIFF1", $date->format("d.m.Y H:i:s"), time() + 7 * 24 * 60 * 60);