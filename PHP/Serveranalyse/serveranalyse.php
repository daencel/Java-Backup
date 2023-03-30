<?php
/*
 * ?a=1&b=2&c=3
 */
?>


<html>
<head>
    <title>Serveranalyse</title>
    <meta charset="utf-8">
    <meta name="author" content="Daniel Lechner">
    <meta name="description" content="Übung zu globale Variablen">
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

<?php
function multiexplode($delimiters, $data)
{
    $MakeReady = str_replace($delimiters, $delimiters[0], $data);
    $Return = explode($delimiters[0], $MakeReady);
    return $Return;
}

function getLanguages()
{
    $languagesString = $_SERVER['HTTP_ACCEPT_LANGUAGE'];
    $languages = multiexplode(array(",", ";"), $languagesString);
    foreach ($languages as $key => $one)
        if (strpos($one, '=') !== false) {
            unset($languages[$key]);
        }
    $languages = array_values($languages);
    return $languages;
}

function getLanguage()
{
    $languages = getLanguages();
    return $languages[0];
}

?>

<table>
    <tr>
        <th>$_SERVER</th>
        <th>
            <?php if (getLanguage() == "de")
                echo "Wert";
            elseif (getLanguage() == "it")
                echo "Valore";
            else
                echo "Value";
            ?>
        </th>
    </tr>
    <tr>
        <td>SERVER_NAME</td>
        <td><?php echo $_SERVER['SERVER_NAME'] ?></td>
    </tr>
    <tr>
        <td>SERVER_PORT</td>
        <td><?php echo $_SERVER['SERVER_PORT'] ?></td>
    </tr>
    <tr>
        <td>HTTP_ACCEPT_LANGUAGE</td>
        <td><?php echo $_SERVER['HTTP_ACCEPT_LANGUAGE'] ?></td>
    </tr>
    <tr>
        <td>PHP_SELF</td>
        <td><?php echo $_SERVER['PHP_SELF'] ?></td>
    </tr>
    <tr>
        <td>QUERY_STRING</td>
        <td><?php echo $_SERVER['QUERY_STRING'] ?></td>
    </tr>
    <tr>
        <td>REQUEST_URI</td>
        <td><?php echo $_SERVER['REQUEST_URI'] ?></td>
    </tr>
    <tr>
        <td style="font-weight: bold">$_REQUEST</td>
        <td>
            <table style="width: 100%">
                <?php
                $arr = array(
                    'a' => 1, 'b' => 2, 'c' => 3);
                ?>
                <tr>
                    <th>
                        <?php if (getLanguage() == "de")
                            echo "Parameter";
                        elseif (getLanguage() == "it")
                            echo "Parametro";
                        else
                            echo "Parameter";
                        ?>
                    </th>
                    <th>
                        <?php if (getLanguage() == "de")
                            echo "Wert";
                        elseif (getLanguage() == "it")
                            echo "Valore";
                        else
                            echo "Value";
                        ?>
                    </th>
                </tr>
               <?php foreach ($_REQUEST as $key => $value){
                   echo "<tr><td>$key</td><td>$value</td></tr>";
               }
               ?>
            </table>
        </td>
    </tr>
    <tr>
        <td style="font-weight: bold">
            <?php if (getLanguage() == "de")
                echo "Sprache";
            elseif (getLanguage() == "it")
                echo "Lingua";
            else
                echo "Language";
            ?>
        </td>
        <td>
            <table style="width: 100%">
                <tr>
                    <th>
                        <?php if (getLanguage() == "de")
                            echo "Parameter";
                        elseif (getLanguage() == "it")
                            echo "Parametro";
                        else
                            echo "Parameter";
                        ?>
                    </th>
                    <th>
                        <?php if (getLanguage() == "de")
                            echo "Wert";
                        elseif (getLanguage() == "it")
                            echo "Valore";
                        else
                            echo "Value";
                        ?>
                    </th>
                </tr>
                <?php
                $languages = getLanguages();
                for ($i = 0; $i < sizeof($languages); $i++) {
                    echo "<tr><td>" . $i . "</td><td>" . $languages[$i] . "</td></tr>";
                }
                ?>
            </table>
        </td>
    </tr>
</table>
