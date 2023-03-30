<table style="height: 100%; width: 100%">
    <tr>
        <td width="90%">
            <h1>Frage <?= $quiz->getActualQuestionNumber() + 1 ?> von <?= $quiz::QUESTIONS_COUNT ?></h1>
        </td>
        <td style="text-align: center" height="100%">
            <?php
            if ($quiz->getActualQuestion()->getImageFilename() != null) {
                ?>
                <img src="images/<?php echo $quiz->getActualQuestion()->getImageFilename() ?>" width="100%">
            <?php } else { ?>
                <img src="images/placeholder.jpg" alt="placeholder" width="100%">
            <?php } ?>
        </td>
    </tr>
    <tr>
        <td width="90%">
            <p><?php echo $quiz->getActualQuestion()->getQuestionText() ?></p>
        </td>
        <td style="text-align: center">
            <?php echo $quiz->getActualQuestion()->getImageText(); ?>
        </td>
    </tr>
</table>

