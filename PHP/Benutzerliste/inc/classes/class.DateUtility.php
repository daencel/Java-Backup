<?php

class DateUtility
{
    protected $string;

    function __construct($date = null)
    {
        $this->string = $date;
    }

    function validate()
    {
        if (strtotime($this->string) > strtotime('now')) {
            return false;
        } else
            return true;
    }

}