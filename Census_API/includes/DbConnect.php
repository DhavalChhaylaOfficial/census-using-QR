<?php

class DbConnect
{
    private $con_wpf;

    function __construct()
    {
    }

    function connect()
    {
        include_once dirname(__FILE__) . '/Constants.php';

        $this->con_wpf = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_WPF_NAME);

        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        return $this->con_wpf;
    }
}

class DbConnect1
{
    private $con_mob;

    function __construct()
    {
    }

    function connect()
    {
        include_once dirname(__FILE__) . '/Constants.php';

        $this->con_mob = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_MOB_NAME);

        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

        return $this->con_mob;
    }
}
