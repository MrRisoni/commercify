<?php

$postObj['shop'] = array('id' => 3);
$postObj['billTo'] = array('id' => 1);
$postObj['shipTo'] = array('id' => 1);
$postObj['pay'] = array('id' => 1);
$postObj['currencyCode'] = 'EUR';
$postObj['currency'] = 'EUR';
$postObj['deliveryClass'] = array('id' => 1);
$postObj['usr'] = array('id' => 1);

$itm1  = array('quantity' => 1, 'prod' => array('id' => 4408));
$postObj['items'] = [$itm1];


echo json_encode($postObj);
$ch = curl_init();

curl_setopt($ch, CURLOPT_URL,"http://localhost:8080/api/order/shipping_cost");
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