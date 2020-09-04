<?php

$orderBy = array('orderBy' => 'best_seller', 'sortOrder'=> 'asc');
$filters[] = array('attributeId' => 22,'attributeCode'=> 'Key','from' =>-1,'to' => -1,'type' => 'str','valueStr' => 'Bb','value' => -1);
$filters[] = array('attributeId' => 23,'attributeCode'=> 'Color','from' =>-1,'to' => -1,'type' => 'str','valueStr' => 'Green','value' => -1);
$filters[] = array('attributeId' => 22,'attributeCode'=> 'Key','from' =>-1,'to' => -1,'type' => 'str','valueStr' => 'C','value' => -1);


$postObj['orderBy'] = $orderBy;
$postObj['filters'] = $filters;
$postObj['minPrice'] = 10;
$postObj['maxPrice'] = 5000;
$postObj['currentPage'] =1;
$postObj['perPage'] = 5;

echo json_encode($postObj);
$ch = curl_init();

curl_setopt($ch, CURLOPT_URL,"http://localhost:8080/api/browse/shop/3/category/6");
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