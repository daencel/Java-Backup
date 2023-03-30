<html>
<head>
    <title>Cookiemanager - new</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu Cookiemanager">
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: left;
        }

        th {
            text-align: center;
        }
    </style>
</head>
<body>

<h1>Neues Cookie</h1>

<form method="post" action="newAction.php">
    <table width="40%">
        <tr>
            <th><label>Name:</label></th>
            <td><input type="text" name="cookieName" style="width: 100%"><br></td>
        </tr>
        <tr>
            <th><label>Wert:</label></th>
            <td><input type="text" name="cookieWert" style="width: 100%"><br></td>
        </tr>
        <tr>
            <th><label>Lebensdauer:</label></th>
            <td class="select">
                <label for="time" style="width: 100%"></label>
                <select name="cookieTime" style="width: 100%">
                    <option value="0">Stirbt beim Schließen des Browser</option>
                    <option value="30">30 Sekunden</option>
                    <option value="60">1 Minute</option>
                    <option value="300">5 Minuten</option>
                    <option value="3600">1 Stunde</option>
                    <option value="604800">7 Tage</option>
                </select>
            </td>
        </tr>
        <tr>
            <th></th>
            <td>
                <input type="submit" name="submit"
                       value="Erstellen" style="float: left; display: inline-block;width: 50%">
                <input type="reset" name="abort"
                       value="Zurücksetzten" style="float: right; display: inline-block;width: 50%">
            </td>
        </tr>
    </table>
</form>