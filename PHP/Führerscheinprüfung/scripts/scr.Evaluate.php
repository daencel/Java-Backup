<h3>Auswertung</h3>
<?php
$point = 0;
for ($i = 0; $i < count($quiz->getQuestions()); $i++) {
    $question = $quiz->getQuestions()[$i];
    for ($j = 0; $j < count($question->getAnswers()); $j++) {
        $point += $question->getAnswers()[$j]->getPoints();
    }
}
$percent = 0;
if ($point != 0)
    $percent = $point / ($quiz::QUESTIONS_COUNT * $quiz::ANSWERS_COUNT) * 100;
$percent = round($percent, 2)

?>

Von <?= $quiz::QUESTIONS_COUNT * $quiz::ANSWERS_COUNT ?> möglichen Punkten haben Sie <?= $point ?> Punkte
erreicht. Das sind <?= $percent ?> Prozent. Sie haben somit den
Test <?php if ($percent < $quiz::MINIMUM_PERCENT) echo "nicht " ?>
bestanden.

<form action="index.php" name="submitForm">
    <input align="left" type="submit" value="Neues Quiz starten" onclick="<?php session_destroy() ?>">
</form>
<?php
for ($i = 0; $i < count($quiz->getQuestions()); $i++) { ?>
    <hr>
    <table style="height: 100%; width: 100%">
        <tr>
            <td width="90%">
                <h1>Frage <?= $i + 1 ?> von <?= $quiz::QUESTIONS_COUNT ?></h1>
            </td>
            <td style="text-align: center" height="100%">
                <?php
                if ($quiz->getQuestions()[$i]->getImageFilename() != null) {
                    ?>
                    <img src="images/<?php echo $quiz->getQuestions()[$i]->getImageFilename() ?>" width="100%">
                <?php } else { ?>
                    <img src="images/placeholder.jpg" alt="placeholder" width="100%">
                <?php } ?>
            </td>
        </tr>
        <tr>
            <td width="90%">
                <p><?php echo $quiz->getQuestions()[$i]->getQuestionText() ?></p>
            </td>
            <td style="text-align: center">
                <?php echo $quiz->getQuestions()[$i]->getImageText(); ?>
            </td>
        </tr>
    </table>
    <table style="height: 100%; width: 100%">
        <tr>
            <th width="80%" rowspan="2">
                Antworten
            </th>
            <th colspan="2">
                Ihre Antworten
            </th>
            <th colspan="2">
                Richtige Antworten
            </th>
            <th rowspan="2">
                Punkte
            </th>
        </tr>
        <tr>
            <th>
                Richtig
            </th>
            <th>
                Falsch
            </th>
            <th>
                Richtig
            </th>
            <th>
                Falsch
            </th>
        </tr>
        <?php
        $answers = $quiz->getQuestions()[$i]->getAnswers();
        for ($j = 0; $j < count($answers); $j++) { ?>
            <tr>
                <td width="80%">
                    <?= $answers[$j]->getAnswerText() ?>
                </td>
                <td align="center">
                    <input type="radio"<?= ($answers[$j]->getAnswered() && $answers[$j]->getAnswerSelected()) ? "checked" : "" ?>
                           disabled>

                </td>
                <td align="center">
                    <input type="radio" <?= ($answers[$j]->getAnswered() && !$answers[$j]->getAnswerSelected()) ? "checked" : "" ?>
                           disabled>
                </td>
                <td align="center">
                    <input type="radio"<?= ($answers[$j]->getCorrect() == 1) ? "checked" : "" ?> disabled>
                </td>
                <td align="center">
                    <input type="radio" <?= ($answers[$j]->getCorrect() == 0) ? "checked" : "" ?> disabled>
                </td>
                <td>
                    <?= $answers[$j]->getPoints() ?>

                </td>
            </tr>
            <?php
        }
        ?>
    </table>
    <?php
}
?>
<script>
    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
</script>
