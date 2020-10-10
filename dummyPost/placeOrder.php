<?php

$postObj['shop'] = array('id' => 3);
$postObj['billTo'] = array('id' => 1);
$postObj['shipTo'] = array('id' => 1);
$postObj['pay'] = array('id' => 1);
$postObj['currencyCode'] = 'EUR';
$postObj['currency'] = 'EUR';
$postObj['usr'] = array('id' => 1);

$itm1  = array('quantity' => 1, 'shipClassId' => 4, 'prod' => array('id' => 4408)); // trumpet with ACS
$itm2  = array('quantity' => 1, 'shipClassId' => 3, 'prod' => array('id' => 4409)); // double bass DHL with DHL

$postObj['items'] = [$itm1,$itm2];


echo json_encode($postObj);
$ch = curl_init();

curl_setopt($ch, CURLOPT_URL,"http://localhost:8080/api/order/place_order");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS,json_encode($postObj));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HEADER, true);
curl_setopt($ch, CURLOPT_HTTPHEADER,
    array(
        'Content-Type:application/json',
        'Content-Length: ' . strlen(json_encode($postObj))
    )
);
$server_output = curl_exec($ch);
var_dump($server_output);
curl_close ($ch);

?>