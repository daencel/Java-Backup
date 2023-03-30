<?php
$i = 3;
$j = "10 Stück";
echo $i + $j; 
// Ausgabe von 13 mit Notice
// In Datei php.ini durch error_reporting=E_ALL & ~E_NOTICE ausschaltbar
echo "<br>";

echo $i . $j; 
// Ausgabe von 310 Stück
echo "<br>";

echo "$i Stück"; 
// Ausgabe von 3 Stück
echo "<br>";

$j = "3";
if ($i == $j) 
  echo "Inhalte gleich";
// Ausgabe von "Inhalte gleich"
echo "<br>";

if ($i === $j) 
  echo "Auch Typen gleich";
// Keine Ausgabe
echo "<br>";

$y = 3.54;
if (is_double($y))
  echo "\$y " . gettype($y); 
// Ausgabe von "$y double"
echo "<br>";

echo gettype($_GET["z"]); 
// Ausgabe von string
echo "<br>";

echo (int) $y; 
// Ausgabe von 3
echo "<br>";

$k = &$i; // Referenzvariable
$k = 7;
echo $i; 
// Ausgabe von 7
echo "<br>";

$varvar = "i";
echo $$varvar; 
// Ausgabe von 7
echo "<br>";

// mb_strlen ermittelt die Zeichenlänge eines Unicode-Strings 
$varmeth = "mb_strlen";
echo $varmeth($$varvar . " Stück"); 
// Ausgabe von 7
echo "<br>";

const KONSTANTE = "Wert";
echo KONSTANTE;
// Ausgabe von Wert
?>
