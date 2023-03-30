<hr>
<table style="height: 100%; width: 100%">
    <tr>
        <th width="90%">
            Antworten
        </th>
        <th>
            Richtig
        </th>
        <th>
            Falsch
        </th>
    </tr>
    <?php
    $answers = $quiz->getActualQuestion()->getAnswers();
    for ($i = 0; $i < count($answers); $i++) { ?>
        <tr>
            <td>
                <?= $answers[$i]->getAnswerText()?>
            </td>
            <td>
                <input type="radio" name="answer<?= $i ?>"
                       value="true" <?= $answers[$i]->getAnswered() && $answers[$i]->getAnswerSelected() ? "checked" : "" ?>>
            </td>
            <td>
                <input type="radio" name="answer<?= $i ?>"
                       value="false" <?= $answers[$i]->getAnswered() && !$answers[$i]->getAnswerSelected() ? "checked" : "" ?>>
            </td>
        </tr>
        <?php
    }
    ?>
</table>