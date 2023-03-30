<pre>
<?php
$numArray = array();
$numArray[3] = 3;
$numArray[2] = 3.15;
$numArray[] = "Hallo";
var_dump($numArray);
echo "<br>";

foreach ($numArray as $key => $value)	 // Achtung Eingabereihenfolge
  echo "$key => $value<br>";	         // ist entscheidend!!!
echo "<br>";
  
$numArray = [2, 3.3, "Leute"];
var_dump($numArray);
echo "<br>";

$numArray = null;
$numArray[7] = "Wie geht`s";
$numArray[] = array("7", 9.0, "dir");
$numArray[8][1] = null;	// löscht nur Inhalt an Stelle 1
unset($numArray[8][1]);	// löscht gesamten Eintrag mit Index 1
var_dump($numArray);
echo "<br>";

echo count($numArray);
echo "<br>";

echo $numArray[100];	
// Keine Ausgabe, würde Notice werfen, in php.ini ausgeschaltet
echo "<br>";

$a = [1, 2, 3];
$b = $a;
$b[0] = 10;
echo $a[0];	// Ausgabe von 1
?>
</pre>
