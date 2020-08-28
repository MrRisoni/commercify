<?php

$orderBy = array('orderBy' => 'best_seller', 'sortOrder'=> 'asc');
$filters[] = array('attributeId' => 9,'attributeCode'=> 'SSD','from' =>-1,'to' => -1,'type' => 'bool','valueStr' => '','value' => 1);
$filters[] = array('attributeId' => 7,'attributeCode'=> 'Screen_inches','from' =>-1,'to' => 17,'type' => 'range','valueStr' => '','value' => -1);
$filters[] = array('attributeId' => 9,'attributeCode'=> 'SSD','from' =>-1,'to' => -1,'type' => 'bool','valueStr' => '','value' => 0);


$postObj['orderBy'] = $orderBy;
$postObj['filters'] = $filters;
$postObj['minPrice'] = 10;
$postObj['maxPrice'] = 5000;
$postObj['currentPage'] =1;
$postObj['perPage'] = 5;

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL,"http://localhost:8080/api/category/criteria");
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