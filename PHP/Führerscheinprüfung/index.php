<?php
require_once 'inc/classes/class.Quiz.php';
require_once 'inc/classes/class.Answer.php';
require_once 'inc/classes/class.Question.php';

session_start();
if (!isset($_SESSION["quiz"]))
    $_SESSION["quiz"] = new Quiz();
$quiz = $_SESSION["quiz"];

require_once 'scripts/scr.auth.php';
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Fahrschulquiz</title>
    <link rel="stylesheet" type="text/css" href="inc/css/usermanagement.css"/>
    <script type="text/javascript">
        function questionLink(questionNumber) {
            document.questionForm.questionNumber.value = questionNumber;
            document.questionForm.submit();
            return false;
        }
    </script>
</head>
<body>


<table width="100%">
    <td width="80%">
        <h1 style="display: inline">Fahrschulquiz</h1>
    </td>
    <td align="right">
        <?php
        if (isset($_SESSION["loginUsername"])) {
            echo "<p style='display: inline'>Sie sind eingeloggt als Benutzer <b>" . $_SESSION["loginUsername"] . "</b>  </p><a href=\"index.php?id=101\">Logout</a></td></table>";
        } else { ?>
        <p style="display: inline" align="right">Sie sind nicht eingeloggt </p><a href="index.php?id=100">Login</a></td>
</table>
<p style="color:red" align="center">Ihre Antworten werden nicht abgespeichert. Loggen Sie sich zuerst ein,
    wenn Sie das Quiz
    durchführen und auswerten lassen möchten!</p>
<?php
}
?>
<form name="questionForm" method="post" action="index.php">
    <input type="hidden" name="questionNumber">
    <?php
    for ($i = 0; $i < Quiz::ANSWERS_COUNT; $i++) {
        if (isset($_POST["answer$i"]) && isset($_SESSION["loginUsername"])) {
            $quiz->getActualQuestion()->getAnswers()[$i]->setAnswerSelected($_POST["answer$i"] == "true");
        }
    }
    if (isset($_POST["questionNumber"]) && $_POST["questionNumber"] != "") {
        $quiz->setActualQuestionNumber($_POST["questionNumber"]);
        require_once 'scripts/scr.Navigation.php';
        require_once 'scripts/scr.Question.php';
        require_once 'scripts/scr.Answer.php';
    } else if (isset($_POST["id"]) && $_POST["id"] == "Vorherige Frage") {
        $quiz->previousQuestion();
        require_once 'scripts/scr.Navigation.php';
        require_once 'scripts/scr.Question.php';
        require_once 'scripts/scr.Answer.php';
    } else if (isset($_POST["id"]) && $_POST["id"] == "Nächste Frage") {
        $quiz->nextQuestion();
        require_once 'scripts/scr.Navigation.php';
        require_once 'scripts/scr.Question.php';
        require_once 'scripts/scr.Answer.php';
    } else if (isset($_POST["id"]) && $_POST["id"] == "Quiz fertigstellen") {
        $quiz->setCompleted(true);
        require_once 'scripts/scr.Evaluate.php';
    } else if (isset($_POST["id"]) && $_POST["id"] == "Neues Quiz erstellen") {
        $_SESSION["quiz"] = new Quiz();
        $quiz = $_SESSION["quiz"];
        require_once 'scripts/scr.Navigation.php';
        require_once 'scripts/scr.Question.php';
        require_once 'scripts/scr.Answer.php';
    } else {
        require_once 'scripts/scr.Navigation.php';
        require_once 'scripts/scr.Question.php';
        require_once 'scripts/scr.Answer.php';
    }
    ?>
</form>
</body>