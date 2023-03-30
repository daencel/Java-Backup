<script type="text/javascript">
    function conf() {
        return confirm("Sind Sie sicher, dass Sie das Quiz abgeben wollen?");
    }
</script>
<table width="100%">
    <tr>
        <td rowspan="2">
            <input type="submit" value="Quiz fertigstellen" name="id"
                   onclick="return conf()" <?= (isset($_SESSION["loginUsername"])) ? "" : "disabled" ?>>
        </td>
        <td width="50%" align="center">
            Beantwortet:
            <?php $questions = $quiz->getQuestions();
            if ($quiz->getNumberAnsweredQuestions() == 0) { ?>
                Keine beantworteten Fragen vorhanden
            <?php } else {
                for ($i = 0; $i < count($questions); $i++) {
                    if ($questions[$i]->getAnswered()) { ?>
                        <a href="index.php?question=<? $i ?>"
                           onclick="return questionLink(<?= $i ?>)"><?= $i + 1 ?></a>&nbsp
                        <?php
                    }
                }
            }
            ?>
        </td>
        <td rowspan="2" width="13%" align="right">
            <input type="submit" value="Vorherige Frage"
                   name="id" <?php if (!$quiz->getHasPreviousQuestion()) echo 'disabled="disabled"'; ?>>
        </td>
        <td rowspan="2" width="13%" align="left">
            <input type="submit" value="Nächste Frage"
                   name="id" <?php if (!$quiz->getHasNextQuestion()) echo 'disabled="disabled"'; ?>>
        </td>
    </tr>
    <tr>
        <td align="center">
            Nicht beantwortet:
            <?php $questions = $quiz->getQuestions();
            if ($quiz->getNumberUnansweredQuestions() == 0) { ?>
                Keine nicht beantworteten Fragen vorhanden.
            <?php } else {
                for ($i = 0; $i < count($questions); $i++) {
                    if (!$questions[$i]->getAnswered()) { ?>
                        <a href="index.php?question=<? $i ?>"
                           onclick="return questionLink(<?= $i ?>)"><?= $i + 1 ?></a>&nbsp
                        <?php
                    }
                }
            }
            ?>
        </td>
    </tr>
</table>
<hr>