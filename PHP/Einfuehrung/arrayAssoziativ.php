<pre>
<?php
$assArray = array("Bozen" => 10000, "Meran" => 40000,
  "Brixen" => 19000, 6 => 25000);
$assArray["Sterzing"] = 5700;
unset($assArray[6]);
var_dump($assArray);
echo "<br>";

ksort($assArray);
foreach ($assArray as $key => $value)
  echo "$key ... $value<br>";
echo "<br>";
  
foreach ($assArray as $value)
  echo "$value<br>";
echo "<br>";
  
$assArray = ["heiß" => 30, "angenehm" => 20, "kalt" => 10];
foreach($assArray as $key => &$value)	// Achtung &
  $value+=5;
unset($value) ;
var_dump($assArray);
?>
</pre>

<!-- 
  $value wird normalerweise als Wert-Parameter übergeben. 
  Soll er geändert werden, dann muss er als Referenz übergeben 
  werden
  WICHTIG: Nach Verwendung von $value muss Variable gelöscht werden 
  (siehe https://stackoverflow.com/questions/3307409/php-pass-by-reference-in-foreach)
-->
