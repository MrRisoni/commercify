<?php

$orderBy = array('orderBy' => 'price', 'sortOrder'=> 'asc');
$filters[] = array('attributeId' => 9,'from' =>-1,'to' => -1,'type' => 'number','valueStr' => '','value' => 1);
$filters[] = array('attributeId' => 7,'from' =>-1,'to' => 7,'type' => 'str','valueStr' => '','value' => -1);


$postObj['orderBy'] = $orderBy;
$postObj['filters'] = $filters;



$ch = curl_init();

curl_setopt($ch, CURLOPT_URL,"http://localhost:8080/api/category");
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

curl_close ($ch);

?>