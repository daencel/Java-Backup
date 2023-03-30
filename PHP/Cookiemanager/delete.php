<script type="text/javascript">

    confirm("test");

</script>

<html>
<head>
    <title>Cookiemanager - delete</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu Cookiemanager">
</head>
</html>

<?php
if (isset($_COOKIE[$_GET["name"]])) {
    setcookie($_GET["name"], false);
    unset($_COOKIE[$_GET["name"]]);
    header("Location:Cookiemanager.php");
} else {
    echo "<h1>Cookie nicht auffindbar</h1>";
    echo "<p><a href=\"Cookiemanager.php\">Startseite</a>";
}
