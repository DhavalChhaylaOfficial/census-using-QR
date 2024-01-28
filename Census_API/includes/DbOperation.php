<?php

class DbOperation
{
    private $con_wpf;
    private $con_mob;

    function __construct()
    {
        require_once dirname(__FILE__) . '/DbConnect.php';

        $db_wpf = new DbConnect();
        $db_mob = new DbConnect1();

        $this->con_wpf = $db_wpf->connect();
        $this->con_mob = $db_mob->connect();
    }

    function insertDeo($emp_id, $deo_mob_no, $deo_pass)
    {
        $stmt = $this->con_wpf->prepare("INSERT INTO deo_auth (emp_id, deo_mob_no, deo_pass) VALUES (?, ?, ?)");
        $stmt->bind_param("sss", $emp_id, $deo_mob_no, $deo_pass);
        if ($stmt->execute())
            return true;
        return false;
    }

    function insertEnu($enu_id, $enu_mob_no, $block_id, $enu_pass)
    {
        $stmt = $this->con_mob->prepare("INSERT INTO enu_auth (enu_id, enu_mob_no, block_id, enu_pass) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssis", $enu_id, $enu_mob_no, $block_id, $enu_pass);
        if ($stmt->execute())
            return true;
        return false;
    }

    function deoLogin()
    {
        $emp_id = $_POST["emp_id"];
        $deo_pass = $_POST["deo_pass"];

        $stmt = $this->con_wpf->prepare("SELECT emp_id,deo_pass FROM deo_auth WHERE emp_id = '$emp_id' AND deo_pass = '$deo_pass'");
        $stmt->execute();
        $stmt->bind_result($emp_id, $deo_pass);

        $heroes = array();
        while ($stmt->fetch()) {
            $hero  = array();
            $hero['emp_id'] = $emp_id;
            $hero['deo_pass'] = $deo_pass;

            array_push($heroes, $hero);
        }

        return $heroes;
    }

    function enuLogin($enu_id, $enu_pass)
    {
        $enu_id = trim($_POST['enu_id']);
        $enu_pass = trim($_POST['enu_pass']);

        $stmt = $this->con_mob->prepare("SELECT enu_id,enu_pass FROM enu_auth WHERE enu_id = '$enu_id' AND enu_pass = '$enu_pass'");
        $stmt->execute();
        $stmt->bind_result($enu_id, $enu_pass);

        $heroes = array();

        while ($stmt->fetch()) {
            $hero  = array();
            $hero['enu_id'] = $enu_id;
            $hero['enu_pass'] = $enu_pass;

            array_push($heroes, $hero);
        }

        return $heroes;
    }

    function uidData()
    {
        $uid = trim($_POST['uid']);

        $stmt = $this->con_wpf->prepare("SELECT uid,u_name,u_mob_no,u_address,u_gender,u_cast,u_dob FROM uid_data WHERE uid = '$uid'");
        $stmt->execute();
        $stmt->bind_result($uid, $name, $mob, $address, $gender, $cast, $dob);

        $heroes = array();
        while ($stmt->fetch()) {
            $hero  = array();
            $hero['uid'] = $uid;
            $hero['u_name'] = $name;
            $hero['u_mob_no'] = $mob;
            $hero['u_address'] = $address;
            $hero['u_gender'] = $gender;
            $hero['u_cast'] = $cast;
            $hero['u_dob'] = $dob;

            array_push($heroes, $hero);
        }

        return $heroes;
    }

    function enuData()
    {
        $enu_id = trim($_POST['enu_id']);

        $stmt = $this->con_mob->prepare("SELECT enu_id,enu_name FROM enu_personal WHERE enu_id = '$enu_id'");
        $stmt->execute();
        $stmt->bind_result($enu_id, $enu_name);

        $heroes = array();
        while ($stmt->fetch()) {
            $hero  = array();
            $hero['enu_id'] = $enu_id;
            $hero['enu_name'] = $enu_name;

            array_push($heroes, $hero);
        }

        return $heroes;
    }

    function insertHeadInfo($head_uid, $head_edu, $head_fos, $head_mt, $head_of, $head_ds, $head_hc, $head_dtw, $head_mot)
    {
        $stmt = $this->con_mob->prepare("INSERT INTO head_info (head_uid, head_edu, head_fos, head_mt, head_of, head_ds, head_hc, head_dtw, head_mot) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("sssssssss", $head_uid, $head_edu, $head_fos, $head_mt, $head_of, $head_ds, $head_hc, $head_dtw, $head_mot);
        if ($stmt->execute())
            return true;
        return false;
    }

    function insertFamilyInfo($head_uid, $family_nop, $family_nmc, $family_nom, $family_nof, $family_noc, $family_noe, $family_cof)
    {
        $stmt = $this->con_mob->prepare("INSERT INTO family_info (head_uid, family_nop, family_nmc, family_nom, family_nof, family_noc, family_noe, family_cof) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("siiiiiis", $head_uid, $family_nop, $family_nmc, $family_nom, $family_nof, $family_noc, $family_noe, $family_cof);
        if ($stmt->execute())
            return true;
        return false;
    }

    function insertHomeInfo($head_uid, $home_moh, $home_own, $home_use, $home_con, $home_sdw, $home_sol, $home_scf, $home_sanit, $home_lack_sanit, $home_dist_health, $home_lor, $home_cur_dur)
    {
        $stmt = $this->con_mob->prepare("INSERT INTO home_info (head_uid, home_moh, home_own, home_use, home_con, home_sdw, home_sol, home_scf, home_sanit, home_lack_sanit, home_dist_health, home_lor, home_cur_dur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $stmt->bind_param("sssssssssssss", $head_uid, $home_moh, $home_own, $home_use, $home_con, $home_sdw, $home_sol, $home_scf, $home_sanit, $home_lack_sanit, $home_dist_health, $home_lor, $home_cur_dur);
        if ($stmt->execute())
            return true;
        return false;
    }

    function insertSub($head_uid,$enu_id)
    {
        $stmt = $this->con_mob->prepare("INSERT INTO sub_auth (head_uid, enu_id) VALUES (?, ?)");
        $stmt->bind_param("ss", $head_uid,$enu_id);
        if ($stmt->execute())
            return true;
        return false;
    }



}



