<html>
<head>
    <title>Cookiemanager</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu Cookiemanager">
    <style>
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>


<table style="width: 70%;border: 1px solid black">
    <th style="font-size: 60px">Cookiemanager</th>
    <td style="width: 50%">
        <table style="width: 100%;border: 1px solid black;border-collapse: collapse">
            <td style="text-align: right;border: 1px solid black"> <?php echo sizeof($_COOKIE) ?> Cookies gesetzt</td>
            <tr>
                <td style="text-align: right;border: 1px solid black">
                    <?php if (isset($_COOKIE["LETZTERZUGRIFF"]))
                        echo "Ihr letzter Zugriff erfolgte am: " . $_COOKIE["LETZTERZUGRIFF"];
                    else
                        echo "Zum ersten Mal auf der Seite!";
                    $date = new DateTime();
                    setcookie("LETZTERZUGRIFF", $date->format("d.m.Y H:i:s"), time() + 7 * 24 * 60 * 60);
                    ?>
                </td>
            </tr>
        </table>
    </td>
</table>
<table style="width: 70%;border: 1px solid black">
    <tr>
        <td style="width: 20%">
            <table>
                <tr>
                    <td><a href="list.php">Liste</a></td>
                </tr>
                <tr>
                    <td><a href="new.php">Neu</a></td>
                </tr>
                <tr>
                    <td><a href="deleteAll.php"
                           onclick="return confirm('Sollen wirklich ALLE Cookies gelöscht werden?')">Alle löschen</a>
                    </td>
                </tr>
            </table>
        </td>
        <td style="width: 70%">
            <table style="width: 100%;border: 1px solid black;border-collapse: collapse">
                <table style="border-collapse: collapse; border: 1px solid black; width: 100%">
                    <?php
                    foreach ($_COOKIE as $k => $v) {
                        echo '<tr><td style="text-align: left;border: 1px solid black">' . $k . '</td><td style="text-align: left;border: 1px solid black">' . $v . '</td><td style="text-align: left;border: 1px solid black"><a href=delete.php?name=' . $k . ' onclick="return confirm(\'Soll das ausgewählte Cookie wirklich gelöscht werden?\')">Löschen</a></td></tr>';
                    }
                    ?>
                </table>
            </table>
        </td>
    </tr>
</table>