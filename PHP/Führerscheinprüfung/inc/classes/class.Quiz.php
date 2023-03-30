<?php
require_once 'class.Answer.php';
require_once 'class.Question.php';

class Quiz
{
    /*
     * Konstanten
     */
    const QUESTIONS_COUNT = 10;
    const ANSWERS_COUNT = 3;
    const MINIMUM_PERCENT = 86.6666;

    /**
     * Hier wird die Fragenummer der aktuellen Frage gemerkt. Die Fragen werden
     * mit 0 beginnend nummeriert
     */
    private $actualQuestionNumber = 0;
    /**
     * Hier wird gemerkt, ob das Quiz fertiggestellt bzw. abgegeben wurde
     */
    private $completed = false;
    /**
     * Enthält die für das Quiz ausgewählten Fragen
     */
    private $questions = null;

    /*
     * Konstruktoren
     */
    /**
     * Legt Quiz an und holt sich dabei Fragen zufällig aus der Datenbank
     * und schreibt diese in das Fragen-Array hinein
     */
    public function __construct()
    {

        $con = new MySQLi("localhost", "root", "masterkey", "quiz");
        if (!$con->connect_errno) {
            $sql = "SELECT * FROM fragen ORDER BY RAND() LIMIT " . self::QUESTIONS_COUNT . ";";
            $stmt = $con->prepare($sql);
            $stmt->execute();
            $stmt->store_result();
            $stmt->bind_result($fragenummer, $fragetext, $bild);
            if ($con->errno) {
                trigger_error($con->error, E_USER_WARNING);
            } else {
                $temp = 0;
                while ($stmt->fetch()) {
                    $sql2 = "SELECT antworttext, richtig FROM `antworten` WHERE fragenummer = ? ORDER BY RAND() LIMIT 3";
                    $stmt2 = $con->prepare($sql2);
                    $stmt2->bind_param("s", $fragenummer);
                    $stmt2->execute();
                    $stmt2->store_result();
                    $stmt2->bind_result($antworttext, $richtig);
                    while ($stmt2->fetch()) {
                        $uanswers[] = new Answer($antworttext, $richtig);
                    }
                    $ques = new Question($fragetext, $bild, $uanswers);
                    $this->questions[$temp++] = $ques;
                    unset($uanswers);
                }
            }
        }
    }

    /*
     * Getter- und Settermethoden
     */
    /**
     * Liefert die Nummer der aktuellen Frage zurück
     * @return
     */
    public
    function getActualQuestionNumber()
    {
        return $this->actualQuestionNumber;
    }

    /**
     * Setzt die Nummer der aktuellen Frage
     * @param
     */
    public
    function setActualQuestionNumber($actualQuestionNumber)
    {
        if ($actualQuestionNumber <= self::QUESTIONS_COUNT - 1 && $actualQuestionNumber >= 0)
            $this->actualQuestionNumber = $actualQuestionNumber;

    }

    /**
     * Springt zur nächsten Frage die zur aktuellen Frage wird
     * @return
     */
    public
    function nextQuestion()
    {
        if ($this->getHasNextQuestion()) {
            $this->setActualQuestionNumber($this->getActualQuestionNumber() + 1);
        }
        return $this->getActualQuestionNumber();
    }

    /**
     * Springt zur vorigen Frage die zur aktuellen Frage wird
     * @return
     */
    public
    function previousQuestion()
    {
        if ($this->getHasPreviousQuestion()) {
            $this->setActualQuestionNumber($this->actualQuestionNumber - 1);
        }
        return $this->getActualQuestionNumber();
    }

    /**
     * Liefert die Anzahl der Fragen des Quiz zurück
     * @return
     */
    public
    function getNumberQuestions()
    {
        return self::QUESTIONS_COUNT;
    }

    /**
     * Liefert die aktuelle Frage zurück
     * @return
     */
    public
    function getActualQuestion()
    {
        return $this->questions[$this->actualQuestionNumber];
    }

    /**
     * Liefert true zurück, falls nach der aktuellen Frage noch eine weitere
     * Frage im Quiz existiert
     * @return
     */
    public
    function getHasNextQuestion()
    {
        return $this->getActualQuestionNumber() < self::QUESTIONS_COUNT - 1;
    }

    /**
     * Liefert true zurück, falls vor der aktuellen Frage noch eine Frage
     * im Quiz vorhanden ist
     * @return
     */
    public
    function getHasPreviousQuestion()
    {
        return $this->getActualQuestionNumber() > 0;
    }

    /**
     * Liefert die ganzen Fragen des Quiz zurück
     * @return
     */
    public
    function getQuestions()
    {
        return $this->questions;
    }

    /**
     * Liefert die Anzahl der beantworteten Fragen des Quiz zurück
     * @return
     */
    public
    function getNumberAnsweredQuestions()
    {
        $ret = 0;
        foreach ($this->getQuestions() as $question) {
            if ($question->getAnswered())
                $ret++;
        }
        return $ret;
    }

    /**
     * Liefert die Anzahl der nicht beantworteten Fragen des Quiz zurück
     * @return
     */
    public
    function getNumberUnansweredQuestions()
    {

        $ret = 0;
        foreach ($this->getQuestions() as $question) {
            if (!$question->getAnswered())
                $ret++;
        }
        return $ret;
    }

    /**
     * Das Quiz wird fertiggestellt
     * @param
     */
    public
    function setCompleted($completed)
    {
        $this->completed = $completed;
    }

    /**
     * Kontrolliert ob das Quiz fertiggestellt wurde
     * @return
     */
    public
    function getCompleted()
    {
        return $this->completed;
    }

    /**
     * Liefert die Anzahl der richtig gesetzten Antwortmöglichkeiten zurück. Für
     * jede richtige Antwort wird ein Punkt vergeben
     * @return
     */
    public
    function getPoints()
    {
    }

    /**
     * Liefert die insgesamt möglichen Punkte zurück. Pro Antwortmöglichkeit wird
     * ein Punkt vergeben
     * @return
     */
    public
    function getMaximalPoints()
    {
        return self::QUESTIONS_COUNT * 3;
    }

    /**
     * Ermittelt die Anzahl der richtig gesetzten Antworten in Prozent gerundet auf 2
     * Kommastellen
     * @return
     */
    public
    function getPointsInPercent()
    {
    }

    /**
     * Liefert zurück ob das Quiz bestanden wurde oder nicht. Ein Quiz kann nur
     * bestanden werden, falls es fertiggestellt wurde und falls MINIMUM_PERCENT
     * der Punkte erzielt werden konnten
     * @return
     */
    public
    function getPassed()
    {
        if ($this->completed) {

        } else {
            return false;
        }
    }
}
