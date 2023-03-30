<html>
<head>
    <title>Cookiemanager - deleteAll</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu Cookiemanager">
</head>
</html>

<script type="text/javascript">
    if (confirm("Sollen wirklich ALLE Cookies gelöscht werden?")) {
        <?php
        if (isset($_COOKIE)) {
        foreach ($_COOKIE as $name => $value) {
                setcookie($name, '', 1);
                setcookie($name, '', 1, '/');
            }
        }
        header("Location:Cookiemanager.php");
        ?>
    } else {
        <?php
        header("Location:Cookiemanager.php");
        ?>
    }