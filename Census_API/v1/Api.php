<?php
require_once '../includes/DbOperation.php';

function isTheseParametersAvailable($params)
{
    $available = true;
    $missingparams = "";

    foreach ($params as $param) {
        if (!isset($_POST[$param]) || strlen($_POST[$param]) <= 0) {
            $available = false;
            $missingparams = $missingparams . ", " . $param;
        }
    }

    if (!$available) {
        $response = array();
        $response['error'] = true;
        $response['message'] = 'Parameters ' . substr($missingparams, 1, strlen($missingparams)) . ' missing';
        echo json_encode($response);
        die();
    }
}

$response = array();

if (isset($_GET['apicall'])) {
    switch ($_GET['apicall']) {

        case 'Insert_DEO':
            isTheseParametersAvailable(array('emp_id', 'deo_mob_no', 'deo_pass'));
            $db_wpf = new DbOperation();
            $result = $db_wpf->insertDeo(
                $_POST['emp_id'],
                $_POST['deo_mob_no'],
                $_POST['deo_pass']
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'Data-Entry-Officer addedd successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        case 'Insert_ENU':
            isTheseParametersAvailable(array('enu_id', 'enu_mob_no', 'block_id', 'enu_pass'));
            $db_mob = new DbOperation();
            $result = $db_mob->insertEnu(
                $_POST['enu_id'],
                $_POST['enu_mob_no'],
                $_POST['block_id'],
                $_POST['enu_pass']
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'Enumerator addedd successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        case 'deologin':
            isTheseParametersAvailable(array('emp_id', 'deo_pass'));
            $db_wpf = new DbOperation();
            $result = $db_wpf->deoLogin(
                $_GET['emp_id'],
                $_GET['deo_pass']
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'DEO Find successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        case 'enulogin':
            isTheseParametersAvailable(array('enu_id', 'enu_pass'));
            $db_mob = new DbOperation();
            $result = $db_mob->enuLogin(
                $_GET['enu_id'],
                $_GET['enu_pass']
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'Enumerator Find successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        case 'uiddata':
            $db_wpf = new DbOperation();
            $response['error'] = false;
            $response['message'] = 'Request successfully completed';
            $response['heroes'] = $db_wpf->uidData();
            break;

        case 'enudata':
            $db_mob = new DbOperation();
            $response['error'] = false;
            $response['message'] = 'Request successfully completed';
            $response['heroes'] = $db_mob->enuData();
            break;

        case 'InsertHeadInfo':
            isTheseParametersAvailable(array('head_uid', 'head_edu', 'head_fos', 'head_mt', 'head_of', 'head_ds', 'head_hc', 'head_dtw', 'head_mot'));
            $db_mob = new DbOperation();
            $result = $db_mob->insertHeadInfo(
                $_POST['head_uid'],
                $_POST['head_edu'],
                $_POST['head_fos'],
                $_POST['head_mt'],
                $_POST['head_of'],
                $_POST['head_ds'],
                $_POST['head_hc'],
                $_POST['head_dtw'],
                $_POST['head_mot']
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'head-info addedd successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        case 'InsertFamilyInfo':
            isTheseParametersAvailable(array('head_uid', 'family_nop', 'family_nmc', 'family_nom', 'family_nof', 'family_noc', 'family_noe', 'family_cof'));
            $db_mob = new DbOperation();
            $result = $db_mob->insertFamilyInfo(
                $_POST['head_uid'],
                $_POST['family_nop'],
                $_POST['family_nmc'],
                $_POST['family_nom'],
                $_POST['family_nof'],
                $_POST['family_noc'],
                $_POST['family_noe'],
                $_POST['family_cof']
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'family-info addedd successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

        case 'InsertHomeInfo':
            isTheseParametersAvailable(array('head_uid', 'home_moh', 'home_own', 'home_use', 'home_con', 'home_sdw', 'home_sol', 'home_scf', 'home_sanit', 'home_lack_sanit', 'home_dist_health', 'home_lor', 'home_cur_dur'));
            $db_mob = new DbOperation();
            $result = $db_mob->insertHomeInfo(
                $_POST['head_uid'],
                $_POST['home_moh'],
                $_POST['home_own'],
                $_POST['home_use'],
                $_POST['home_con'],
                $_POST['home_sdw'],
                $_POST['home_sol'],
                $_POST['home_scf'],
                $_POST['home_sanit'],
                $_POST['home_lack_sanit'],
                $_POST['home_dist_health'],
                $_POST['home_lor'],
                $_POST['home_cur_dur'],
            );

            if ($result) {
                $response['error'] = false;
                $response['message'] = 'Home-info addedd successfully';
            } else {
                $response['error'] = true;
                $response['message'] = 'Some error occurred please try again';
            }
            break;

            case 'Insert_SUB':
                isTheseParametersAvailable(array('head_uid', 'enu_id'));
                $db_mob = new DbOperation();
                $result = $db_mob->insertSub(
                    $_POST['head_uid'],
                    $_POST['enu_id']
                );
    
                if ($result) {
                    $response['error'] = false;
                    $response['message'] = 'Sub-Auth addedd successfully';
                } else {
                    $response['error'] = true;
                    $response['message'] = 'Some error occurred please try again';
                }
                break;
    }
} else {
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
}
echo json_encode($response);
